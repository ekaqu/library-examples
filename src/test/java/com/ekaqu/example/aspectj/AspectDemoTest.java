package com.ekaqu.example.aspectj;

import com.google.common.collect.Lists;
import org.testng.annotations.Test;

import java.util.List;

/**
 * Date: 2/25/12
 * Time: 1:51 PM
 */
public class AspectDemoTest {
  @Test
  public void testMethod1() throws Exception {
    AspectDemo demo = new AspectDemo();
    demo.method1();
  }
  
  @Test
  public void listAdd() {
    List<String> list = Lists.newArrayList();
    list.add("hello there");
  }
}
