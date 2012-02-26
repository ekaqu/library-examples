package com.ekaqu.example.aspectj;

import org.testng.Assert;
import org.testng.annotations.Test;

/**
 * Date: 2/25/12
 * Time: 5:54 PM
 */
public class CacheTest {

  @Test
  public void testMockCache() {
    MockCache cache = new MockCache();
    
    String value = cache.get("key");
    Assert.assertNull(value);
    
    cache.set("key", "value");
    
    value = cache.get("key");
    
    Assert.assertEquals(value, "value" + CacheModifier.class.getSimpleName());

    MoodIndicator.Moody.Mood mood = ((MoodIndicator.Moody) cache).getMood();
    Assert.assertEquals(mood, MoodIndicator.Moody.Mood.HAPPY);
  }
}
