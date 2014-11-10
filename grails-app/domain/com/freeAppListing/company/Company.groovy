package com.freeAppListing.company

import org.bson.types.ObjectId

class Company {

    String name
    String address
    String location
    String vatNumber
    String webPage

    static constraints = {
    }

    static mapping = {
        datasource "trans"
    }

}
