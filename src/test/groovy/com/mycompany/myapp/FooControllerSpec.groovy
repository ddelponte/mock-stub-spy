package com.mycompany.myapp

import grails.testing.gorm.DataTest
import grails.testing.web.controllers.ControllerUnitTest
import spock.lang.Specification

class FooControllerSpec extends Specification implements ControllerUnitTest<FooController>, DataTest {

    def setup() {
    }

    def cleanup() {
    }

    void "exception is thrown if collaborating service is not mocked"() {
        when:
        controller.doSomething()

        then:
        thrown NullPointerException
    }

    void "Mock FooService"() {
        given:
        FooService fooService = Mock(FooService)
        controller.fooService = fooService

        when:
        controller.doSomething()

        then:
        1 * fooService.doSomething() // must be in then block
        response.text == null.toString()
    }

    void "Stub FooService"() {
        given:
        FooService fooService = Stub(FooService) {
            doSomething() >> "Stub did something"
        }
        controller.fooService = fooService

        when:
        controller.doSomething()

        then:
        // 1 * fooService.doSomething() cardinality not supported by Stub
        response.text == "Stub did something"
    }

    void "Spy FooService"() { // requires implementation of DataTest trait to avoid GORM errors
        given:
        FooService fooService = Spy(FooService)
        controller.fooService = fooService

        when:
        controller.doSomething()

        then:
        1 * fooService.doSomething() // can be in then or given block
        response.text == "FooService did something"
    }
}
