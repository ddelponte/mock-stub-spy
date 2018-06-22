package com.mycompany.myapp

import grails.gorm.transactions.Transactional
import groovy.transform.CompileStatic

@Transactional
@CompileStatic
class FooService {

    String doSomething(String name) {
        return "Hi ${name}, FooService did something"
    }
}
