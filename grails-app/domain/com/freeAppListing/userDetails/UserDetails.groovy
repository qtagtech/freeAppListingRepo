package com.freeAppListing.userDetails

import com.freeAppListing.company.Company
import com.freeAppListing.sprinSecurity.auth.SecUser

class UserDetails extends SecUser {

    String fullName
    String email
    String position
    static hasMany = [company : Company]

    static constraints = {
        email unique: true, nullable: false, blank: false
    }

    static mapping = {
        datasource "trans"
    }
}
