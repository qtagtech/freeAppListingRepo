package com.freeAppListing.publisher

import org.bson.types.ObjectId

class Publisher {

    static mapWith = "mongo"

    ObjectId id
    Long externalId
    String name
    String key
    String urlweb
    Date dateCreate
    Date dateUpdate

    static constraints = {
        urlweb url: true
    }
}
