package com.mycompany.myapp

import grails.testing.web.controllers.ControllerUnitTest
import spock.lang.Specification

class SpySpec  extends Specification implements ControllerUnitTest<FooController> {

    void "Spy FooService"() { // requires implementation of DataTest trait to avoid GORM errors
        given: "the collaborating service is a Spy"
        def fooService = Spy(FooService)

        and: "the Spy service is set on the controller"
        controller.fooService = fooService

        when: "the controller action is called"
        controller.doSomething()

        then: "the Spy can be used to validate cardinality"
        1 * fooService.doSomething("Sally")  >> "A Spy can modify implementation"

        and: 'A spy can modify implementation'
        response.text == "A Spy can modify implementation"
    }
}
