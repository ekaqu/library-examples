package com.ekaqu.example.guice.billing.mocks;

import com.ekaqu.example.guice.billing.ChargeResult;
import com.ekaqu.example.guice.billing.Order;
import com.ekaqu.example.guice.billing.Receipt;

public class InMemoryReceipt implements Receipt {
    public void forSuccessfulCharge(Order order) {
        System.out.println("Order " + order);
    }

    public void forDeclinedCharge(ChargeResult result) {
        System.out.println("Declined Charge " + result);
    }

    public void forSystemFailure(Exception e) {
        System.err.println("Exception " + e);
    }
}
