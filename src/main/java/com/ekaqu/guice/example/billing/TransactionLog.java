package com.ekaqu.guice.example.billing;

public interface TransactionLog {
    void logChargeResult(ChargeResult result);
    void logConnectException(Exception e);
}
