package com.ekaqu.guice.example.billing.mocks;

import com.ekaqu.guice.example.billing.ChargeResult;
import com.ekaqu.guice.example.billing.TransactionLog;

public class InMemoryTransactionLog implements TransactionLog {
    public void logChargeResult(ChargeResult result) {
        System.out.println("Result is successful : " + result.isSuccessful());
    }

    public void logConnectException(Exception e) {
        System.out.println("Exception thrown: " + e);
    }
}
