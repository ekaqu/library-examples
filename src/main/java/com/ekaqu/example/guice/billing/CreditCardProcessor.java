package com.ekaqu.example.guice.billing;

public interface CreditCardProcessor {
    ChargeResult charge(CreditCard card, Order order);
}
