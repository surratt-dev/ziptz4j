package dev.surratt.ziptz4j;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

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

  @DisplayName("Given a set of known zip codes")
  @Nested
  class GivenASetOfKnownZipCodes {

    @DisplayName("When a zip code is known")
    @Nested
    class WhenZipCodeIsKnown {

      final ZipCode zipCode = ZipCode.getZipCode("87121");

      @DisplayName("Then the correct TimeZone ID is returned")
      @Test
      public void thenCorrectTimeZoneIdIsReturned() throws Exception {
        assertEquals("America/Denver", zipCode.getTimeZone());
      }

    }

    @DisplayName("When an extended zip code is known")
    @Nested
    class When {

      final ZipCode zipCode = ZipCode.getZipCode("97034-1234");

      @DisplayName("Then the correct TimeZone ID is returned for an extended Zip Code")
      @Test
      public void then() throws Exception {
        assertEquals("America/Los_Angeles", zipCode.getTimeZone());
      }

    }

    @DisplayName("When ZipCode is not known")
    @Nested
    class WhenZipCodeIsNotKnown {

      ZipCode zipCode = ZipCode.getZipCode("00000");

      @DisplayName("Then unknown is returned")
      @Test
      public void thenUnknownIsReturned() throws Exception {
        assertEquals("Unknown", zipCode.getTimeZone());
      }

    }


  }

  @DisplayName("Given a set of rules for zip code structure")
  @Nested
  class GivenASetOfRules {

    @DisplayName("When isValid is called")
    @Nested
    class WhenIsValidIsCalled {

      @DisplayName("Then isValid returns false when null is passed")
      @Test
      public void thenNullIsInvalid() throws Exception {
        assertFalse(ZipCode.isValid(null));
      }

      @DisplayName("Then isValid returns true when string is 5 digits")
      @Test
      public void then5DigitsIsValid() throws Exception {
        assertTrue(ZipCode.isValid("12345"));
      }

      @DisplayName("Then isValid returns true when string is 9 digits")
      @Test
      public void then9DigitsIsValis() throws Exception {
        assertTrue(ZipCode.isValid("123455309"));
      }

      @DisplayName("Then isValid returns true when string is extended format")
      @Test
      public void thenExtendedFormatIsValid() throws Exception {
        assertTrue(ZipCode.isValid("12345-5309"));
      }

      @DisplayName("Then isValid returns false when the string has alpha characters ")
      @Test
      public void thenContainsAlphaIsInvalid() throws Exception {
        assertFalse(ZipCode.isValid("12T45"));
      }

      @DisplayName("Then isValid returns false when the string is less than 5 characters")
      @Test
      public void thenLessThan5CharactersInvalid() throws Exception {
        assertFalse(ZipCode.isValid("1234"));
      }

      @DisplayName("Then isValid returns false when the string is more than 10 characters")
      @Test
      public void then() throws Exception {
        assertFalse(ZipCode.isValid("12345678901"));
      }

    }

    @DisplayName("When getZipCode is called with an invalid string")
    @Nested
    class WhenGetZipCode {

      @DisplayName("Then an IllegalArgumentException is thrown")
      @Test
      public void then() throws Exception {
        try {
          ZipCode.getZipCode("1");
          fail("Expected an exception.");
        } catch (IllegalArgumentException e) {
          assertEquals("Invalid zip code [1]", e.getMessage());
        }

      }

    }

  }

}
