package com.mycompany.myapp

class FooController {
    FooService fooService

    def doSomething() {
        render fooService.doSomething("Sally").toString()
    }
}
