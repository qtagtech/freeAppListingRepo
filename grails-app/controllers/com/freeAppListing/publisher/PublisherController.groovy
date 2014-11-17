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

    @Transactional(readOnly = true)
    def delete(){

        def respuesta

        List<String> listId = request.JSON.ids

        for(int i =0;i< listId.size();i++){
            String value = listId[i].toString()

            def publisher = Publisher.findById(value)
            publisher.delete()
        }

        respuesta = [status:1]
        render respuesta as JSON

    }

}
