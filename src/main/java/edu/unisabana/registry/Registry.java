package edu.unisabana.registry;

import java.util.HashMap;
import java.util.Map;

public class Registry {

  private static final int MIN_ALLOWED_AGE = 18;
  private static final int MAX_ALLOWED_AGE = 150;
  private static final int[] ID_ALLOWED_SIZE = {8, 10};

  private Map<Integer, Person> records;

  public Registry() {
    this.records = new HashMap<>();
  }

  public RegisterResult registerVoter(Person person) {
    RegisterResult result = null;

    boolean isAlive = person.isAlive();
    int age = person.getAge();
    int id = person.getId();
    String name = person.getName();
    Gender gender = person.getGender();

    if(!isAlive){
      result = RegisterResult.DEAD;
    }

    if (!(age >= MIN_ALLOWED_AGE)) {
      result = RegisterResult.UNDERAGE;
    }
    if(age > MAX_ALLOWED_AGE) {
      result = RegisterResult.INVALID_AGE;
    }

    if(!Utils.isAllowedSize(id , ID_ALLOWED_SIZE)) {
      result = RegisterResult.INVALID_ID;
    }
    if(isRegistered(id)){
      result = RegisterResult.DUPLICATED;
    }

    if(name == null) {
      throw new IllegalArgumentException("Name can not be empty");
    }
    if(!Utils.isValidName(name)) {
      result = RegisterResult.INVALID_NAME;
    }

    if(gender == null) {
      throw new IllegalArgumentException("Gender can not be empty");
    }

    if(result == null) {
      records.put(person.getId(), person);
      result = RegisterResult.VALID;
    }

    return result;
  }

  public Boolean isRegistered(int id) {
    return records.get(id) != null;
  }

  public void clearAllRecords() {
    records.clear();
  }
}
