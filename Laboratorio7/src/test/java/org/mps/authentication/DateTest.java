package org.mps.authentication;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

public class DateTest {
  @Test
  void validateReturnsTrueIfTheDateIsValid() {
    assertTrue(new Date(12, 1, 2020).validate()) ;
  }

  @Test
  void validateReturnsFalseIfTheDayIsTooSmall() {
    assertFalse(new Date(0, 1, 2020).validate()) ;
  }

  @Test
  void validateReturnsFalseIfTheDayIsTooBig() {
    assertFalse(new Date(40, 1, 2020).validate()) ;
  }

  @Test
  void validateReturnsFalseIfTheMonthIsTooSmall() {
    assertFalse(new Date(1, 0, 2020).validate()) ;
  }

  @Test
  void validateReturnsFalseIfTheMonthIsTooBig() {
    assertFalse(new Date(40, 13, 2020).validate()) ;
  }

  @Test
  void validateReturnsFalseIfTheYearIsTooSmall() {
    assertFalse(new Date(1, 10, 1899).validate()) ;
  }

  @Test
  void validateReturnsFalseIfTheYearIsTooBig() {
    assertFalse(new Date(1, 10, 2051).validate()) ;
  }

  @Test
  void validateReturnsTrueIfTheYearIsLeapAndTheDayIs29OfFebruary() {
    assertTrue(new Date(29, 2, 1980).validate()) ;
  }

  @Test
  void validateReturnsFalseIfTheYearIsNotLeapAndTheDayIs29OfFebruary() {
    assertFalse(new Date(29, 2, 1981).validate()); ;
  }
}
