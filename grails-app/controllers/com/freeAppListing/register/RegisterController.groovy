package com.freeAppListing.register

import grails.converters.JSON
import grails.plugin.springsecurity.annotation.Secured

@Secured('permitAll')
class RegisterController {

    static allowedMethods = [save:"POST"]

    def index() {

    }

    def save(){
        def parametro = params.lenght
        log.debug"Render parametro ${parametro}"
        def respuesta = [status: 1, messaje:"Hola a todos usuarios registrados"]
        render respuesta as JSON
    }

}
