package com.ekaqu.example.aspectj;

import com.google.common.collect.Maps;

import java.util.Map;

/**
 * Date: 2/25/12
 * Time: 5:59 PM
 */
public class MockCache {
  private Map<String, String> cache = Maps.newHashMap();
  
  public String get(String key) {
    return cache.get(key);
  }
  
  public void set(String key, String value) {
    cache.put(key, value);
  }
}
