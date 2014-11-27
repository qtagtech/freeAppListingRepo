package com.freeAppListing.conversion

import com.mongodb.BasicDBObject
import grails.converters.JSON
import grails.plugin.springsecurity.annotation.Secured
import org.bson.types.ObjectId

@Secured(['permitAll'])
class ConversionController {

    def mongo

    static defaultAction = "index"

    static allowedMethods = [index: "GET"]

    def securityService
    def utilsService

    @Secured(['permitAll'])
    def index() {

        def db = mongo.getDB(grailsApplication.config.com.freeAppListing.database)

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

        params.remove("ceId")
        params.remove("action")
        params.remove("controller")
        params.remove("format")

        BasicDBObject conversion = new BasicDBObject()

        /**
         * Producer external id to conversion
         */
        def randomNum = utilsService.randomNumber(conversion)
        while(randomNum == 0L){
            randomNum = utilsService.randomNumber(conversion)
        }
        def ecrypExIdConver = securityService.encryt(randomNum.toString())
        conversion.append("exConvId",ecrypExIdConver)

        /**
         * Assigned id of the campaign
         */
        conversion.append("campaignId",campaign._id)

        /**
         * Assigned the aplication id to conversion
         */
        //TODO agregar id de la aplicacion con links
        conversion.append("appId", campaign.application._id)
        conversion.append("appName", campaign.application.nombre)

        /**
         * Assigned event click to conversion
         */
        // TODO agregar evento clic

        /**
         * Looping the all parameters that need save
         */
        params.each() { key, value ->
            conversion.append(key, value)
        }

        /**
        * Save all parameters in document conversion
         */
        def resultadoConversion = db.conversion.insert(conversion)

        /**
         * Look if has been ocurred a error
         */
        if(resultadoConversion.error)
        {
            response.setStatus(400)
            def result = [status: 400, code: 55512,message: 'Error saving new conversion to database: '+resultadoConversion?.lastError?.err]
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
