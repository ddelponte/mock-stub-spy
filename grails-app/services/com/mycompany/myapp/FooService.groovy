package com.mycompany.myapp

import grails.gorm.transactions.Transactional

@Transactional
class FooService {

    String doSomething(String name) {
        return "Hi ${name}, FooService did something"
    }
}
