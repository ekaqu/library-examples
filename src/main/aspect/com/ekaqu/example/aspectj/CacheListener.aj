package com.ekaqu.example.aspectj;

import com.ekaqu.example.aspectj.MockCache;

public aspect CacheListener {

//    pointcut cache() :
//        call(* com.google.common.cache.Cache.*(..));

//    before() : cache() {
//        System.out.println("Cache Called");
//    }

//    before() : call(* Cache.*(..)) {
//        System.out.println("Cache Called: ");
//    }

    String around(String key) : call(* MockCache.get(*)) && args(key) {
        System.out.println("Key : " + key);

        String value = proceed(key);

        System.out.println("Value : " + value);
        return value;
    }
}