package com.freeAppListing.serve

import com.mongodb.BasicDBObject
import grails.plugin.springsecurity.annotation.Secured
import groovyx.net.http.HTTPBuilder
import org.apache.commons.logging.LogFactory
import org.bson.types.ObjectId

@Secured(['permitAll'])
class ServeController {

    private static final log = LogFactory.getLog(ServeController.class)

    def mongo

    static defaultAction = "index"

    static allowedMethods = [index: "GET"]

    def securityService
    def utilsService


    def index() {
        def db = mongo.getDB(grailsApplication.config.com.freeAppListing.database)

        /**
         * General validations
         */
            // Look at if id of the url exist
        if(params.ciId==null||params.ciId==""){
            log.error "The campaign identifier doesn't not exist."
        }

        def cid = securityService.decrypt(params.ceId)

        if(cid == params.ciId ){
            log.error "The identifier the campaign is invalid."
        }

        BasicDBObject query = new BasicDBObject().append("externalId", Long.parseLong(cid))
        def campaign = db.campaign.findOne(query)

        if(campaign == null || campaign ==""){
            log.error "Don't found the campaign with the identifier obtained"
        }

        /**
         * Redirect if have link to store app o hassoffers
         */
        def linkUrlHasOffers
        def linkUrlApp

        for(link in campaign.application.link){
            linkUrlApp = link.urlDirect
            linkUrlHasOffers = link.urlHasOffer
        }

        def ciid = securityService.decrypt(params.ciId)

        BasicDBObject queryConversion = new BasicDBObject().append("campaignId", new ObjectId(ciid))
        def conversion = db.conversion.find(queryConversion)
        def countConversCamp = conversion.count()

        if(countConversCamp==0){
            log.error "The event install don't save because don't have events type click."
            if(linkUrlHasOffers == null || linkUrlHasOffers == ""){
                redirect url: "http://${linkUrlApp}"
            }else{
                redirect url: "http://${linkUrlHasOffers}"
            }
        }

        BasicDBObject queryServe = new BasicDBObject().append("campaignId", new ObjectId(ciid))
        def serveExist = db.serve.find(queryServe)
        def countServeCamp = serveExist.count()

        if(countConversCamp <= countServeCamp){
            log.error "The event install don't save because don't have events type click."
            if(linkUrlHasOffers == null || linkUrlHasOffers == ""){
                redirect url: "http://${linkUrlApp}"
                return
            }else{
                redirect url: "http://${linkUrlHasOffers}"
                return
            }
        }

        params.remove("ceId")
        params.remove("action")
        params.remove("controller")
        params.remove("format")

        BasicDBObject serve = new BasicDBObject()

        def randomNum = utilsService.randomNumber(serve)
        while(randomNum == 0L){
            randomNum = utilsService.randomNumber(serve)
        }
        def ecrypExIdServe = securityService.encryt(randomNum.toString())
        serve.append("exServId",ecrypExIdServe)

        /**
         * Assigned id of the campaign
         */
        serve.append("campaignId",campaign._id)

        /**
         * Assigned the aplication id to conversion
         */
        //TODO agregar id de la aplicacion con links
        serve.append("appId", campaign.application._id)
        serve.append("appName", campaign.application.nombre)

        /**
         * Assigned event click to conversion
         */
        // TODO agregar evento clic

        /**
         * Looping the all parameters that need save
         */
        params.each() { key, value ->
            serve.append(key, value)
        }

        /**
         * Save all parameters in document conversion
         */
        def resultadoServe = db.serve.insert(serve)

        /**
         * Look if has been ocurred a error
         */
        if(resultadoServe.error)
        {
            log.error "Error saving new conversion to database: " + resultadoServe?.lastError?.err
        }else{
            log.info "The install event  was saved successfully."
        }

        def http = new HTTPBuilder("http://${campaign.trakSubpublisher}")
        http.get(path:'/'){resp ->
            assert resp.statusLine.statusCode == 200
            log.info "The request to url the sub publisher was successful"
        }

        if(linkUrlHasOffers == null || linkUrlHasOffers == ""){
            redirect url: "http://${linkUrlApp}"
        }else{
            redirect url: "http://${linkUrlHasOffers}"
        }
    }
}
