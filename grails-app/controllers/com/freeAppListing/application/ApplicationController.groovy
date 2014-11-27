package com.freeAppListing.application

import com.freeAppListing.company.Company
import com.freeAppListing.link.Link
import com.freeAppListing.platform.Platforms
import com.mongodb.BasicDBObject
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

        def respuesta

        if( StringUtils.isEmpty(params.id.toString())){
            throw new Exception("Id is null")
        }

        def application = Application.get(params.id)

        if(application.nombre != null || application.nombre != ""){
            application.delete()
        }

        respuesta = [status:1]
        render respuesta as JSON

    }
}
