package com.freeAppListing.platform

import org.bson.types.ObjectId

class Platforms {

    static mapWith = "mongo"

    ObjectId id
    String nombre
    String description
    String web
    String market
    Date dateCreate
    Date dateUpdate

    static constraints = {
        web url: true
    }
}
