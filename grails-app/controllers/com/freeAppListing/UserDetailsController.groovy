package com.freeAppListing



import static org.springframework.http.HttpStatus.*
import grails.transaction.Transactional

@Transactional(readOnly = true)
class UserDetailsController {

    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    def index(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        respond UserDetails.list(params), model:[userDetailsInstanceCount: UserDetails.count()]
    }

    def show(UserDetails userDetailsInstance) {
        respond userDetailsInstance
    }

    def create() {
        respond new UserDetails(params)
    }

    @Transactional
    def save(UserDetails userDetailsInstance) {
        if (userDetailsInstance == null) {
            notFound()
            return
        }

        if (userDetailsInstance.hasErrors()) {
            respond userDetailsInstance.errors, view:'create'
            return
        }

        userDetailsInstance.save flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.created.message', args: [message(code: 'userDetails.label', default: 'UserDetails'), userDetailsInstance.id])
                redirect userDetailsInstance
            }
            '*' { respond userDetailsInstance, [status: CREATED] }
        }
    }

    def edit(UserDetails userDetailsInstance) {
        respond userDetailsInstance
    }

    @Transactional
    def update(UserDetails userDetailsInstance) {
        if (userDetailsInstance == null) {
            notFound()
            return
        }

        if (userDetailsInstance.hasErrors()) {
            respond userDetailsInstance.errors, view:'edit'
            return
        }

        userDetailsInstance.save flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.updated.message', args: [message(code: 'UserDetails.label', default: 'UserDetails'), userDetailsInstance.id])
                redirect userDetailsInstance
            }
            '*'{ respond userDetailsInstance, [status: OK] }
        }
    }

    @Transactional
    def delete(UserDetails userDetailsInstance) {

        if (userDetailsInstance == null) {
            notFound()
            return
        }

        userDetailsInstance.delete flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.deleted.message', args: [message(code: 'UserDetails.label', default: 'UserDetails'), userDetailsInstance.id])
                redirect action:"index", method:"GET"
            }
            '*'{ render status: NO_CONTENT }
        }
    }

    protected void notFound() {
        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'userDetails.label', default: 'UserDetails'), params.id])
                redirect action: "index", method: "GET"
            }
            '*'{ render status: NOT_FOUND }
        }
    }
}
