package org.mps.authentication;

import java.util.HashSet;
import java.util.Set;

public class CredentialStoreSet implements CredentialStore {

  private class Pair {

    public Date date;
    public PasswordString passwordString;

    public Pair(Date date, PasswordString passwordString) {
      this.date = date;
      this.passwordString = passwordString;
    }

    @Override
    public boolean equals(Object o) {
      if (this == o) {
        return true;
      }
      if (o == null || getClass() != o.getClass()) {
        return false;
      }

      Pair pair = (Pair) o;

      if (date != null ? !date.equals(pair.date) : pair.date != null) {
        return false;
      }
      return passwordString != null ? passwordString.equals(pair.passwordString)
          : pair.passwordString == null;
    }

    @Override
    public int hashCode() {
      int result = date != null ? date.hashCode() : 0;
      result = 31 * result + (passwordString != null ? passwordString.hashCode() : 0);
      return result;
    }
  }

  private final Set<Pair> credentials = new HashSet<>();

  @Override
  public boolean credentialExists(Date date, PasswordString passwordString) {
    return credentials.contains(new Pair(date, passwordString));
  }

  @Override
  public void register(Date date, PasswordString passwordString) throws CredentialExistsException {
    Pair newCredentials = new Pair(date, passwordString);
    if (credentials.contains(newCredentials)) {
      throw new CredentialExistsException();
    } else {
      credentials.add(newCredentials);
    }
  }

  @Override
  public int size() {
    return credentials.size() ;
  }
}
