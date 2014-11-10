package com.freeAppListing.register

import com.freeAppListing.company.Company
import com.freeAppListing.userDetails.UserDetails
import com.freeAppListing.sprinSecurity.auth.SecRole
import com.freeAppListing.sprinSecurity.auth.SecUser
import com.freeAppListing.sprinSecurity.auth.SecUserSecRole
import grails.converters.JSON
import grails.plugin.springsecurity.annotation.Secured
import grails.transaction.Transactional

@Secured('permitAll')
class RegisterController {

    static allowedMethods = [save:"POST"]

    def index() {

    }

    @Transactional
    def save(){
        def respuesta;
        def dataUserDetails = request.JSON.userDetails
        def dataCompanyDetail = request.JSON.companyDetails

        try{
            def userAdmin = SecUser.findOrSaveWhere(
                    username: dataUserDetails.userName,
                    password: dataUserDetails.password,
                    enabled: true,
                    accountLocked: false,
                    accountExpired: false,
                    passwordExpired: false,
                    fullName: dataUserDetails.userFullName,
                    email: dataUserDetails.email,
                    position: dataUserDetails.position,
                    companyName: dataCompanyDetail.companyName ,
                    companyAddress: dataCompanyDetail.companyAddres,
                    location: dataCompanyDetail.location,
                    vatNumber: dataCompanyDetail.vatNumber ,
                    webPage: dataCompanyDetail.webPage
            ).save(failOnError: true)

            println(userAdmin.errors.allErrors)

            def roleAdmin = SecRole.findOrSaveWhere(authority: "ROLE_ADMIN")

            SecUserSecRole.create(userAdmin, roleAdmin, true)

        }catch (Exception e){
                e.printStackTrace();
                respuesta = [status: 1, messaje:"Error to save information, Try again. "]
                render respuesta as JSON
        }
        respuesta = [status: 1, messaje:"Now you are register, Thanks. "]
        render respuesta as JSON
    }

}
