package com.ekaqu.example.guice.billing;

import com.ekaqu.example.guice.billing.config.Configurations;
import com.ekaqu.example.guice.billing.impl.RealBillingService;
import com.ekaqu.example.guice.billing.mocks.InMemoryCreditCardProcessor;
import com.ekaqu.example.guice.billing.mocks.InMemoryReceipt;
import com.ekaqu.example.guice.billing.mocks.InMemoryTransactionLog;
import com.google.inject.AbstractModule;
import com.google.inject.assistedinject.FactoryModuleBuilder;
import com.google.inject.multibindings.Multibinder;
import org.apache.commons.configuration.*;

import java.io.PrintStream;


public class TestBillingModule extends AbstractModule {
  @Override
  protected void configure() {
    bind(BillingService.class).to(RealBillingService.class);
    bind(CreditCardProcessor.class).to(InMemoryCreditCardProcessor.class);
    bind(TransactionLog.class).to(InMemoryTransactionLog.class);
    bind(Receipt.class).to(InMemoryReceipt.class);
    bind(PrintStream.class).toInstance(System.out);

//    Properties properties = new Properties();
//    try {
//      properties.load(getClass().getClassLoader().getResourceAsStream("conf.properties"));
//      Names.bindProperties(this.binder(), properties);
//    } catch (IOException e) {
//      e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
//    }

    try {
      CombinedConfiguration conf = new CombinedConfiguration();
      conf.addConfiguration(new PropertiesConfiguration("conf.properties"));
      conf.addConfiguration(new SystemConfiguration());
      Configurations.bindProperties(this.binder(), conf);
    } catch (ConfigurationException e) {
      e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
    }

    // can build a composite object like this.  have interface -> composite and have
    // composite get the Set<?> injected in
    Multibinder<String> multibinder = Multibinder.newSetBinder(binder(), String.class);
    multibinder.addBinding().toInstance("Foo");
    multibinder.addBinding().toInstance("Bar");

    install(new FactoryModuleBuilder()
      .implement(BillingService.class, RealBillingService.class)
      .build(BillingServiceFactory.class));


    // ... override
    // You can not override a binding, it will throw an exception
//        bind(BillingService.class).toInstance(new BillingService() {
//            public Receipt chargeOrder(Order order, CreditCard creditCard) {
//                return null;  //To change body of implemented methods use File | Settings | File Templates.
//            }
//        });
  }
}
