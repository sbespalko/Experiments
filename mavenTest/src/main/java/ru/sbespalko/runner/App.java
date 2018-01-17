package ru.sbespalko.runner;

import java.awt.*;
import javax.swing.*;
import jaxb.xml.customer.CustomerSerializer;
import ru.sbespalko.AppShower;
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
    AppShower shower = new AppShower();
    shower.showNumber();
  }
}

class HelloWorld extends JFrame {
  public static void main(String[] args) {
    new HelloWorld();
  }
  HelloWorld() {
    setTitle(sayHello());
    setDefaultCloseOperation(EXIT_ON_CLOSE);
    setBounds(200, 200, 300, 200);
    JLabel label = new JLabel(sayHello(), SwingConstants.CENTER);
    label.setFont(new Font("", Font.BOLD, 24));
    add(label);
    setVisible(true);
  }
  String sayHello() {
    return "Hello, World!";
  }
}
