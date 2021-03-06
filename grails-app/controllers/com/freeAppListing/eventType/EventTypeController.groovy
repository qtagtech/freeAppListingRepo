package com.freeAppListing.eventType

import grails.converters.JSON
import grails.plugin.springsecurity.annotation.Secured
import grails.transaction.Transactional

@Secured(['ROLE_SUPERADMIN'])
class EventTypeController {

    static allowedMethods = [save: "POST"]

    @Transactional
    def save() {

        def newEventType
        def respuesta

        try {

            newEventType = new EventType(
                    name: params.name,
                    description: params.description,
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

            def eventType = EventType.findById(value)
            eventType.delete()
        }

        respuesta = [status:1]
        render respuesta as JSON

    }
}
