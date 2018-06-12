package com.mycompany.myapp

import grails.gorm.transactions.Transactional

@Transactional
class FooService {

    String doSomething() {
        return "FooService did something"
    }
}
