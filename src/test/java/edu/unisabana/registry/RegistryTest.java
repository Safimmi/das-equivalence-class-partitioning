package edu.unisabana.registry;

import org.junit.Test;

import static org.junit.Assert.assertThrows;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;

public class RegistryTest {

  private Registry registry = new Registry();
  private Person person;

  @Before
  public void arrange() {
    registry = new Registry();
    person = new Person(12345678, "John Doe", 18, Gender.UNIDENTIFIED, true);
  }
  @After
  public void clear() {
    registry.clearAllRecords();
  }
  
  @Test
  public void registerVoter_Valid_isAliveTrue() {
    person.setAlive(true);
    RegisterResult result = registry.registerVoter(person);
    Assert.assertEquals(RegisterResult.VALID, result);
  }
  @Test
  public void registerVoter_Dead_isAliveFalse() {
    person.setAlive(false);
    RegisterResult result = registry.registerVoter(person);
    Assert.assertEquals(RegisterResult.DEAD, result);
  }
  
  @Test
  public void registerVoter_Valid_AgeOver18() {
    person.setAge(20);
    RegisterResult result = registry.registerVoter(person);
    Assert.assertEquals(RegisterResult.VALID, result);
  }
  @Test
  public void registerVoter_Underage_AgeUnder18() {
    person.setAge(5);
    RegisterResult result = registry.registerVoter(person);
    Assert.assertEquals(RegisterResult.UNDERAGE, result);
  }
  @Test
  public void registerVoter_InvalidAge_AgeOverTopLimit() {
    person.setAge(300);
    RegisterResult result = registry.registerVoter(person);
    Assert.assertEquals(RegisterResult.INVALID_AGE, result);
  }

  @Test
  public void registerVoter_Valid_8DigitId() {
    person.setId(12345678);
    RegisterResult result = registry.registerVoter(person);
    Assert.assertEquals(RegisterResult.VALID, result);
  }
  @Test
  public void registerVoter_Valid_10DigitId() {
    person.setId(1234567890);
    RegisterResult result = registry.registerVoter(person);
    Assert.assertEquals(RegisterResult.VALID, result);
  }
  @Test
  public void registerVoter_InvalidId_NDigitId() {
    person.setId(123);
    RegisterResult result = registry.registerVoter(person);
    Assert.assertEquals(RegisterResult.INVALID_ID, result);
  }
  
  @Test
  public void registerVoter_Duplicated_8DigitId() {
    person.setId(12345678);
    registry.registerVoter(person);
    RegisterResult result = registry.registerVoter(person);
    Assert.assertEquals(RegisterResult.DUPLICATED, result);
  }
  @Test
  public void registerVoter_Duplicated_10DigitId() {
    person.setId(1234567890);
    registry.registerVoter(person);
    RegisterResult result = registry.registerVoter(person);
    Assert.assertEquals(RegisterResult.DUPLICATED, result);
  }
  
  @Test
  public void registerVoter_Valid_AlphabeticalName() {
    person.setName("John Doe");
    RegisterResult result = registry.registerVoter(person);
    Assert.assertEquals(RegisterResult.VALID, result);
  }
  @Test
  public void registerVoter_InvalidName_NonAlphabeticalName() {
    person.setName("J3ohn Do@e#");
    RegisterResult result = registry.registerVoter(person);
    Assert.assertEquals(RegisterResult.INVALID_NAME, result);
  }
  @Test
  public void registerVoter_IllegalArgumentException_NullName() {
    person.setName(null);
    assertThrows(IllegalArgumentException.class, () -> {
      registry.registerVoter(person);
    });
  }

  @Test
  public void registerVoter_Valid_AllowedGender() {
    person.setGender(Gender.UNIDENTIFIED);
    RegisterResult result = registry.registerVoter(person);
    Assert.assertEquals(RegisterResult.VALID, result);
  }
  @Test
  public void registerVoter_IllegalArgumentException_NullGender() {
    person.setGender(null);
    assertThrows(IllegalArgumentException.class, () -> {
      registry.registerVoter(person);
    });
  }
}
