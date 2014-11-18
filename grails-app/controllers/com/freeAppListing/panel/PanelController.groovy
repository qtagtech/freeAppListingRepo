package com.freeAppListing.panel

import com.freeAppListing.application.Application
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

                def applicationData = Application.list()

                render view:"index",  model: [activeMenu: 1, dataUser:userLoggin, applicationData:applicationData]
            }

        }

    }
}
