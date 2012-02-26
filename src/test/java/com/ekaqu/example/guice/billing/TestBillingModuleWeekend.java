package com.ekaqu.example.guice.billing;

import com.ekaqu.example.guice.billing.annotation.NotOnWeekends;
import com.ekaqu.example.guice.billing.annotation.WeekendBlocker;
import com.ekaqu.example.guice.billing.config.Configurations;
import com.ekaqu.example.guice.billing.impl.RealBillingService;
import com.ekaqu.example.guice.billing.mocks.InMemoryCreditCardProcessor;
import com.ekaqu.example.guice.billing.mocks.InMemoryReceipt;
import com.ekaqu.example.guice.billing.mocks.InMemoryTransactionLog;
import com.google.common.base.Throwables;
import com.google.inject.AbstractModule;
import com.google.inject.assistedinject.FactoryModuleBuilder;
import com.google.inject.matcher.Matchers;
import com.google.inject.multibindings.Multibinder;
import org.apache.commons.configuration.CombinedConfiguration;
import org.apache.commons.configuration.ConfigurationException;
import org.apache.commons.configuration.PropertiesConfiguration;
import org.apache.commons.configuration.SystemConfiguration;

import java.io.PrintStream;
import java.util.Calendar;

/**
 * Date: 2/26/12
 * Time: 10:04 AM
 */
public class TestBillingModuleWeekend extends AbstractModule {
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
    calendar.set(Calendar.DAY_OF_WEEK, Calendar.SUNDAY);
    bind(Calendar.class).toInstance(calendar);

    bindInterceptor(Matchers.any(), Matchers.annotatedWith(NotOnWeekends.class),
      new WeekendBlocker(getProvider(Calendar.class)));
  }
}
