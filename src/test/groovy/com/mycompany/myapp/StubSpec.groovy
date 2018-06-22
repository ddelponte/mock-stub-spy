package com.mycompany.myapp

import grails.testing.web.controllers.ControllerUnitTest
import spock.lang.Specification

class StubSpec extends Specification implements ControllerUnitTest<FooController> {

    void "Stub FooService"() {
        given: "the collaborating service is stubbed"
        FooService fooService = Stub(FooService) {
            doSomething(_) >> "Stub did something"
        }

        and: "the stubbed service is set on the controller"
        controller.fooService = fooService

        when: "the controller action is called"
        controller.doSomething()

        then: "the stubbed service returns the stubbed text"
        // 1 * fooService.doSomething() cardinality not supported by Stub
        response.text == "Stub did something"
    }
}
