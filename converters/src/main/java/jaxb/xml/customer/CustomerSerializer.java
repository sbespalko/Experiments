package jaxb.xml.customer;

import com.google.common.collect.Lists;
import entity.customer.Customer;
import java.io.File;
import java.io.IOException;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

public class CustomerSerializer {
  public static void main(String[] args) throws IOException, JAXBException {
    Customer customer = new Customer();
    customer.setId(100);
    customer.setName("mkyong");
    customer.setAge(29);
    customer.setCarsMarks(Lists.newArrayList("audi", "bmw", "zaz"));
    customer.setValue(5.5D);

    File file = new File("file.xml");
    if (file.exists()) {
      file.delete();
    }
    file.createNewFile();
    JAXBContext jaxbContext = JAXBContext.newInstance(Customer.class);
    Marshaller jaxbMarshaller = jaxbContext.createMarshaller();

    // output pretty printed
    jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);

    jaxbMarshaller.marshal(customer, file);
    jaxbMarshaller.marshal(customer, System.out);
  }
}

class CustomerDeserializer {
  public static void main(String[] args) throws IOException, JAXBException {
    File file = new File("file.xml");
    JAXBContext jaxbContext = JAXBContext.newInstance(Customer.class);
    Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
    Customer customer = (Customer) jaxbUnmarshaller.unmarshal(file);
    System.out.println(customer);
  }
}