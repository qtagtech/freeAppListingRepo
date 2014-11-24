package com.freeAppListing.conversion

class ConversionController {

    static defaultAction = "index"

    def index() {

        redirect(controller: "login")
        return
    }
}
