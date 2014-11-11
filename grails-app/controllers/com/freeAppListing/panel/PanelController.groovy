package com.freeAppListing.panel

import grails.plugin.springsecurity.SpringSecurityService
import grails.plugin.springsecurity.annotation.Secured

@Secured(['ROLE_ADMIN','ROLE_USER'])
class PanelController {
    def springSecurityService

    @Secured(['ROLE_ADMIN','ROLE_USER'])
    def index() {
        if(springSecurityService.isLoggedIn()){

            def userLoggin = springSecurityService.getCurrentUser()
            [dataUser:userLoggin]
        }

    }
}
