package dev.surratt.ziptz4j;

public class ZipCode {

  private final String value;

  public static ZipCode getZipCode(final String value) {
    return new ZipCode(value);
  }

  public ZipCode(final String value) {
    this.value = value;
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
