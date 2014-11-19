package campaigns

import com.freeAppListing.application.Application
import com.freeAppListing.platform.Platforms
import com.freeAppListing.publisher.Publisher
import grails.plugin.springsecurity.annotation.Secured

@Secured(['ROLE_USER'])
class CampaignsController {
    def springSecurityService

    def create() {
        if(springSecurityService.isLoggedIn()){
            def userLoggin = springSecurityService.getCurrentUser()

            List<Application> applicationList = Application.list()

            List<Publisher> publisherList = Publisher.list()

            List<Platforms> platformsList = Platforms.list()

            render view: "create", model: [
                    activeMenu:3 ,
                    dataUser:userLoggin,
                    applicationList:applicationList,
                    publisherList: publisherList,
                    platformsList: platformsList
            ]
        }
    }
}
