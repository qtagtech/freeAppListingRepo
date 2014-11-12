package com.freeAppListing.panel

import grails.plugin.springsecurity.SpringSecurityService
import grails.plugin.springsecurity.annotation.Secured

@Secured(['ROLE_SUPERADMIN','ROLE_USER'])
class PanelController {
    def springSecurityService


    def index() {
        if(springSecurityService.isLoggedIn()){
            // get info user loggin
            def userLoggin = springSecurityService.getCurrentUser()

            //get info platform


            [dataUser:userLoggin]
        }

    }
}
