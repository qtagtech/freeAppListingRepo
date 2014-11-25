package com.freeAppListing

import com.mongodb.BasicDBObject
import grails.transaction.Transactional


class UtilsService {
    def mongo
    def grailsApplication

    @Transactional(readOnly = true)
    def Long randomNumber (BasicDBObject entity){
        def db = mongo.getDB(grailsApplication.config.com.freeAppListing.database)

        def number =  (long) Math.floor(Math.random() * 900000000000L) + 100000000000L
        if(db.entity.findOne(new BasicDBObject("externalId",number))){
            return 0L
        }
        return number
    }

}
