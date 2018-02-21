package ru.sbespalko;

/**
 * @author bespalko
 * @since 17.01.2018
 */
public class FirstClass {

  public void doWork() {
    System.out.println(this.getClass() + ".doWork()");
    SecondClass secondClass = new SecondClass();
    secondClass.doWork(10);
  }
}
