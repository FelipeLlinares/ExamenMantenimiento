package org.mps.authentication;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class PasswordStringTest {

  @Test
  void validateReturnsTrueIfThePasswordIsCorrect() {
    assertTrue(new PasswordString("hello.123;").validate()) ;
  }

  @Test
  void validateReturnsFalseIfThePasswordIsTooShort() {
    assertFalse(new PasswordString("hello").validate()) ;
  }

  @Test
  void validateReturnsFalseIfThePasswordIsTooLong() {
    assertFalse(new PasswordString("1234.1234.1234.1345.1").validate()) ;
  }

  @Test
  void validateReturnsFalseIfThePasswordDoestNotContainASpecialChar() {
    assertFalse(new PasswordString("Hell0World").validate()) ;
  }

  @Test
  void validateReturnsFalseIfThePasswordDoestNotContainADigit() {
    assertFalse(new PasswordString("Hello.World").validate()) ;
  }
}