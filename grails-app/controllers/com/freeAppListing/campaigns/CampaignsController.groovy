package com.freeAppListing.campaigns

import com.freeAppListing.application.Application
import com.freeAppListing.campaign.Campaign
import com.freeAppListing.platform.Platforms
import com.freeAppListing.publisher.Publisher
import com.mongodb.BasicDBObject
import grails.converters.JSON
import grails.plugin.springsecurity.annotation.Secured
import grails.transaction.Transactional
import org.bson.types.ObjectId

@Secured(['ROLE_USER'])
class CampaignsController {
    def mongo

    static allowedMethods = [save: "POST", delete: "POST"]

    def springSecurityService

    @Transactional(readOnly = true)
    def create() {
        if(springSecurityService.isLoggedIn()){
            def userLoggin = springSecurityService.getCurrentUser()

            List<Application> applicationList = Application.list()

            List<Publisher> publisherList = Publisher.list()

            List<Platforms> platformsList = Platforms.list()

            render view: "create", model: [
                    activeMenu:3 ,
                    dataUser:userLoggin,
                    applicationList:applicationList,
                    publisherList: publisherList,
                    platformsList: platformsList
            ]
        }
    }

    @Transactional(readOnly = true)
    def list(){
        if(springSecurityService.isLoggedIn()){
            def userLoggin = springSecurityService.getCurrentUser()

            List<Campaign> campaigns = Campaign.list()

            render view: "list", model: [
                    activeMenu:3 ,
                    dataUser:userLoggin,
                    campaigns:campaigns
            ]
        }
    }

    @Transactional
    def save(){
        def db = mongo.getDB(grailsApplication.config.com.freeAppListing.database)
        if(springSecurityService.isLoggedIn()){

            def respuesta

            BasicDBObject queryApp = new BasicDBObject().append("_id",new ObjectId(params.appId))
            def application = db.application.findOne(queryApp)

            BasicDBObject queryPlat = new BasicDBObject().append("_id",new ObjectId(params.plfmId))
            def plataforma = db.platforms.findOne(queryPlat)

            BasicDBObject queryPubl = new BasicDBObject().append("_id",new ObjectId(params.plshrId))
            def publisher = db.publisher.findOne(queryPubl)


            def resultado = db.campaign.insert(name:params.name, application: application, plataforma: plataforma, publisher: publisher)
            if(resultado.error)
            {
                respuesta = [status: 0]
                render respuesta as JSON
                return
            }
            BasicDBObject query = new BasicDBObject().append("name",params.name)
            def campaign = db.campaign.findOne(query)

            respuesta = [status: 1]
            render respuesta as JSON
        }
    }

    @Transactional
    def delete(){
        def db = mongo.getDB(grailsApplication.config.com.freeAppListing.database)
        if(springSecurityService.isLoggedIn()){
            def respuesta

            respuesta = [status: 1]
            render respuesta as JSON
        }
    }
}
