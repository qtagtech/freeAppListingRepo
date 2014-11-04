package com.freeAppListing.panel

import grails.plugin.springsecurity.annotation.Secured

@Secured(['ROLE_ADMIN','ROLE_USER'])
class PanelController {

    def index() {
    }
}
