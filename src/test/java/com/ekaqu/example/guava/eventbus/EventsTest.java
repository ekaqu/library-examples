package com.ekaqu.example.guava.eventbus;

import com.google.common.eventbus.Subscribe;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.testng.annotations.Test;

public class EventsTest {
  private static final Log LOG = LogFactory.getLog(EventsTest.class.getName());

  @Test
  public void test() {
    Events events = new Events();
    events.listen(this);

    events.dispatchEvent("Moo", 5);
    events.dispatchEvent(this.getClass(), 5);
  }

  @Subscribe
  public void onClass(Class clazz) {
    LOG.info(clazz);
  }
}
