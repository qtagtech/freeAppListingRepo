package com.freeAppListing.panel

import com.freeAppListing.application.Application
import com.freeAppListing.campaign.Campaign
import com.freeAppListing.company.Company
import com.freeAppListing.eventType.EventType
import com.freeAppListing.platform.Platforms
import com.freeAppListing.publisher.Publisher
import com.freeAppListing.sprinSecurity.auth.SecRole
import com.freeAppListing.sprinSecurity.auth.SecUserSecRole
import com.freeAppListing.userDetails.UserDetails
import com.mongodb.BasicDBObject
import grails.plugin.springsecurity.SpringSecurityService
import grails.plugin.springsecurity.annotation.Secured
import org.bson.types.ObjectId

@Secured(['ROLE_SUPERADMIN','ROLE_USER'])
class PanelController {
    def springSecurityService
    def mongo


    def index() {
        def db = mongo.getDB(grailsApplication.config.com.freeAppListing.database)
        if(springSecurityService.isLoggedIn()){

            // Do is roler is superadmin
            sec.ifAllGranted(roles: 'ROLE_SUPERADMIN'){
                def userLoggin = springSecurityService.getCurrentUser()
                //get info platform
                List<Platforms> platformsList = Platforms.list()

                //get info Publisher
                List<Publisher> publisherList = Publisher.list()

                // get infor event Type
                List<EventType> eventTypeList = EventType.list()

                List<UserDetails> userDetailsList = UserDetails.list()

                List<SecRole> roleList = SecRole.list()

                def listUser = []
                for(user in userDetailsList ){
                    def map = [:]
                    for( role in roleList){
                        if(SecUserSecRole.exists(user.id, role.id)){
                            map.put("user",user)
                            map.put("role",role)
                            listUser.add(map)
                        }
                    }
                }

                render view:"index", model:[
                        activeMenu: 1,
                        dataUser:userLoggin,
                        platformsList:platformsList,
                        publisherList: publisherList,
                        eventTypeList: eventTypeList,
                        listUser : listUser
                ]
            }

            // Do if role is user
            sec.ifAllGranted(roles: 'ROLE_USER'){
                def userLoggin = springSecurityService.getCurrentUser()

                /**
                 * List appliction by company
                 */
                def listAllApp = Application.list()
                def listApp = []
                listAllApp.eachWithIndex { Application app, int i ->
                    if(app.company.id== userLoggin.company[0].id){
                        listApp.add(app)
                    }
                }
                def countApp = listApp.size()

                /**
                 * List campaign by company
                 */
                def listAllCampaign = Campaign.list()
                def listCamp = []
                listAllCampaign.eachWithIndex { value, i ->
                    if(value.application.company.id == userLoggin.company[0].id){
                        listCamp.add(value)
                    }
                }
                def countCampaign = listCamp.size()

                /**
                 * Look at all events click
                 */
                def listConversion = []
                for(camp in listCamp){
                    def listMap = [:]
                    def conversion = db.conversion.findOne(new BasicDBObject("campaignId",camp._id))
                    def countConversion = db.conversion.count(new BasicDBObject("campaignId",camp._id))
                    def serve = db.serve.findOne(new BasicDBObject("campaignId",camp._id))
                    def countServe = db.serve.count(new BasicDBObject("campaignId",camp._id))

                    if(!(conversion==null)){
                        listMap.put("conver",conversion)
                        listMap.put("numClick",countConversion)
                        listMap.put("serve",serve)
                        listMap.put("numInstall",countServe)

                        listConversion.add(listMap)
                    }
                }

                def countCamp = listConversion.size()

                /**
                 * Generic list to see in frontend
                 */
                def countPlat = Platforms.count()
                def listPlat = Platforms.list()
                def countPubli = Publisher.count()
                def listPubli = Publisher.list()
                def countEvTp = EventType.count()
                def listEvTp = EventType.list()

                /**
                 * Respond to view
                 */
                render view:"index",  model: [
                        activeMenu: 1,
                        dataUser:userLoggin,
                        countCamp: countCamp,
                        listCamp: listConversion,
                        countApp: countApp,
                        listApp: listApp,
                        countPlat: countPlat,
                        listPlat: listPlat.name,
                        countPub: countPubli,
                        listPub: listPubli.name,
                        countEvTp: countEvTp,
                        listEvTp: listEvTp.name,
                        countCampaign: countCampaign,
                        listCampaign:listCamp
                ]
            }

        }

    }
}
