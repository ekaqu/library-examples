package com.ekaqu.example.aspectj;

import com.google.common.cache.Cache;

public aspect CacheListener {

//    pointcut cache() :
//        call(* com.google.common.cache.Cache.*(..));

//    before() : cache() {
//        System.out.println("Cache Called");
//    }

//    before() : call(* Cache.*(..)) {
//        System.out.println("Cache Called: ");
//    }

    Object around(String key) : call(* Cache.getIfPresent(*)) && args(key) {
        System.out.println("Key " + key);

        return "-aop";
    }
}