package com.ekaqu.example.guice.billing.mocks;

import com.ekaqu.example.guice.billing.ChargeResult;
import com.ekaqu.example.guice.billing.CreditCard;
import com.ekaqu.example.guice.billing.CreditCardProcessor;
import com.ekaqu.example.guice.billing.Order;

public class InMemoryCreditCardProcessor implements CreditCardProcessor {
    public ChargeResult charge(CreditCard card, Order order) {
        System.out.println("Called Charge");
        return new ChargeResult() {
            public boolean isSuccessful() {
                return true;
            }
        };
    }
}
