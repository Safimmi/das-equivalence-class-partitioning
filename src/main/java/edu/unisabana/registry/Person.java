package edu.unisabana.registry;

public class Person {

  private int id;
  private String name;
  private int age;
  private Gender gender;
  private boolean isAlive;

  public Person() {
    super();
  }
  
  public Person(int id, String name, int age, Gender gender, boolean isAlive) {
    this.id = id;
    this.name = name;
    this.age = age;
    this.gender = gender;
    this.isAlive = isAlive;
  }

  public int getId() {
    return id;
  }
  public String getName() {
    return name;
  }
  public int getAge() {
    return age;
  }
  public Gender getGender() {
    return gender;
  }
  public boolean isAlive() {
    return isAlive;
  }

  public void setName(String name) {
    this.name = name;
  }
  public void setId(int id) {
    this.id = id;
  }
  public void setAge(int age) {
    this.age = age;
  }
  public void setGender(Gender gender) {
    this.gender = gender;
  }
  public void setAlive(boolean isAlive) {
    this.isAlive = isAlive;
  }

  @Override
  public String toString() {
    return "Person [id=" + id + ", name=" + name + ", age=" + age + ", gender=" + gender + ", isAlive=" + isAlive + "]"; 
  }
}
