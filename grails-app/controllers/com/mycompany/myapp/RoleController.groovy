package com.mycompany.myapp

import grails.plugin.springsecurity.SpringSecurityService
import org.springframework.dao.DataIntegrityViolationException

class RoleController {

    SpringSecurityService springSecurityService

    def update(Role role) {
        if (!springSecurityService.updateRole(role, params)) {
            render "Role NOT updated"
            return
        }

        render "Role updated"
    }

    def delete(Role role) {
        try {
            springSecurityService.deleteRole role
            flash.message = "The role was deleted"
            redirect action: list
        }
        catch (DataIntegrityViolationException e) {
            flash.message = "Unable to delete the role"
            redirect action: show, id: params.id
        }
    }
}
