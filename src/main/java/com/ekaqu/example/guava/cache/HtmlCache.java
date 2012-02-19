package com.ekaqu.example.guava.cache;

import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;
import com.google.common.io.CharStreams;

import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.concurrent.ExecutionException;

public class HtmlCache {
  private final LoadingCache<String, String> httpCache;

  public HtmlCache() {
    this.httpCache = CacheBuilder.newBuilder().build(new CacheLoader<String, String>() {
      @Override
      public String load(final String key) throws Exception {
        URL url = new URL(key);
        URLConnection connection = url.openConnection();

        return CharStreams.toString(new InputStreamReader(connection.getInputStream()));
      }
    });
  }

  public String getSite(String url) throws ExecutionException {
    return httpCache.get(url);
  }
}
