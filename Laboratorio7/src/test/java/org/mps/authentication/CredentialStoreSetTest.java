package org.mps.authentication;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class CredentialStoreSetTest {

  @Test
  void credentialExistsReturnsFalseIfTheCredentialStoreIsEmpty() {
    Date dummyDate = new Date(10, 10, 2010);
    PasswordString dummyPasswordString = new PasswordString("fsjfh.6kdksd");
    assertFalse(new CredentialStoreSet().credentialExists(dummyDate, dummyPasswordString));
  }

  @Test
  void registerAddsANewCredentialIfTheStoreIsEmpty() {
    Date date = new Date(10, 10, 2010);
    PasswordString passwordString = new PasswordString("fsjfh.6kdksd");

    CredentialStore credentialStore = new CredentialStoreSet() ;
    credentialStore.register(date, passwordString);

    assertTrue(credentialStore.credentialExists(date, passwordString));
    assertEquals(1, credentialStore.size());
  }

  @Test
  void registerThrowsAnExceptionIfTheCredentialExists() {
    Date date = new Date(10, 10, 2010);
    PasswordString passwordString = new PasswordString("fsjfh.6kdksd");

    CredentialStore credentialStore = new CredentialStoreSet() ;
    credentialStore.register(date, passwordString);

    assertThrows(CredentialExistsException.class, () -> credentialStore.register(date, passwordString)) ;
  }

  @Test
  void sizeReturnsThreeIfTheCredentialStoreHasThreeCredentials() {
    CredentialStore credentialStore = new CredentialStoreSet() ;
    credentialStore.register(new Date(10, 10, 2021), new PasswordString("asdfwf.,03234"));
    credentialStore.register(new Date(1, 3, 2014), new PasswordString("0ghue,743"));
    credentialStore.register(new Date(8, 6, 1985), new PasswordString("p.hfa81djd"));

    assertEquals(3, credentialStore.size());
  }
}