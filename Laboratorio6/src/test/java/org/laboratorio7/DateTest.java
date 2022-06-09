package org.laboratorio7;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Java6Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class DateTest {
    @Test
    public void validateShouldReturnFalseWhenYearLessThan1900(){
        Date date = new Date(20,10,1800);
        assertThat(date.validate()).isFalse();
    }

    @Test
    public void validateShouldReturnFalseWhenMonthLessThan1(){
        Date date = new Date(20,0,1990);
        assertThat(date.validate()).isFalse();
    }

    //Meses de 31 dias
    @Test
    public void validateShouldReturnFalseWhenMonth31DaysAndDayLessThan1(){
        Date date = new Date(0,1,1990);
        assertThat(date.validate()).isFalse();
    }

    @Test
    public void validateShouldReturnTrueWhenMonth31DaysAndDayBetween1And31(){
        Date date = new Date(31,1,1990);
        assertThat(date.validate()).isTrue();
    }

    @Test
    public void validateShouldReturnFalseWhenMonth31DaysAndDayMoreThan31(){
        Date date = new Date(32,1,1990);
        assertThat(date.validate()).isFalse();
    }

    //Meses de 30 días
    @Test
    public void validateShouldReturnFalseWhenMonth30DaysAndDayLessThan1(){
        Date date = new Date(0,4,1990);
        assertThat(date.validate()).isFalse();
    }

    @Test
    public void validateShouldReturnTrueWhenMonth30DaysAndDayBetween1And30(){
        Date date = new Date(30,4,1990);
        assertThat(date.validate()).isTrue();
    }

    @Test
    public void validateShouldReturnFalseWhenMonth30DaysAndDayMoreThan30(){
        Date date = new Date(31,4,1990);
        assertThat(date.validate()).isFalse();
    }

    //Mes Febrero -- Año Bisiesto 29  días
    @Test
    public void validateShouldReturnFalseWhenYearLeapMonth2AndDayLessThan1(){
        Date date = new Date(0,2,2000);
        assertThat(date.validate()).isFalse();
    }

    @Test
    public void validateShouldReturnTrueWhenYearLeapMonth2AndDayBetween1And29(){
        Date date = new Date(29,2,2000);
        assertThat(date.validate()).isTrue();
    }

    @Test
    public void validateShouldReturnFalseWhenYearLeapMonth2AndDayMoreThan29(){
        Date date = new Date(30,2,2000);
        assertThat(date.validate()).isFalse();
    }

    //Mes Febrero -- Año No Bisiesto 28  días
    @Test
    public void validateShouldReturnFalseWhenYearNoLeapMonth2AndDayLessThan1(){
        Date date = new Date(0,2,2001);
        assertThat(date.validate()).isFalse();
    }

    @Test
    public void validateShouldReturnTrueWhenYearNoLeapMonth2AndDayBetween1And28(){
        Date date = new Date(28,2,2001);
        assertThat(date.validate()).isTrue();
    }

    @Test
    public void validateShouldReturnFalseWhenYearNoLeapMonth2AndDayMoreThan28(){
        Date date = new Date(29,2,2001);
        assertThat(date.validate()).isFalse();
    }


    public void validateShouldReturnFalseWhenMonthMoreThan12(){
        Date date = new Date(20,13,1990);
        assertThat(date.validate()).isFalse();
    }
    public void validateShouldReturnFalseWhenYearMoreThan2050(){
        Date date = new Date(20,10,2051);
        assertThat(date.validate()).isFalse();
    }

}