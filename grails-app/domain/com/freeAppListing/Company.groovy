package com.freeAppListing

class Company {

    String companyName
    String companyAddress
    String vatNumber
    String webPage
    String location

    static constraints = {
        companyName nullable: false, blank: false
    }
    static mapping = {
        datasource "trans"
    }
}
