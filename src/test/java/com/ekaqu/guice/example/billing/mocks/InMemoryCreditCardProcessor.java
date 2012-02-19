package com.ekaqu.guice.example.billing.mocks;

import com.ekaqu.guice.example.billing.ChargeResult;
import com.ekaqu.guice.example.billing.CreditCard;
import com.ekaqu.guice.example.billing.CreditCardProcessor;
import com.ekaqu.guice.example.billing.Order;

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
