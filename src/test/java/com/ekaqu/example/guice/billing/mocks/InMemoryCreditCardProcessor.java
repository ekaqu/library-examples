package com.ekaqu.example.guice.billing.mocks;

import com.ekaqu.example.guice.billing.ChargeResult;
import com.ekaqu.example.guice.billing.CreditCard;
import com.ekaqu.example.guice.billing.CreditCardProcessor;
import com.ekaqu.example.guice.billing.Order;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class InMemoryCreditCardProcessor implements CreditCardProcessor {
  private static final Logger LOG = LoggerFactory.getLogger(InMemoryCreditCardProcessor.class);
  
    public ChargeResult charge(CreditCard card, Order order) {
        LOG.info("Called Charge");
        return new ChargeResult() {
            public boolean isSuccessful() {
                return true;
            }
        };
    }
}
