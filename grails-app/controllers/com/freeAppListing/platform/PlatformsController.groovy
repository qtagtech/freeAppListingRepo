package com.freeAppListing.platform

import grails.converters.JSON
import grails.plugin.springsecurity.annotation.Secured
import grails.transaction.Transactional

@Secured(['ROLE_SUPERADMIN'])
class PlatformsController {

    static allowedMethods = [save: "POST", delete: "POST"]

    @Transactional
    def save() {

        try{

            def newPlatform = new Platforms(
                    name: params.name,
                    web: params.web,
                    market: params.market,
                    description: params.description,
                    dateCreate: new Date(),
                    dateUpdate: new Date()
            ).save(failOnError: true)

        }catch (Exception e){

            e.printStackTrace()

            def respuesta = [status:0]
            render respuesta as JSON
        }

        def respuesta = [status:1]
        render respuesta as JSON
    }

    @Transactional(readOnly = true)
    def delete(){

    }
}
