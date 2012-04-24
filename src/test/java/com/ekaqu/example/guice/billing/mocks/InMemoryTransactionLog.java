package com.ekaqu.example.guice.billing.mocks;

import com.ekaqu.example.guice.billing.ChargeResult;
import com.ekaqu.example.guice.billing.TransactionLog;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class InMemoryTransactionLog implements TransactionLog {
  private static final Logger LOG = LoggerFactory.getLogger(InMemoryTransactionLog.class);
  
    public void logChargeResult(ChargeResult result) {
        LOG.info("Result is successful : {}", result.isSuccessful());
    }

    public void logConnectException(Exception e) {
        LOG.error("Exception thrown: ", e);
    }
}
