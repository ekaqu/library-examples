package com.ekaqu.example.guice.billing.annotation;

import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;

import javax.inject.Provider;
import java.util.Calendar;
import java.util.Locale;

/**
 * Date: 2/26/12
 * Time: 9:46 AM
 */
public class WeekendBlocker implements MethodInterceptor {
  private final Provider<Calendar> calendarProvider;

  public WeekendBlocker(final Provider<Calendar> calendarProvider) {
    this.calendarProvider = calendarProvider;
  }

  @Override
  public Object invoke(final MethodInvocation invocation) throws Throwable {
    Calendar today = calendarProvider.get();
    if (today.getDisplayName(Calendar.DAY_OF_WEEK, Calendar.LONG, Locale.ENGLISH).startsWith("S")) {
      throw new IllegalStateException(
        invocation.getMethod().getName() + " not allowed on weekends!");
    }
    return invocation.proceed();
  }
}
