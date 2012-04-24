package com.ekaqu.example.guice.billing.testcase;

import com.ekaqu.example.guice.billing.BillingService;
import com.ekaqu.example.guice.billing.BillingServiceFactory;
import com.ekaqu.example.guice.billing.TestBillingModule;
import com.google.common.collect.ImmutableList;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.annotations.Guice;
import org.testng.annotations.Test;

import javax.inject.Inject;
import javax.inject.Named;
import javax.inject.Provider;
import java.util.Date;
import java.util.Set;


@Guice(modules = {TestBillingModule.class})
public class TestBillingService {
  private static final Logger LOG = LoggerFactory.getLogger(TestBillingService.class);
  
  private final Provider<BillingService> provider;
  private final BillingServiceFactory factory;

  @Inject
  public TestBillingService(Provider<BillingService> provider,
                            BillingServiceFactory factory) {
    this.provider = provider;
    this.factory = factory;
  }

  @Inject
  public void setParam(@Named("test.foo") String params) {
    LOG.info("Set Param called: {}", params);
  }

  @Inject
  public void setClasspath(@Named("java.class.path") String params) {
    LOG.info("Classpath\n{}", ImmutableList.copyOf(params.split(":")));
  }

  @Inject
  public void setMultiBindings(Set<String> args) {
    LOG.info("setMultiBindings called with {}", args);
  }

  @Test(groups = {"Unit"})
  public void test() {
    BillingService billingService = provider.get();
    billingService.chargeOrder(null, null);
  }

  @Test(groups = {"Unit"})
  public void test2() {
    BillingService billingService = factory.create(new Date());
    billingService.chargeOrder(null, null);
  }
}
