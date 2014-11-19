import com.freeAppListing.company.Company
import com.freeAppListing.sprinSecurity.auth.SecRole
import com.freeAppListing.sprinSecurity.auth.SecUser
import com.freeAppListing.sprinSecurity.auth.SecUserSecRole
import com.freeAppListing.userDetails.UserDetails

class BootStrap {

    def springSecurityService

    def init = { servletContext ->

        def existSuperUser = UserDetails.findAllWhere(username: "Superadmin" )

        if(existSuperUser.empty){
            def userAdmin = new UserDetails(
                    fullName: "Administrador",
                    email: "admin@freeAppListing.com",
                    position: "Manager",
                    username: "Superadmin",
                    password: "123456",
                    enabled: true,
                    accountExpired: false,
                    accountLocked: false,
                    passwordExpired: false
            ).save(failOnError: true)

            def company =  new Company(
                    name: "FreeAppListing" ,
                    address:  "Disabled",
                    location:  "Disabled",
                    vatNumber: "Disabled" ,
                    webPage:  "Disabled"
            )

            userAdmin.addToCompany(company)

            userAdmin.save(flush: true)

            def roleAdmin = SecRole.findOrSaveWhere(authority: "ROLE_SUPERADMIN")

            SecUserSecRole.create(userAdmin, roleAdmin, true)
        }

    }
    def destroy = {
    }
}
