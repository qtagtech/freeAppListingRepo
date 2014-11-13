package com.freeAppListing.platform

import grails.converters.JSON

import static org.springframework.http.HttpStatus.*
import grails.transaction.Transactional

@Transactional(readOnly = true)
class PlatformsController {

    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]


    def list() {
        respond Platforms.list() as JSON
    }

    def create() {
        respond new Platforms(params)
    }

    @Transactional
    def save(Platforms platformsInstance) {
        if (platformsInstance == null) {
            notFound()
            return
        }

        if (platformsInstance.hasErrors()) {
            respond platformsInstance.errors, view:'create'
            return
        }

        platformsInstance.save flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.created.message', args: [message(code: 'platforms.label', default: 'Platforms'), platformsInstance.id])
                redirect platformsInstance
            }
            '*' { respond platformsInstance, [status: CREATED] }
        }
    }

    def edit(Platforms platformsInstance) {
        respond platformsInstance
    }

    @Transactional
    def update(Platforms platformsInstance) {
        if (platformsInstance == null) {
            notFound()
            return
        }

        if (platformsInstance.hasErrors()) {
            respond platformsInstance.errors, view:'edit'
            return
        }

        platformsInstance.save flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.updated.message', args: [message(code: 'Platforms.label', default: 'Platforms'), platformsInstance.id])
                redirect platformsInstance
            }
            '*'{ respond platformsInstance, [status: OK] }
        }
    }

    @Transactional
    def delete(Platforms platformsInstance) {

        if (platformsInstance == null) {
            notFound()
            return
        }

        platformsInstance.delete flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.deleted.message', args: [message(code: 'Platforms.label', default: 'Platforms'), platformsInstance.id])
                redirect action:"index", method:"GET"
            }
            '*'{ render status: NO_CONTENT }
        }
    }

    protected void notFound() {
        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'platforms.label', default: 'Platforms'), params.id])
                redirect action: "index", method: "GET"
            }
            '*'{ render status: NOT_FOUND }
        }
    }
}
