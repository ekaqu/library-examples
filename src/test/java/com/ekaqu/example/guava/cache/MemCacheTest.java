package com.ekaqu.example.guava.cache;

import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertNull;

public class MemCacheTest {

  @Test
  public void test() {
    MemCache cache = new MemCache();
    String value = cache.getIfPresent("notthere");
    assertNull(value, "Cache should be empty");

    cache.cache("notthere", "yes i am");
    value = cache.getIfPresent("notthere");
    assertEquals(value, "yes i am");
  }
}
