package com.ekaqu.example.guice.billing;

import java.util.Date;

public interface BillingServiceFactory {
    BillingService create(Date date);
}
