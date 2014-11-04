package com.freeAppListing

import com.freeAppListing.sprinSecurity.auth.SecUser

class Company extends SecUser{

    String companyName
    String companyAddress
    String vatNumber
    String webPage
    String location

    static constraints = {
        companyName nullable: false, blank: false
    }
    static mapping = {
        datasource "trans"
    }
}
