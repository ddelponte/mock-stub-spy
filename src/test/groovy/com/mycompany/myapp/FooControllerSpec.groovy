package com.mycompany.myapp

import grails.testing.web.controllers.ControllerUnitTest
import spock.lang.Specification

class FooControllerSpec extends Specification implements ControllerUnitTest<FooController> {

    void "exception is thrown if collaborating service is not mocked"() {
        when: "the controller action is called without mocking the collaborating service"
        controller.doSomething()

        then: "a NullPointerException is thrown"
        thrown NullPointerException
    }

}
