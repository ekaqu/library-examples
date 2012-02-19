package com.ekaqu.guice.example.billing;

import java.util.Date;

public interface BillingServiceFactory {
    BillingService create(Date date);
}
