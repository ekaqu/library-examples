package com.ekaqu.guice.example.billing.impl;

import com.ekaqu.guice.example.billing.*;
import com.google.inject.Inject;
import com.google.inject.Injector;
import com.google.inject.assistedinject.Assisted;
import com.google.inject.assistedinject.AssistedInject;

import java.util.Date;

public class RealBillingService implements BillingService {
    private final CreditCardProcessor processor;
    private final TransactionLog transactionLog;

    @Inject
    private Injector injector;

    @Inject
    public RealBillingService(CreditCardProcessor processor, TransactionLog transactionLog) {
        this.processor = processor;
        this.transactionLog = transactionLog;
    }

    @AssistedInject
    public RealBillingService(CreditCardProcessor processor, TransactionLog transactionLog, @Assisted Date date) {
        this.processor = processor;
        this.transactionLog = transactionLog;
        System.out.println(date);
    }

    public Receipt chargeOrder(Order order, CreditCard creditCard) {
        Receipt receipt = injector.getInstance(Receipt.class);
        try {
            ChargeResult result = processor.charge(creditCard, order);
            transactionLog.logChargeResult(result);

            if (result.isSuccessful()) {
                receipt.forSuccessfulCharge(order);
            } else {
                receipt.forDeclinedCharge(result);
            }

        } catch (Exception e) {
            transactionLog.logConnectException(e);
            receipt.forSystemFailure(e);
        }
        return receipt;
    }
}
