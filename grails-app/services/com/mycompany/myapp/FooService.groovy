package com.mycompany.myapp

import groovy.transform.CompileStatic

@CompileStatic
class FooService {

    String doSomething(String name) {
        return "Hi ${name}, FooService did something"
    }
}
