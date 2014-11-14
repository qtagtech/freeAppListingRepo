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

    def beforeUpdate() {
        dateUpdate = new Date()
    }

    def beforeValidate(){
        externalId = randomNumber() //it gets a random number, if the number already exists in the database the function is going to return 0 so it will cycle the while until it gets a number different from 0L
        while(externalId == 0L){
            externalId = randomNumber()
        }
        dateUpdate = new Date()
    }

    private Long randomNumber (){
        def number =  (long) Math.floor(Math.random() * 900000000000L) + 100000000000L
        if(Publisher.findByExternalId(number)){
            return 0L
        }
        return number
    }
}
