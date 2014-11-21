package com.freeAppListing.panel

import com.freeAppListing.application.Application
import com.freeAppListing.campaign.Campaign
import com.freeAppListing.company.Company
import com.freeAppListing.eventType.EventType
import com.freeAppListing.platform.Platforms
import com.freeAppListing.publisher.Publisher
import grails.plugin.springsecurity.SpringSecurityService
import grails.plugin.springsecurity.annotation.Secured

@Secured(['ROLE_SUPERADMIN','ROLE_USER'])
class PanelController {
    def springSecurityService


    def index() {
        if(springSecurityService.isLoggedIn()){
            // get info user loggin
            def userLoggin = springSecurityService.getCurrentUser()

            // Do is roler is superadmin
            sec.ifAllGranted(roles: 'ROLE_SUPERADMIN'){
                //get info platform
                List<Platforms> platformsList = Platforms.list()

                //get info Publisher
                List<Publisher> publisherList = Publisher.list()

                // get infor event Type
                List<EventType> eventTypeList = EventType.list()

                render view:"index", model:[activeMenu: 1, dataUser:userLoggin,platformsList:platformsList, publisherList: publisherList, eventTypeList: eventTypeList]
            }

            // Do if role is user
            sec.ifAllGranted(roles: 'ROLE_USER'){

                def countCamp = 0
                def listCamp = ""
                def countApp = Application.count()
                def listApp = Application.list()
                def countPlat = Platforms.count()
                def listPlat = Platforms.list()
                def countPubli = Publisher.count()
                def listPubli = Publisher.list()
                def countEvTp = EventType.count()
                def listEvTp = EventType.list()
                def countCampaign = Campaign.count()
                def listCampaign = Campaign.list()

                render view:"index",  model: [
                        activeMenu: 1,
                        dataUser:userLoggin,
                        countCamp: countCamp,
                        listCamp: listCamp,
                        countApp: countApp,
                        listApp: listApp,
                        countPlat: countPlat,
                        listPlat: listPlat.name,
                        countPub: countPubli,
                        listPub: listPubli.name,
                        countEvTp: countEvTp,
                        listEvTp: listEvTp.name,
                        countCampaign: countCampaign,
                        listCampaign:listCampaign
                ]
            }

        }

    }
}
