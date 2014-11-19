package com.freeAppListing.company

import com.freeAppListing.userDetails.UserDetails

class Company {

    String name
    String address
    String location
    String vatNumber
    String webPage

    static belongsTo = [userDetails: UserDetails]

    static constraints = {
    }

    static mapping = {
        datasource "trans"
    }

}
