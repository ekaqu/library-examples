package com.ekaqu.example.guice.billing;

public interface TransactionLog {
    void logChargeResult(ChargeResult result);
    void logConnectException(Exception e);
}
