package com.mycompany.myapp

import groovy.transform.CompileStatic

@CompileStatic
class FooService {

    String doSomething(String name) {
        "Hi ${name}, FooService did something"
    }
}
