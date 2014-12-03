package com.freeAppListing.application

import com.freeAppListing.company.Company
import com.freeAppListing.link.Link
import com.freeAppListing.platform.Platforms
import com.mongodb.BasicDBObject
import com.mongodb.DBCollection
import grails.converters.JSON
import grails.plugin.springsecurity.annotation.Secured
import grails.transaction.Transactional
import org.apache.commons.lang.StringUtils
import org.bson.types.ObjectId

@Secured(['ROLE_USER'])
class ApplicationController {
    def mongo

    static allowedMethods = [save: "POST", delete: "POST"]

    def springSecurityService

    def create() {
        if(springSecurityService.isLoggedIn()){
            def userLoggin = springSecurityService.getCurrentUser()

            List<Platforms> platformsList = Platforms.list()

            render view: "create", model: [activeMenu:2 , dataUser:userLoggin, platformsList: platformsList]
        }
    }

    @Transactional(readOnly = true)
    def list(){
        if(springSecurityService.isLoggedIn()){
            def userLoggin = springSecurityService.getCurrentUser()

            List<Application> applicationList = Application.list()

            def listApp = []
            applicationList.eachWithIndex { Application app, int i ->
                if(app.company.id== userLoggin.company[0].id){
                    listApp.add(app)
                }
            }

            render view: "list", model: [activeMenu:2 , dataUser:userLoggin, applicationList: listApp]
        }
    }

    @Transactional
    def save(){
        def db = mongo.getDB(grailsApplication.config.com.freeAppListing.database)

        def userLoggin = springSecurityService.getCurrentUser()
        def data = request.JSON

        def company = Company.get(userLoggin.company.id)
        BasicDBObject platformm =  new BasicDBObject("_id", new ObjectId(data.platformid))
        def platform = db.platforms.findOne(platformm)

        def application = new Application(
                nombre: data.nombre,
                keywords: data.keywords,
                description: data.description,
                company: company,
                link: ""
        ).save(failOnError: true)

        println(application.errors.allErrors)

        def link = new Link(
                urlDirect: data.urlDirect,
                urlHasOffer: data.urlHasOffers,
                platforms: platform,
                application: application
        ).save(failOnError: true)

        application.addToLink(link)

        application.save(failOnError: true)

        def respuesta = [status:1]
        render respuesta as JSON
    }

    @Transactional
    def delete(){
        def db = mongo.getDB(grailsApplication.config.com.freeAppListing.database)
        def respuesta

        // validate that id of app is not null or empty
        if( StringUtils.isEmpty(params.id.toString())){
            throw new Exception("Id is null")
            response.setStatus(400)
            respuesta = [status:0]
            render respuesta as JSON
            return
        }

        // get the app for id
        def application = Application.get(params.id)
        if(application==null){
            throw  new Exception("The application don't exist.")
            response.setStatus(400)
            respuesta = [status:0]
            render respuesta as JSON
            return
        }

        def resultOne
        def resultTwo
        def resultTre
        // valiate if have campaigns
        DBCollection campaing = db.getCollection("campaign");
        BasicDBObject qCampaing = new BasicDBObject().append("application._id",application.id)
        def haveCampaigns = campaing.count(qCampaing)
        if(haveCampaigns>0){
            resultOne = campaing.remove(qCampaing)
        }

        // valiate if have clicks
        BasicDBObject qClicks = new BasicDBObject().append("appId",application.id)
        def haveClicks = db.conversion.count(qClicks)
        if(haveClicks>0){
            resultTwo = db.conversion.remove(qClicks)
        }
        // valiate if have installs
        BasicDBObject qInstall = new BasicDBObject().append("appId",application.id)
        def haveInstalls = db.serve.count(qInstall)
        if(haveInstalls>0){
            resultTre = db.serve.remove(qInstall)
        }

        // if diferent to null delete the app
        if(application.nombre != null || application.nombre != ""){
            application.delete()
        }

        respuesta = [status:1]
        render respuesta as JSON
    }
}
