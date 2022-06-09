package org.mps.authentication;

public interface CredentialStore {
  boolean credentialExists(Date date, PasswordString passwordString) ;
  void register(Date date, PasswordString passwordString) throws CredentialExistsException ;

  int size () ;
}
