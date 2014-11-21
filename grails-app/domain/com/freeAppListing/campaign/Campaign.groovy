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
        trakingUrl blank: true, nullable: true
    }

    def beforeValidate(){
        externalId = randomNumber() //it gets a random number, if the number already exists in the database the function is going to return 0 so it will cycle the while until it gets a number different from 0L
        while(externalId == 0L){
            externalId = randomNumber()
        }
    }

    private Long randomNumber (){
        def number =  (long) Math.floor(Math.random() * 900000000000L) + 100000000000L
        if(Publisher.findByExternalId(number)){
            return 0L
        }
        return number
    }
}
