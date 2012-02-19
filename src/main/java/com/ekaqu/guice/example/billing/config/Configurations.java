package com.ekaqu.guice.example.billing.config;

import com.google.inject.Binder;
import com.google.inject.Key;
import com.google.inject.name.Names;
import org.apache.commons.configuration.Configuration;

import java.util.Iterator;

public class Configurations {
  /**
   * Creates a constant binding to {@code @Named(key)} for each property
   */
  public static void bindProperties(Binder binder, Configuration config) {
    binder = binder.skipSources(Configurations.class);

    // use enumeration to include the default properties
    Iterator<String> keys = config.getKeys();
    while (keys.hasNext()) {
      String key = keys.next();
      String value = config.getString(key);
      binder.bind(Key.get(String.class, Names.named(key))).toInstance(value);
    }
  }
}
