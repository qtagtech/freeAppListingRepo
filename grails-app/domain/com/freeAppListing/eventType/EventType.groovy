package com.freeAppListing.eventType

import org.bson.types.ObjectId

class EventType {

    static mapWith = "mongo"

    ObjectId id
    String name
    String description
    Date dateCreate
    Date dateUpdate

    static constraints = {
    }
}
