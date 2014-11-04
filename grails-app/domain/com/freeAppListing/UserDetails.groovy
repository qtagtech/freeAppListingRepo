package com.freeAppListing

import com.freeAppListing.sprinSecurity.auth.SecUser

class UserDetails extends SecUser {

    String fullName
    String email
    String position

    static constraints = {
        email nullable: false, blank: false,unique: true
    }

    static mapping = {
        datasource "trans"
    }
}
