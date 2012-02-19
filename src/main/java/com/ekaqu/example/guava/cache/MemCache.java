package com.ekaqu.example.guava.cache;


import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;

public class MemCache {
  private final Cache<String, String> cache;

  public MemCache() {
    cache = CacheBuilder.newBuilder().<String, String>build();
  }

  public String getIfPresent(String key) {
    return cache.getIfPresent(key);
  }

  public void cache(String key, String object) {
    cache.put(key, object);
  }

}
