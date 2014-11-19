package com.freeAppListing.application

import com.freeAppListing.company.Company
import com.freeAppListing.link.Link
import com.freeAppListing.platform.Platforms
import grails.converters.JSON
import grails.plugin.springsecurity.annotation.Secured
import grails.transaction.Transactional
import org.apache.commons.lang.StringUtils

@Secured(['ROLE_USER'])
class ApplicationController {

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

            render view: "list", model: [activeMenu:2 , dataUser:userLoggin, applicationList: applicationList]
        }
    }

    @Transactional
    def save(){

        def userLoggin = springSecurityService.getCurrentUser()
        def data = request.JSON

        def company = Company.get(userLoggin.company.id)

        def application = new Application(
                nombre: data.nombre,
                keywords: data.keywords,
                description: data.description,
                company: company
        ).save(failOnError: true)

        println(application.errors.allErrors)

        if(data.link.size()>1){
            for(int i=0; i<data.link.size();i++){

                def platform = Platforms.get(data.link[i].platformsId)

                def link = new Link(
                        platforms: platform,
                        urlDirect: data.link[i].urlDirect,
                        urlHasOffer: data.link[i].urlHasOffer
                )

                application.addToLink(link)
            }
        }else{
            def platform = Platforms.get(data.link[0].platformsId)

            def link = new Link(
                    platforms: platform,
                    urlDirect: data.link[0].urlDirect,
                    urlHasOffer: data.link[0].urlHasOffer
            )

            application.addToLink(link)
        }

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
