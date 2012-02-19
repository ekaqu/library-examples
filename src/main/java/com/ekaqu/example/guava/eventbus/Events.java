package com.ekaqu.example.guava.eventbus;

import com.google.common.eventbus.AllowConcurrentEvents;
import com.google.common.eventbus.EventBus;
import com.google.common.eventbus.Subscribe;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import javax.inject.Inject;

import static com.google.common.base.Preconditions.checkNotNull;

public class Events {
  private static final Log LOG = LogFactory.getLog(Events.class.getName());
  private final EventBus eventBus;

  @Inject
  public Events(final EventBus eventBus) {
    this.eventBus = checkNotNull(eventBus);
    this.eventBus.register(this);
  }
  public Events() {
    this(new EventBus("#Parent#"));
  }

  public void dispatchEvent(Object event, int count) {
    for(int i = 0; i < count; i++) {
      eventBus.post(event);
    }
  }

  public void listen(Object listener) {
    eventBus.register(listener);
  }

  @AllowConcurrentEvents
  @Subscribe
  public void onString(String event) {
    LOG.info("Event: " + event);
  }
}
