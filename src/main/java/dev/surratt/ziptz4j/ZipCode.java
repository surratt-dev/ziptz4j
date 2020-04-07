package dev.surratt.ziptz4j;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.Properties;

public class ZipCode {

  private static final Properties zipCodeToTZ = new Properties();

  static {

    final ClassLoader classLoader = ZipCode.class.getClassLoader();
    final String name = "tz.data";
    final URL url = classLoader.getResource(name);

    if (url == null) {
      throw new IllegalStateException("Could not locate file");
    }

    final InputStream stream = classLoader.getResourceAsStream(name);

    try {
      zipCodeToTZ.load(stream);
    } catch (IOException e) {
      e.printStackTrace();
      zipCodeToTZ.clear();
    }

  }

  private final String value;

  /**
   * Return an instance of the ZipCode class for the specified zip code value.
   *
   * @param value a zip code String matching the patterns #####, ######## or #####-####
   */
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

  public String getTimeZoneID() {

    String timeZoneId = zipCodeToTZ.getProperty(getCode());

    if (timeZoneId == null) {
      timeZoneId = "Unknown";
    }

    return timeZoneId;
  }

}
