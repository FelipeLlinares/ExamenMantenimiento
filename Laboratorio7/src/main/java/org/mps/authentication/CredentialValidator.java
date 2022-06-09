package org.mps.authentication;

/**
 * Class that validate that a birth data and a password are valid values
 */
public class CredentialValidator {
  public enum ValidationStatus {BIRTHDAY_INVALID, PASSWORD_INVALID, EXISTING_CREDENTIAL, VALIDATION_OK}

  private final Date birthDate ;
  private final PasswordString passwordString ;

  private final CredentialStore credentialStore ;

  public CredentialValidator(Date birthDate, PasswordString passwordString, CredentialStore credentialStore) {
    this.birthDate = birthDate;
    this.passwordString = passwordString;
    this.credentialStore = credentialStore ;
  }

  public ValidationStatus validate() {
    ValidationStatus result = ValidationStatus.VALIDATION_OK;
    if (!birthDate.validate()) {
      result = ValidationStatus.BIRTHDAY_INVALID ;
    } else if (!passwordString.validate()) {
      result = ValidationStatus.PASSWORD_INVALID ;
    } else if (credentialStore.credentialExists(birthDate, passwordString)) {
      result = ValidationStatus.EXISTING_CREDENTIAL ;
    }
    return result ;
  }
}
