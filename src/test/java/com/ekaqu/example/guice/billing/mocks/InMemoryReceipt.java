package com.ekaqu.example.guice.billing.mocks;

import com.ekaqu.example.guice.billing.ChargeResult;
import com.ekaqu.example.guice.billing.Order;
import com.ekaqu.example.guice.billing.Receipt;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class InMemoryReceipt implements Receipt {
  private static final Logger LOG = LoggerFactory.getLogger(InMemoryReceipt.class);
  
    public void forSuccessfulCharge(Order order) {
        LOG.info("Order {}", order);
    }

    public void forDeclinedCharge(ChargeResult result) {
        LOG.info("Declined Charge {}", result);
    }

    public void forSystemFailure(Exception e) {
        LOG.error("Exception ", e);
    }
}
