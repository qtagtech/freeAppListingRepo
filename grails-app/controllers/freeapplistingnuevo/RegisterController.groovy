package freeapplistingnuevo

import com.freeAppListing.sprinSecurity.auth.SecUser
import grails.plugin.springsecurity.annotation.Secured

@Secured('permitAll')
class RegisterController {

    def index() {

    }

    def save(){
        new SecUser()
    }

}
