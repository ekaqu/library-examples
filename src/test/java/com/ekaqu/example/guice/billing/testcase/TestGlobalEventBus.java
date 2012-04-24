package com.ekaqu.example.guice.billing.testcase;

import com.ekaqu.example.guice.billing.TestBillingModule;
import com.google.common.eventbus.EventBus;
import com.google.common.eventbus.Subscribe;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.annotations.Guice;
import org.testng.annotations.Test;

import javax.inject.Inject;

import static org.testng.Assert.assertTrue;

/**
 *
 */
@Guice(modules = TestBillingModule.class)
public class TestGlobalEventBus {
  private static final Logger LOG = LoggerFactory.getLogger(TestGlobalEventBus.class);
  
  private final EventBus eventBus;
  private boolean called = false;

  @Inject
  public TestGlobalEventBus(final EventBus eventBus) {
    this.eventBus = eventBus;
    LOG.info("Got EventBus {}", eventBus);
  }

  @Test(groups = "Unit")
  public void test() throws InterruptedException {
    LOG.info("Running test");
    eventBus.post("Hello my friend hello");
    assertTrue(called, "Notify was called");
  }

  /**
   * Must be public else won't get called
   */
  @Subscribe
  public void notify(Object obj) {
    LOG.info("EventObject: {}",obj);
    called = true;
  }
}
