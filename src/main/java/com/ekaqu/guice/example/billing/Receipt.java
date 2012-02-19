package com.ekaqu.guice.example.billing;

public interface Receipt {
    void forSuccessfulCharge(Order order);
    void forDeclinedCharge(ChargeResult result);
    void forSystemFailure(Exception e);
}
