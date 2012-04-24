package com.ekaqu.example.guice.billing;

import com.ekaqu.example.guice.billing.annotation.NotOnWeekends;
import com.ekaqu.example.guice.billing.annotation.WeekendBlocker;
import com.ekaqu.example.guice.billing.config.Configurations;
import com.ekaqu.example.guice.billing.impl.RealBillingService;
import com.ekaqu.example.guice.billing.mocks.InMemoryCreditCardProcessor;
import com.ekaqu.example.guice.billing.mocks.InMemoryReceipt;
import com.ekaqu.example.guice.billing.mocks.InMemoryTransactionLog;
import com.google.common.base.Throwables;
import com.google.common.eventbus.EventBus;
import com.google.inject.AbstractModule;
import com.google.inject.TypeLiteral;
import com.google.inject.assistedinject.FactoryModuleBuilder;
import com.google.inject.matcher.Matchers;
import com.google.inject.multibindings.Multibinder;
import com.google.inject.spi.InjectionListener;
import com.google.inject.spi.TypeEncounter;
import com.google.inject.spi.TypeListener;
import org.apache.commons.configuration.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.PrintStream;
import java.util.Calendar;


public class TestBillingModule extends AbstractModule {
  private static final Logger LOG = LoggerFactory.getLogger(TestBillingModule.class);
  
  private final EventBus eventBus = new EventBus("Default EventBus");

  @Override
  protected void configure() {
    bind(BillingService.class).to(RealBillingService.class);
    bind(CreditCardProcessor.class).to(InMemoryCreditCardProcessor.class);
    bind(TransactionLog.class).to(InMemoryTransactionLog.class);
    bind(Receipt.class).to(InMemoryReceipt.class);
    bind(PrintStream.class).toInstance(System.out);

    try {
      CombinedConfiguration conf = new CombinedConfiguration();
      conf.addConfiguration(new PropertiesConfiguration("conf.properties"));
      conf.addConfiguration(new SystemConfiguration());
      Configurations.bindProperties(this.binder(), conf);
    } catch (ConfigurationException e) {
      LOG.error("Unable to process configurations", e);
      Throwables.propagate(e);
    }

    // can build a composite object like this.  have interface -> composite and have
    // composite get the Set<?> injected in
    Multibinder<String> multibinder = Multibinder.newSetBinder(binder(), String.class);
    multibinder.addBinding().toInstance("Foo");
    multibinder.addBinding().toInstance("Bar");

    install(new FactoryModuleBuilder()
      .implement(BillingService.class, RealBillingService.class)
      .build(BillingServiceFactory.class));

    Calendar calendar = Calendar.getInstance();
    calendar.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);
    bind(Calendar.class).toInstance(calendar);

    bindInterceptor(Matchers.any(), Matchers.annotatedWith(NotOnWeekends.class),
      new WeekendBlocker(getProvider(Calendar.class)));


    // make every created object register with eventbus
    LOG.info("Registering event buss {}", eventBus);
    bind(EventBus.class).toInstance(eventBus);
    bindListener(Matchers.any(), new TypeListener() {
      @Override
      public <I> void hear(final TypeLiteral<I> type, final TypeEncounter<I> encounter) {
        encounter.register(new InjectionListener<I>() {
          @Override
          public void afterInjection(final I injectee) {
            if( injectee instanceof String) {
              // don't bother
              return;
            }
            LOG.debug("Registering Object {} with eventbus", injectee);
            eventBus.register(injectee);
          }
        });
      }
    });
  }
}
