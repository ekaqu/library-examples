package com.ekaqu.example.aspectj;

public aspect ListLisener {
    pointcut update() :
        call(void java.util.List.add(*));

    before() : update() {
        System.out.println("Before update");
    }

    after() : update() {
        System.out.println("After update");
    }
}