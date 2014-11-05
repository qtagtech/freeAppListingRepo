package com.freeAppListing.register

import grails.converters.JSON
import grails.plugin.springsecurity.annotation.Secured

@Secured('permitAll')
class RegisterController {

    static allowedMethods = [save:"POST"]

    def index() {

    }

    def save(){
        def dataJson = request.JSON
        def respuesta = [status: 1, messaje:"Now you are register, Thanks. "]
        render respuesta as JSON
    }

}
