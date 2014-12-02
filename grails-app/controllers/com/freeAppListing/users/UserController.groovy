package com.freeAppListing.users

import grails.plugin.springsecurity.annotation.Secured
import grails.transaction.Transactional

@Secured(['ROLE_SUPERADMIN'])
class UserController {

    static allowedMethods = [delete: "POST"]

    @Transactional
    def delete(){

    }
}
