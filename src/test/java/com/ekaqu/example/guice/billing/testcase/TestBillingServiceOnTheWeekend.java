package com.ekaqu.example.guice.billing.testcase;

import com.ekaqu.example.guice.billing.BillingService;
import com.ekaqu.example.guice.billing.TestBillingModuleWeekend;
import org.testng.annotations.Guice;
import org.testng.annotations.Test;

import javax.inject.Inject;
import javax.inject.Provider;

/**
 * Date: 2/26/12
 * Time: 10:04 AM
 */
@Guice(modules = TestBillingModuleWeekend.class)
public class TestBillingServiceOnTheWeekend {
  private final Provider<BillingService> provider;

  @Inject
  public TestBillingServiceOnTheWeekend(final Provider<BillingService> provider) {
    this.provider = provider;
  }

  @Test(groups = {"Unit"}, expectedExceptions = IllegalStateException.class)
  public void test() {
    BillingService billingService = provider.get();
    billingService.chargeOrder(null, null);
  }
}
