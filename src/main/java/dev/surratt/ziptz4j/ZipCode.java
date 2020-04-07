package dev.surratt.ziptz4j;

import java.util.regex.Pattern;

public class ZipCode {

  private final String value;

  public static ZipCode getZipCode(final String value) {
    if (!isValid(value)) {
      throw new IllegalArgumentException("Invalid zip code [" + value + "]");
    }
    return new ZipCode(value);
  }

  public ZipCode(final String value) {
    this.value = value;
  }

  public static boolean isValid(final String zipCode) {

    if (zipCode == null) {
      return false;
    }

    return zipCode.matches("^\\d{5}(-?\\d{4})?$");

  }

  @Override
  public String toString() {
    return value;
  }

  /**
   * Returns the 5 digit zip code, even if more than 5 digits were provided.
   */
  public String getCode() {
    return value.substring(0, 5);
  }

  public String getValue() {
    return value;
  }

  public boolean isExtended() {
    return value.length() != 5;
  }

}
