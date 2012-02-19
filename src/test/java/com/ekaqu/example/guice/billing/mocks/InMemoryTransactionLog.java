package com.ekaqu.example.guice.billing.mocks;

import com.ekaqu.example.guice.billing.ChargeResult;
import com.ekaqu.example.guice.billing.TransactionLog;

public class InMemoryTransactionLog implements TransactionLog {
    public void logChargeResult(ChargeResult result) {
        System.out.println("Result is successful : " + result.isSuccessful());
    }

    public void logConnectException(Exception e) {
        System.out.println("Exception thrown: " + e);
    }
}
