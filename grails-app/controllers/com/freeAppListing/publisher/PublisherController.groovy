package com.freeAppListing.publisher

import grails.converters.JSON
import grails.plugin.springsecurity.annotation.Secured
import grails.transaction.Transactional

@Secured(['ROLE_SUPERADMIN'])
class PublisherController {

    static allowedMethods = [save: "POST"]

    @Transactional
    def save(){

        def newPublisher
        def respuesta
        try {
            newPublisher = new Publisher(
                    name: params.name,
                    key: params.key,
                    urlweb: params.web,
                    dateCreate: new Date()
            ).save(failOnError: true)

        }catch (Exception e){
            e.printStackTrace()
            respuesta = [status:0]
            render respuesta as JSON
        }
        respuesta = [status:1]
        render respuesta as JSON
    }

}
