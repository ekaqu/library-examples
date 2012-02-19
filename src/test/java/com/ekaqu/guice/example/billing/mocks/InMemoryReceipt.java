package com.ekaqu.guice.example.billing.mocks;

import com.ekaqu.guice.example.billing.ChargeResult;
import com.ekaqu.guice.example.billing.Order;
import com.ekaqu.guice.example.billing.Receipt;

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
