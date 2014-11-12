package com.freeAppListing.application

import org.bson.types.ObjectId

class Application {

    static mapWith = "mongo"

    ObjectId id
    String nombre
    String description
    String keywords
    

    static constraints = {
    }
}
