package com.freeAppListing.campaigns

import com.freeAppListing.application.Application
import com.freeAppListing.campaign.Campaign
import com.freeAppListing.platform.Platforms
import com.freeAppListing.publisher.Publisher
import com.mongodb.BasicDBObject
import com.mongodb.DBCollection
import grails.converters.JSON
import grails.plugin.springsecurity.annotation.Secured
import grails.transaction.Transactional
import org.bson.types.ObjectId

@Secured(['ROLE_USER'])
class CampaignsController {

    def mongo

    static allowedMethods = [save: "POST", delete: "POST"]

    def springSecurityService

    def securityService

    @Transactional(readOnly = true)
    def create() {
        if(springSecurityService.isLoggedIn()){
            def userLoggin = springSecurityService.getCurrentUser()

            List<Application> applicationList = Application.list()
            def listApp = []
            applicationList.eachWithIndex { Application app, int i ->
                if(app.company.id== userLoggin.company[0].id){
                    listApp.add(app)
                }
            }

            List<Publisher> publisherList = Publisher.list()

            List<Platforms> platformsList = Platforms.list()

            render view: "create", model: [
                    activeMenu:3 ,
                    dataUser:userLoggin,
                    applicationList:listApp,
                    publisherList: publisherList,
                    platformsList: platformsList
            ]
        }
    }

    @Transactional(readOnly = true)
    def list(){
        def db = mongo.getDB(grailsApplication.config.com.freeAppListing.database)
        if(springSecurityService.isLoggedIn()){
            def userLoggin = springSecurityService.getCurrentUser()

            def campaigns = db.campaign.find()

            def listCampaign = []
            while(campaigns.hasNext()){
                def actual = campaigns.next()
                if(actual.application.company.id == userLoggin.company[0].id ){
                    listCampaign.add(actual)
                }
            }

            render view: "list", model: [
                    activeMenu:3 ,
                    dataUser:userLoggin,
                    campaigns:listCampaign
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

            def externalId = randomNumber()
            while(externalId == 0L){
                externalId = randomNumber()
            }
            def resultado = db.campaign.insert(
                    name: params.name,
                    externalId: externalId,
                    application: application,
                    plataforma: plataforma,
                    publisher: publisher,
                    trakingUrl: ""
            )

            BasicDBObject query = new BasicDBObject().append("name",params.name)
            def campaign = db.campaign.findOne(query)

            /**
             * Logica Linktrakker
             */
            String campaignExternalId = campaign.externalId.toString()
            String campaignInternalId = campaign._id.toString()

            String publisherInternalId = campaign.publisher._id.toString()
            String publisherExternalId = campaign.publisher.externalId.toString()

            def encryExternalCampId  = securityService.encryt(campaignExternalId)
            def encryInternalCampId  = securityService.encryt(campaignInternalId)
            def nameCampaig = campaign.name
            def encryInternalPublId  = securityService.encryt(publisherInternalId)
            def encryExternalPublId  = securityService.encryt(publisherExternalId)
            def namePublisher = campaign.publisher.name

            def linkConversion = g.createLink(
                    controller: "conversion",
                    params: [
                            "peId": encryExternalPublId,
                            "piId": encryInternalPublId,
                            "pn": namePublisher,
                            "ceId": encryExternalCampId,
                            "ciId": encryInternalCampId,
                            "cn": nameCampaig
                    ]
            )

            BasicDBObject queryNewTrakinlink = new BasicDBObject()
            queryNewTrakinlink.append(
                    "trakingUrl",linkConversion
            ).append(
                    "name",params.name
            ).append(
                    "externalId",externalId
            ).append(
                    "application",application
            ).append(
                    "plataforma",plataforma
            ).append(
                    "publisher",publisher
            ).append(
                    "trakSubpublisher",params.traksubpublisher
            )

            DBCollection collection =  db.getCollection("campaign")
            collection.update(query,queryNewTrakinlink)

            respuesta = [status: 1]
            render respuesta as JSON
        }
    }

    @Transactional
    def delete(){

        def db = mongo.getDB(grailsApplication.config.com.freeAppListing.database)

        if(springSecurityService.isLoggedIn()){
            def respuesta

            BasicDBObject query = new BasicDBObject().append("_id",new ObjectId(params.id))
            def campaignToDelete = db.campaign.findOne(query)

            db.campaign.remove(campaignToDelete)

            respuesta = [status: 1]
            render respuesta as JSON
        }
    }

    /**
     * Funciones utiles
     */
    private Long randomNumber (){
        def number =  (long) Math.floor(Math.random() * 900000000000L) + 100000000000L
        if(Campaign.findByExternalId(number)){
            return 0L
        }
        return number
    }
}
