package ru.sbespalko.runner;

import jaxb.xml.customer.CustomerSerializer;
import ru.sbespalko.InnerClass;

/**
 * @author bespalko
 * @since 15.12.2017
 */
public class App {
  public static void main(String[] args) {
    CustomerSerializer serializer = new CustomerSerializer();
    InnerClass aClass = new InnerClass();
    aClass.showInner();
  }
}
