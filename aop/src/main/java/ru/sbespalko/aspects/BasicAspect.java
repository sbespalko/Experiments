/* BasicAspect.java */
package ru.sbespalko.aspects;

import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;

@Aspect
public class BasicAspect {

  /* Read as -- do this *before* any *call* to the function
         * *java.io.PrintStream.println* that takes a *String*
         * and returns *void* and the function is not called
   * within any class under the package net.andrewewhite.aspects
   */
  @Before("execution(* ru.sbespalko.*.doWork(..))")
  public void beforePrintlnCall() {
    System.out.println("About to make call to print Hello World");
  }

  @After("execution(* ru.sbespalko.*.doWork(..))")
  public void afterPrintlnCall() {
    System.out.println("Just made call to print Hello World");
  }
}