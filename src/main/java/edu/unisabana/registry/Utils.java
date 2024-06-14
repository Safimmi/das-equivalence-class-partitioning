package edu.unisabana.registry;

import java.util.Arrays;

public class Utils {

  private static final String NAME_PATTERN = "^[a-zA-Z\\s]+$";

  static boolean isAllowedSize(int number, int[] allowedSizes) { 
    int length = String.valueOf(number).length();
    return Arrays.stream(allowedSizes).anyMatch(digit -> digit == length);
  }

  static boolean isValidName(String name) {
    return name.matches(NAME_PATTERN);
  }
}
