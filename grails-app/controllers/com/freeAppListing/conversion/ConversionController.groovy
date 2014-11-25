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
            def result = [status: 400, code: 55514,message: 'The campaign identifier not exist.']
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
        params.each() { key, value ->
            conversion.append(key, value)
        }

        def randomNum = utilsService.randomNumber(conversion)

        while(randomNum == 0L){
            randomNum = utilsService.randomNumber(conversion)
        }

        def ecrypExIdConver = securityService.encryt(randomNum.toString())

        conversion.append("externalId",ecrypExIdConver)
        conversion.append("campaign",campaign._id)

        def resultadoConversion = db.conversion.insert(conversion)

        if(resultadoConversion.error)
        {
            response.setStatus(400)
            def result = [status: 400, code: 55512,message: 'Error saving new conversion to database: '+resultadoConversion?.lastError?.err]
            render result as JSON
            return
        }

        redirect url: "http://www.google.com.co"
    }
}
