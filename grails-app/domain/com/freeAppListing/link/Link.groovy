package com.freeAppListing.link

import com.freeAppListing.application.Application
import com.freeAppListing.platform.Platforms
import org.bson.types.ObjectId

class Link {

    static mapWith = "mongo"

    ObjectId id
    String urlDirect
    String urlHasOffer
    Platforms platforms

    static belongsTo = [application: Application]

    static constraints = {
    }
}
