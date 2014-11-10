package com.freeAppListing.register

import com.freeAppListing.company.Company
import com.freeAppListing.userDetails.UserDetails
import com.freeAppListing.sprinSecurity.auth.SecRole
import com.freeAppListing.sprinSecurity.auth.SecUserSecRole
import grails.converters.JSON
import grails.plugin.springsecurity.annotation.Secured
import grails.transaction.Transactional

@Secured('permitAll')
class RegisterController {

    static allowedMethods = [save:"POST",validationEmail:"POST",validationUsername:"POST"]

    /**
     * Carga la pagina inicial
     */
    def index() {

    }

    /**
     * Valida si el email existe
     * @return
     */
    @Transactional(readOnly = true)
    def validationEmail(){
        def respuesta;
        String email = request.JSON.email

        if(email==null || email ==""){
            respuesta = [status: 0]
            render respuesta as JSON
        }

        def userEmailExists = UserDetails.findAllWhere(email: email)

        boolean emailExists = false

        if(!userEmailExists.empty){
            emailExists = true
        }

        String existsMessage
        if(emailExists){
            existsMessage = "The user that you want register with Email: $email, already exists."
            respuesta = [status: 0, messaje:existsMessage]
            render respuesta as JSON
        }
        respuesta = [status: 1]
        render respuesta as JSON
    }

    /**
     * Valida si el username existe
     * @return
     */
    @Transactional(readOnly = true)
    def validationUsername(){
        def respuesta;
        String username = request.JSON.username

        if(username==null || username ==""){
            respuesta = [status: 0]
            render respuesta as JSON
        }

        def userUserNameExists = UserDetails.findAllWhere(username: username)

        boolean userNameExist

        if(!userUserNameExists.empty){
            userNameExist = true
        }

        String existsMessage

        if(userNameExist){
            existsMessage = "The user that you want register with Username: $username, already exists."
            respuesta = [status: 0, messaje:existsMessage]
            render respuesta as JSON
        }
        respuesta = [status: 1]
        render respuesta as JSON
    }

    /**
     * Guarda si el usuario no existe
     * @return
     */
    @Transactional
    def save(){
        def respuesta;
        def dataUserDetails = request.JSON.userDetails
        def dataCompanyDetail = request.JSON.companyDetails

        try{
            def userAdmin = new UserDetails(
                    fullName: dataUserDetails.userFullName,
                    email: dataUserDetails.email,
                    position: dataUserDetails.position,
                    username: dataUserDetails.userName,
                    password: dataUserDetails.password,
                    enabled: true,
                    accountExpired: false,
                    accountLocked: false,
                    passwordExpired: false,
                    company: new Company(
                            name: dataCompanyDetail.companyName ,
                            address:  dataCompanyDetail.companyAddres,
                            location:  dataCompanyDetail.location,
                            vatNumber: dataCompanyDetail.vatNumber ,
                            webPage:  dataCompanyDetail.webPage
                    )
            ).save(failOnError: true)

            def roleAdmin = SecRole.findOrSaveWhere(authority: "ROLE_ADMIN")

            SecUserSecRole.create(userAdmin, roleAdmin, true)

        }catch (Exception e){
                e.printStackTrace();
                respuesta = [status: 1, messaje:"Error to save information, Try again. "]
                render respuesta as JSON
        }
        respuesta = [status: 1, messaje:"The register is complete successfully, Thanks."]
        render respuesta as JSON
    }

}
