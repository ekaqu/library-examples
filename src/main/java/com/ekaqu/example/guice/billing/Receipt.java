package com.ekaqu.example.guice.billing;

public interface Receipt {
    void forSuccessfulCharge(Order order);
    void forDeclinedCharge(ChargeResult result);
    void forSystemFailure(Exception e);
}
