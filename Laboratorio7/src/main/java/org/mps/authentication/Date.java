package org.mps.authentication;

import java.text.ParseException;
import java.text.SimpleDateFormat;

/**
 * Class used to store and validate a date.
 *
 * A date is valid is the year is in the range [1900, 2050], the month is in the range [1,12] and
 * the day is in the range [1,31] but having into account the month and the leap years.
 */
public class Date {
  private final int day ;
  private final int month ;
  private final int year ;

  public int getDay() {
    return day;
  }

  public int getMonth() {
    return month;
  }

  public int getYear() {
    return year;
  }

  public Date(int day, int month, int year) {
    this.day = day ;
    this.month = month ;
    this.year = year ;
  }

  public boolean validate() {
    SimpleDateFormat date = new SimpleDateFormat("dd/MM/yyyy") ;
    date.setLenient(false);

    String dateString = "" + day +"/" + month + "/" + year ;

    try {
      date.parse(dateString);
      if (year > 2050 || year < 1900) {
        return false ;
      } else {
        return true;
      }
    } catch (ParseException exception) {
      return false ;
    }
  }
}
