package com.ekaqu.guice.example.billing;

public interface CreditCardProcessor {
    ChargeResult charge(CreditCard card, Order order);
}
