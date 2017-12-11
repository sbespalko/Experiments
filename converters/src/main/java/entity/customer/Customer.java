package entity.customer;

import java.util.List;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Customer {

  private int id;
  private String name;
  private int age;
  private List<String> carsMarks;
  private double value;


  public double getValue() {
    return value;
  }

  @XmlElement
  public void setValue(double value) {
    this.value = value;
  }

  public List<String> getCarsMarks() {
    return carsMarks;
  }

  @XmlElement
  public void setCarsMarks(List<String> cars) {
    this.carsMarks = cars;
  }

  public int getId() {
    return id;
  }

  @XmlElement
  public void setId(int id) {
    this.id = id;
  }

  public String getName() {
    return name;
  }

  @XmlElement
  public void setName(String name) {
    this.name = name;
  }

  public int getAge() {
    return age;
  }

  @XmlElement
  public void setAge(int age) {
    this.age = age;
  }

  @Override
  public String toString() {
    return "Customer{" + "id=" + id + ", name='" + name + '\'' + ", age=" + age + ", carsMarks=" + carsMarks
      + ", value=" + value + '}';
  }
}