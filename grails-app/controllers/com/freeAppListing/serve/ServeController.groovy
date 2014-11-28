package com.freeAppListing.serve

import com.mongodb.BasicDBObject
import grails.converters.JSON
import grails.plugin.springsecurity.annotation.Secured
import org.bson.types.ObjectId

@Secured(['permitAll'])
class ServeController {

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
            response.setStatus(400)
            def result = [status: 400, code: 55514,message: "The campaign identifier doesn't not exist."]
            render result as JSON
            return
        }

        def cid = securityService.decrypt(params.ceId)

        if(cid == params.ciId ){
            response.setStatus(400)
            def result = [status: 400, code: 456546 ,message: 'The identifier the campaign is invalid.']
            render result as JSON
            return
        }

        BasicDBObject query = new BasicDBObject().append("externalId", Long.parseLong(cid))
        def campaign = db.campaign.findOne(query)

        if(campaign == null || campaign ==""){
            response.setStatus(400)
            def result = [status: 400, code: 55514,message: 'Don\'t found the campaign with the identifier obtained']
            render result as JSON
            return
        }

        def ciid = securityService.decrypt(params.ciId)

        BasicDBObject queryConversion = new BasicDBObject().append("campaignId", new ObjectId(ciid))
        def conversion = db.conversion.findOne(queryConversion)

        if(conversion==null){
            response.setStatus(400)
            def result = [status: 400, code: 55514,message: "The event install save done."]
            render result as JSON
            return
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
            response.setStatus(400)
            def result = [status: 400, code: 55512,message: 'Error saving new conversion to database: '+resultadoServe?.lastError?.err]
            render result as JSON
            return
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


        if(linkUrlHasOffers == null || linkUrlHasOffers == ""){
            redirect url: "http://${linkUrlApp}"
        }else{
            redirect url: "http://${linkUrlHasOffers}"
        }
    }
}
