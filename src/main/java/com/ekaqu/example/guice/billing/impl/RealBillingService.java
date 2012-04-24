package com.ekaqu.example.guice.billing.impl;

import com.ekaqu.example.guice.billing.*;
import com.ekaqu.example.guice.billing.annotation.NotOnWeekends;
import com.google.inject.Inject;
import com.google.inject.assistedinject.Assisted;
import com.google.inject.assistedinject.AssistedInject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.inject.Provider;
import java.io.PrintStream;
import java.util.Date;

public class RealBillingService implements BillingService {
  private final CreditCardProcessor processor;
  private final TransactionLog transactionLog;
  private final Provider<Receipt> receiptProvider;
  private final PrintStream output;
  private final Date date;

  @Inject
  public RealBillingService(CreditCardProcessor processor,
                            TransactionLog transactionLog,
                            Provider<Receipt> receiptProvider, PrintStream output) {
    this.processor = processor;
    this.transactionLog = transactionLog;
    this.receiptProvider = receiptProvider;
    this.output = output;
    this.date = null;
    output.println("Service created at " + date);
  }

  @AssistedInject
  public RealBillingService(CreditCardProcessor processor,
                            TransactionLog transactionLog,
                            @Assisted Date date,
                            Provider<Receipt> receiptProvider, PrintStream output) {
    this.processor = processor;
    this.transactionLog = transactionLog;
    this.receiptProvider = receiptProvider;
    this.date = date;
    this.output = output;
    output.println("Service created at " + date);
  }

  @NotOnWeekends
  public Receipt chargeOrder(Order order, CreditCard creditCard) {
    Receipt receipt = receiptProvider.get();
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
