package com.mycompany.myapp

import groovy.transform.CompileStatic

@CompileStatic
class FooController {
    FooService fooService

    def doSomething() {
        render fooService.doSomething("Sally")
    }
}
