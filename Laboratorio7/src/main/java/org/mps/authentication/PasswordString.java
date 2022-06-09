package org.mps.authentication;

import java.util.ArrayList;
import java.util.List;

/**
 * Class that used to store and validate a string that will be used as a password.
 *
 * A string is a valid password if their length is in the range [8, 20] and it contains at least
 * a number and at least a special character from the set {'.', ',',':','?','¿'}
 */
public class PasswordString {
  private final List<Character> specialChars = List.of('.', ',',':','?','¿') ;

  private final String password ;

  public String getPassword() {
    return password;
  }

  public PasswordString(String string) {
    this.password = string ;
  }

  public boolean validate() {
    boolean result = true ;

    List<Character> passwordChars = convertStringToListOfChars(password) ;

    if ( passwordChars.size() < 8 || passwordChars.size() > 20) {
      result = false ;
    } else if (passwordChars.stream().noneMatch(character -> specialChars.contains(character))) {
      result = false ;
    } else if (passwordChars.stream().noneMatch(Character::isDigit)) {
      return false ;
    }

    return result ;
  }

  private List<Character> convertStringToListOfChars(String string) {
    List<Character> chars = new ArrayList<>() ;
    for (int i = 0 ; i < string.length(); i++) {
      chars.add(string.charAt(i)) ;
    }

    return chars ;
  }
}
