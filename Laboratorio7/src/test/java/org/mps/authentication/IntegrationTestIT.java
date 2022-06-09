package org.mps.authentication;

import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class IntegrationTestIT {
    @Test
    public void userRegistration(){
        CredentialStore css = Mockito.mock(CredentialStore.class);
        Date d = Mockito.mock(Date.class);
        PasswordString ps = Mockito.mock(PasswordString.class);
        CredentialValidator cv = Mockito.mock(CredentialValidator.class);

        Mockito.when(cv.validate()).thenReturn(CredentialValidator.ValidationStatus.VALIDATION_OK);
        Mockito.when(css.credentialExists(d,ps)).thenReturn(false);
        Mockito.when(d.validate()).thenReturn(true);
        Mockito.when(ps.validate()).thenReturn(true);

        UserRegistration ur = new UserRegistration();
        ur.register(d,ps,css,cv);

        Mockito.verify(css, Mockito.times(1)).register(d,ps);
    }

    @Test
    public void userRegistrationAndCredentialValidator(){
        CredentialStore css = Mockito.mock(CredentialStore.class);
        Date d = Mockito.mock(Date.class);
        PasswordString ps = Mockito.mock(PasswordString.class);
        CredentialValidator cv = null;

        Mockito.when(css.credentialExists(d,ps)).thenReturn(false);
        Mockito.when(d.validate()).thenReturn(true);
        Mockito.when(ps.validate()).thenReturn(true);

        UserRegistration ur = new UserRegistration();
        ur.register(d,ps,css,cv);

        Mockito.verify(css, Mockito.times(1)).register(d,ps);
    }

    @Test
    public void userRegistrationCredentialValidatorAndDate(){
        CredentialStore css = Mockito.mock(CredentialStore.class);
        Date d = new Date(10,10,2001);
        PasswordString ps = Mockito.mock(PasswordString.class);
        CredentialValidator cv = null;

        Mockito.when(css.credentialExists(d,ps)).thenReturn(false);
        Mockito.when(ps.validate()).thenReturn(true);

        UserRegistration ur = new UserRegistration();
        ur.register(d,ps,css,cv);

        Mockito.verify(css, Mockito.times(1)).register(d,ps);
    }

    @Test
    public void userRegistrationCredentialValidatorDateAndPasswordString(){
        CredentialStore css = Mockito.mock(CredentialStore.class);
        Date d = new Date(10,10,2001);
        PasswordString ps = new PasswordString("hdasjd??221da");
        CredentialValidator cv = null;

        Mockito.when(css.credentialExists(d,ps)).thenReturn(false);

        UserRegistration ur = new UserRegistration();
        ur.register(d,ps,css,cv);

        Mockito.verify(css, Mockito.times(1)).register(d,ps);
    }

    @Test
    public void userRegistrationCredentialValidatorDatePasswordStringAndCredentialStore(){
        CredentialStoreSet css = new CredentialStoreSet();
        Date d = new Date(10,10,2001);
        PasswordString ps = new PasswordString("hdad??22,1da");
        CredentialValidator cv = null;

        UserRegistration ur = new UserRegistration();
        ur.register(d,ps,css,cv);

        assertThat(css.credentialExists(d,ps)).isTrue();
    }




}
