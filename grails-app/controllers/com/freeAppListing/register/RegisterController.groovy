package com.freeAppListing.register

import grails.converters.JSON
import grails.plugin.springsecurity.annotation.Secured

@Secured('permitAll')
class RegisterController {

    static allowedMethods = [save:"POST"]

    def index() {

    }

    def save(){
        def parametrosJson = request.JSON
        def respuesta = [status: 1, messaje:"Hola a todos usuarios registrados"]
        render respuesta as JSON
    }

}
