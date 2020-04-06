package dev.surratt.ziptz4j;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.fail;

class ZipCodeTest {

    @DisplayName("Given a known zip code")
    @Nested
    class GivenAKnownZipCode {

        @DisplayName("When a 5 digit zip code is looked up")
        @Nested
        class When5DigitZipCodeIsLookedUp {

            @DisplayName("Then an instance of ZipCode is returned")
            @Test
            public void thenAnObjectIsReturned() throws Exception {
                fail("test not implemented");
            }

            @DisplayName("Then the toString implementation returns zip code passed in ")
            @Test
            public void thenToStringReturnTheParameter() throws Exception {
                fail("test not implemented");
            }

            @DisplayName("Then getCode returns the 5 digit code")
            @Test
            public void thenGetCodeReturnsThe5DigitCode() throws Exception {
                fail("test not implemented");
            }

            @DisplayName("Then is9Digit returns false")
            @Test
            public void thenIs9DigitReturnsFalse() throws Exception {
                fail("test not implemented");
            }

            @DisplayName("Then getValue returns the value passed in")
            @Test
            public void thenGetValue() throws Exception {
                fail("test not implemented");
            }

        }

        @DisplayName("When a 9 digit zip code is looked up with hyphen")
        @Nested
        class When9DigitZipCodeIsLookedUp {

            @DisplayName("Then an instance of ZipCode is returned")
            @Test
            public void thenAnObjectIsReturned() throws Exception {
                fail("test not implemented");
            }

            @DisplayName("Then the toString implementation returns zip code passed in ")
            @Test
            public void thenToStringReturnTheParameter() throws Exception {
                fail("test not implemented");
            }

            @DisplayName("Then getCode returns the 5 digit code")
            @Test
            public void thenGetCodeReturnsThe5DigitCode() throws Exception {
                fail("test not implemented");
            }

            @DisplayName("Then is9Digit returns true")
            @Test
            public void thenIs9DigitReturnsTrue() throws Exception {
                fail("test not implemented");
            }

        }

    }

}
