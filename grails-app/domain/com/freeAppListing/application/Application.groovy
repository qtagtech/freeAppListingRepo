package com.freeAppListing.application

import com.freeAppListing.company.Company
import com.freeAppListing.link.Link
import org.bson.types.ObjectId

class Application {

    static mapWith = "mongo"

    ObjectId id
    String nombre
    String description
    String keywords
    Company company
    static hasMany = [link:Link]

    static constraints = {
    }
}
