package org.laboratorio7;

import java.util.Arrays;

/**
 * Class that used to store and validate a date.
 *
 * A data is valid is the year is in the range [1900, 2050], the month is in the range [1,12] and
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
    boolean bien = false;

    int[] meses31 = {1,3,5,7,8,10,12};
    int[] meses30 = {4,6,9,11};

    if(year>= 1900 && year <= 2050){
      if(month>=1 && month<= 12){
          if(month == 2){
              if((year % 4 == 0) && ((year % 100 != 0) || (year % 400 == 0))){
                if (day >= 1 && day <= 29){
                  bien = true;
                }
              }else{
                if (day >= 1 && day <= 28){
                  bien = true;
                }
              }
          }else if(contains(meses31,month)) {
            if (day >= 1 && day <= 31){
              bien = true;
            }
          }else if(contains(meses30,month)){
            if (day >= 1 && day <= 30){
              bien = true;
            }
          }
      }
    }

    return bien;
  }

  private static boolean contains(final int[] arr, final int key) {
    return Arrays.stream(arr).anyMatch(i -> i == key);
  }
}
