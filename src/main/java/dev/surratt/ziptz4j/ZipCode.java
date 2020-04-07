package dev.surratt.ziptz4j;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.Properties;

/**
 * <p>
 * Provides a mapping from a zip code the corresponding time zone for US ZIP codes.
 * </p>
 * To use:
 *
 * <blockquote><pre>
 *  String value = "17360-5510";
 *  if (ZipCode.isValid(value)) {
 *    ZipCode zipCode = ZipCode.getZipCode(value);
 *    String timeZoneName = zipCode.getTimeZone();
 *    TimeZone timeZone = TimeZone.getTimeZone(timeZoneName);
 *  }
 *  </pre></blockquote>
 *
 *  With the resulting <code>TimeZone</code> object you will have access to all the behavior provided by the JRE.
 */
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
   * Return an instance of the ZipCode class for the specified zip code value.  Value must be in one of the following formats:
   * <ul>
   * <li><code>#####</code> - simple 5 digit code </li>
   * <li>######## - 9 digit code</li>
   * <li>#####-#### - 5+4 extended form</li>
   * </ul>
   *
   * @param value a zip code String
   * @return an instance of ZipCode
   * @throws IllegalArgumentException if the value parameter is not valid.
   * @see #isValid(String)
   */
  public static ZipCode getZipCode(final String value) {

    if (!isValid(value)) {
      throw new IllegalArgumentException("Invalid zip code [" + value + "]");
    }

    return new ZipCode(value);
  }

  private ZipCode(final String value) {
    this.value = value;
  }

  /**
   * Returns a boolean value indicating if the String value provided is a supported zip code format.
   *
   * @param zipCode the String value to be validated
   * @return true if the String is a valid zip code, false otherwise
   */
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
   * Returns the 5 digit zip code, even an extended zip code was used to create the object.
   *
   * @return the 5 digit zip code.
   */
  public String getCode() {
    return value.substring(0, 5);
  }

  /**
   * Returns the zip code string used to create the instance, including the 4 digit extension if one
   * was originally provided.
   *
   * @return the full String value used to create the instance.
   */
  public String getValue() {
    return value;
  }

  /**
   * Returns a boolean value indicating if the value includes a four digit extension.
   *
   * @return true if the zip code has an extension, false otherwise
   */
  public boolean isExtended() {
    return value.length() != 5;
  }

  /**
   * Returns the full name for the time zone id associated with the zip code instance.  Ex: <code>America/New_York</code> If there is no mapping for
   * the zip code, then "Unknown" will be returned.
   *
   * @return the display name for the time zone, or "Unknown".
   */
  public String getTimeZone() {

    String timeZoneId = zipCodeToTZ.getProperty(getCode());

    if (timeZoneId == null) {
      timeZoneId = "Unknown";
    }

    return timeZoneId;
  }

}
