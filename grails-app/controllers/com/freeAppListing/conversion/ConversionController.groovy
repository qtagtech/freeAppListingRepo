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

    @Secured(['permitAll'])
    def index() {

        def db = mongo.getDB(grailsApplication.config.com.freeAppListing.database)

        def dataUrl = params

        if(dataUrl==null || dataUrl==""){
            response.setStatus(400)
            def result = [status: 400, code: 55514,message: 'The params to process the request is invalid.']
            render result as JSON
            return
        }

        if(dataUrl.ciId==null||dataUrl.ciId==""){
            response.setStatus(400)
            def result = [status: 400, code: 55514,message: 'The campaign identifier not exist.']
            render result as JSON
            return
        }

        def cid = securityService.decrypt(dataUrl.ciId)

        if(cid == dataUrl.ciId ){
            response.setStatus(400)
            def result = [status: 400, code: 456546 ,message: 'The identifier the campaign is invalid.']
            render result as JSON
            return
        }

        BasicDBObject query = new BasicDBObject().append("_id", new ObjectId(cid))
        def campaign = db.campaign.findOne(query)

        if(campaign == null || campaign ==""){
            response.setStatus(400)
            def result = [status: 400, code: 55514,message: 'Don\'t found the campaign with the identifier obtained']
            render result as JSON
            return
        }

        params.remove("ciId")
        params.remove("action")
        params.remove("controller")
        params.remove("format")

        response.setStatus(200)
        def result = [status: 200, code: 200,message: 'The campaign exist and remove params']
        render result as JSON
        return
    }
}
