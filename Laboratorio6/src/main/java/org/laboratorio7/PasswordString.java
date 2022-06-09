package org.laboratorio7;

/**
 * Class that used to store and validate a string that will be used as a password.
 *
 * A string is a valid password if their length is in the range [8, 20] and it contains at least
 * a number and at least a special character from the set {'.', ',',':','?','¿'}
 */
public class PasswordString {
  private final String password ;

  public String getPassword() {
    return password;
  }

  public PasswordString(String string) {
    this.password = string ;
  }

  public boolean validate() {
    boolean esp = false;
    boolean num = false;
    char[] especiales = {'.', ',',':','?','¿'};

    if(password.length() <= 20 && password.length() >= 8){
        for(int i=0; i< password.length();i++){
          char c = password.charAt(i);

          if(Character.isDigit(c))
            num = true;

          for(int j=0; j < especiales.length;j++){
              if(especiales[j] == c){
                esp = true;
              }
          }
        }
    }
    return num && esp;
  }
}
