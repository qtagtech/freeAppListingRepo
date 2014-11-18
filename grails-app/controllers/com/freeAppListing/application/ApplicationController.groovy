package com.freeAppListing.application

import com.freeAppListing.platform.Platforms
import grails.plugin.springsecurity.annotation.Secured

@Secured(['ROLE_USER'])
class ApplicationController {
    def springSecurityService

    def create() {
        if(springSecurityService.isLoggedIn()){
            def userLoggin = springSecurityService.getCurrentUser()

            List<Platforms> platformsList = Platforms.list()

            render view: "create", model: [activeMenu:2 , dataUser:userLoggin, platformsList: platformsList]
        }
    }
}
