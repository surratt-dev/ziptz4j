package dev.surratt.ziptz4j;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

class ZipCodeTest {

  @DisplayName("Given a known zip code")
  @Nested
  class GivenAKnownZipCode {

    @DisplayName("When a 5 digit zip code is looked up")
    @Nested
    class When5DigitZipCodeIsLookedUp {

      final ZipCode zipCode;
      final String testValue = "12345";

      {
        zipCode = ZipCode.getZipCode(testValue);
      }

      @DisplayName("Then an instance of ZipCode is returned")
      @Test
      public void thenAnObjectIsReturned() throws Exception {
        assertNotNull(zipCode);
      }

      @DisplayName("Then the toString implementation returns zip code passed in ")
      @Test
      public void thenToStringReturnTheParameter() throws Exception {
        assertEquals(testValue, zipCode.toString());
      }

      @DisplayName("Then getCode returns the 5 digit code")
      @Test
      public void thenGetCodeReturnsThe5DigitCode() throws Exception {
        assertEquals(testValue, zipCode.getCode());
      }

      @DisplayName("Then isExtended returns false")
      @Test
      public void thenIsExtendedReturnsFalse() throws Exception {
        assertFalse(zipCode.isExtended());
      }

      @DisplayName("Then getValue returns the value passed in")
      @Test
      public void thenGetValue() throws Exception {
        assertEquals(testValue, zipCode.getValue());
      }

    }

    @DisplayName("When a 9 digit zip code is looked up with hyphen")
    @Nested
    class When9DigitZipCodeIsLookedUp {

      final ZipCode zipCode;
      final String testValue = "12345-5309";

      {
        zipCode = ZipCode.getZipCode(testValue);
      }

      @DisplayName("Then an instance of ZipCode is returned")
      @Test
      public void thenAnObjectIsReturned() throws Exception {
        assertNotNull(zipCode);
      }

      @DisplayName("Then the toString implementation returns zip code passed in ")
      @Test
      public void thenToStringReturnTheParameter() throws Exception {
        assertEquals(testValue, zipCode.toString());
      }

      @DisplayName("Then getCode returns the 5 digit code")
      @Test
      public void thenGetCodeReturnsThe5DigitCode() throws Exception {
        assertEquals("12345", zipCode.getCode());
      }

      @DisplayName("Then is9Digit returns true")
      @Test
      public void thenIs9DigitReturnsTrue() throws Exception {
        assertTrue(zipCode.isExtended());
      }


      @DisplayName("Then getValue returns the value passed in")
      @Test
      public void thenGetValue() throws Exception {
        assertEquals(testValue, zipCode.getValue());
      }

    }

  }

}
