package com.mycompany.myapp

import grails.plugin.springsecurity.SpringSecurityService
import grails.testing.gorm.DataTest
import grails.testing.web.controllers.ControllerUnitTest
import org.springframework.http.HttpStatus
import spock.lang.Specification
import spock.lang.Subject

class RoleControllerSpec extends Specification implements ControllerUnitTest<RoleController>, DataTest {
    @Subject adminRole = new Role(authority: 'ROLE_ADMIN').save()

    void setupSpec() {
        mockDomains User, Role, UserRole
    }

    def setup() {
        User user = new User(username: 'me', password: 'password').save()
        UserRole.create user, adminRole

        assert User.count() == 1
        assert adminRole.id
        assert UserRole.count() == 1
    }

    void "update a Role"() {
        given: "springSecurityService is mocked and set on the controller"
        SpringSecurityService springSecurityService = Mock(SpringSecurityService)
        controller.springSecurityService = springSecurityService

        when: "a request is made to update a Role"
        controller.update(adminRole)

        then: "updateRole method is called one time with the correct parameters"
        1 * springSecurityService.updateRole(adminRole, params)
    }

    void "controller handles both successful and failed updates properly"() {
        given: "springSecurityService is stubbed and set on the controller"
        SpringSecurityService springSecurityService = Stub(SpringSecurityService) {
            updateRole(adminRole, params) >> isRoleUpdated
        }
        controller.springSecurityService = springSecurityService

        when: "a request is made to update a Role"
        controller.update(adminRole)

        then: "updateRole method is called one time with the correct parameters"
        response.status == HttpStatus.OK.value()
        response.text == expectedText

        where: "SpringSecurityService responds with either true or false"
        isRoleUpdated | expectedText
        false         | "Role NOT updated"
        true          | "Role updated"
    }

    void "spy"() { // calls actual, underlying class
        given: "springSecurityService is partially mocked with a spy and set on the controller"
        SpringSecurityService springSecurityService = Spy(SpringSecurityService)
        controller.springSecurityService = springSecurityService

        when: "a request is made to update a Role"
        controller.update(adminRole)

        then: "updateRole method is called one time with the correct parameters"
        1 * springSecurityService.updateRole(adminRole, params)
    }
}
