package com.mycompany.myapp

import grails.testing.web.controllers.ControllerUnitTest
import spock.lang.Specification

class MockSpec extends Specification implements ControllerUnitTest<FooController> {

    void "Mock FooService"() {
        given: "the collaborating service is mocked"
        def fooService = Mock(FooService)

        and: "the mocked service is set on the controller"
        controller.fooService = fooService

        when: "the controller action is called"
        controller.doSomething()

        then: "the Mock can be used to validate cardinality and parameters"
        1 * fooService.doSomething("Sally") // must be in then block

        and: "the mocked service returns the default 'zero value' of 'null'"
        response.text == null.toString()
    }
}
