package com.freeAppListing.campaigns

import com.freeAppListing.application.Application
import com.freeAppListing.campaign.Campaign
import com.freeAppListing.platform.Platforms
import com.freeAppListing.publisher.Publisher
import grails.converters.JSON
import grails.plugin.springsecurity.annotation.Secured
import grails.transaction.Transactional

@Secured(['ROLE_USER'])
class CampaignsController {
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
        if(springSecurityService.isLoggedIn()){
            def respuesta

            respuesta = [status: 1]
            render respuesta as JSON
        }
    }

    @Transactional
    def delete(){
        if(springSecurityService.isLoggedIn()){
            def respuesta

            respuesta = [status: 1]
            render respuesta as JSON
        }
    }
}
