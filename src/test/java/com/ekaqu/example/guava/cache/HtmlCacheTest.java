package com.ekaqu.example.guava.cache;

import com.google.common.base.Stopwatch;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertNotNull;

public class HtmlCacheTest {
  private static final Log LOG = LogFactory.getLog(HtmlCacheTest.class.getName());

  @Test
  public void testGetSite() throws Exception {
    HtmlCache cache = new HtmlCache();
    Stopwatch stopwatch = new Stopwatch().start();
    String content = cache.getSite("http://www.yahoo.com");
    stopwatch.stop();
    assertNotNull(content);

    if(LOG.isDebugEnabled()) {
      LOG.debug("Site : " + content);
    }

    LOG.info("Conntected in ("+ stopwatch.elapsedMillis()+") millies");

    // retry to show its called
    stopwatch = new Stopwatch().start();
    assertEquals(cache.getSite("http://www.yahoo.com"), content);
    stopwatch.stop();
    LOG.info("Conntected in ("+ stopwatch.elapsedMillis()+") millies");
    assertEquals(stopwatch.elapsedMillis(), 0);
  }
}
