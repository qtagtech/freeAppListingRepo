package com.freeAppListing.users

import grails.plugin.springsecurity.annotation.Secured
import grails.transaction.Transactional

@Secured(['ROLE_SUPERADMIN'])
class UserController {

    static allowedMethods = [delete: "POST"]

    @Transactional
    def delete(){
        /**
         * Solo se elimina usuario tipo user
         */

        // Recibir id o ids de USER a eliminar
        // Validar que existan y que no sea usuario manager
        // se valida por company si tiene aplicaciones y campañas
        // Se elimina las app y campañas que tenga el usuario
        // se eliminar la relacion de role y usuario
        // Se elimina el usuario y la compañia
        // Se responde si fue exitoso o no la eliminacion del usuario
    }
}
