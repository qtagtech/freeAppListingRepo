package com.freeAppListing.campaign

import com.freeAppListing.application.Application
import com.freeAppListing.platform.Platforms
import com.freeAppListing.publisher.Publisher
import org.bson.types.ObjectId

class Campaign {

    static mapWith = "mongo"

    ObjectId id
    String name
    Application application
    Publisher publisher
    Platforms platforms
    String trakingUrl
    Long externalId

    static embedded = ['application','publisher','platforms']

    static constraints = {
    }
}
