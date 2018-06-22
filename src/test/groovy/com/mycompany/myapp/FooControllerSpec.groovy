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

    void "Spy FooService"() { // requires implementation of DataTest trait to avoid GORM errors
        given: "the collaborating service is a Spy"
        FooService fooService = Spy(FooService)

        and: "the Spy service is set on the controller"
        controller.fooService = fooService

        when: "the controller action is called"
        controller.doSomething()

        then: "the Spy can be used to validate cardinality"
        1 * fooService.doSomething("Sally") // can be in then or given block

        and: "the text from the original service implementation is returned"
        response.text == "Hi Sally, FooService did something"
    }
}
