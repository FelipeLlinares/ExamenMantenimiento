package org.laboratorio7;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Java6Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class PasswordStringTest {

    @Test
    public void validateShouldReturnFalseWhenPasswordLengthLessThan8(){
        PasswordString ps = new PasswordString("hola9?");
        assertThat(ps.validate()).isFalse();
    }


    @Test
    public void validateShouldReturnFalseWhenPasswordLength7(){
        PasswordString ps = new PasswordString("holaq?1");
        assertThat(ps.validate()).isFalse();
    }

    @Test
    public void validateShouldReturnTrueWhenPasswordLengt8WithSpecialAndNumber(){
        PasswordString ps = new PasswordString("holaq?1a");
        assertThat(ps.validate()).isTrue();
    }

    @Test
    public void validateShouldReturnTrueWhenPasswordLengt9WithSpecialAndNumber(){
        PasswordString ps = new PasswordString("holaq?1at");
        assertThat(ps.validate()).isTrue();
    }

    @Test
    public void validateShouldReturnTrueWhenPasswordLengtBetween8And20WithSpecialAndNumber(){
        PasswordString ps = new PasswordString("holaq??2d1at");
        assertThat(ps.validate()).isTrue();
    }

    @Test
    public void validateShouldReturnFalseWhenPasswordLengtBetween8And20WithSpecialButNoNumber(){
        PasswordString ps = new PasswordString("holaq:?tdpat");
        assertThat(ps.validate()).isFalse();
    }

    @Test
    public void validateShouldReturnFalseWhenPasswordLengtBetween8And20WithNoNumberAndNoSpecial(){
        PasswordString ps = new PasswordString("holaqdfdytrat");
        assertThat(ps.validate()).isFalse();
    }

    @Test
    public void validateShouldReturnTrueWhenPasswordLengtBetween8And20WithNumberButNoSpecial(){
        PasswordString ps = new PasswordString("holaq2d1ytrat");
        assertThat(ps.validate()).isFalse();
    }

    @Test
    public void validateShouldReturnTrueWhenPasswordLengt19WithSpecialAndNumber(){
        PasswordString ps = new PasswordString("holaq?1a:prt45d,po0");
        assertThat(ps.validate()).isTrue();
    }

    @Test
    public void validateShouldReturnTrueWhenPasswordLengt20WithSpecialAndNumber(){
        PasswordString ps = new PasswordString("holaq?1a:prt45d,po0r");
        assertThat(ps.validate()).isTrue();
    }

    @Test
    public void validateShouldReturnFalseWhenPasswordLength21(){
        PasswordString ps = new PasswordString("holaqreagprtpmdtpotrp");
        assertThat(ps.validate()).isFalse();
    }

    @Test
    public void validateShouldReturnFalseWhenPasswordLengthMoreThan21(){
        PasswordString ps = new PasswordString("holaqreagprt:92812pmd??,.tpotrp");
        assertThat(ps.validate()).isFalse();
    }
}