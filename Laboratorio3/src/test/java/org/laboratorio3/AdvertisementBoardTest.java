package org.laboratorio3;

import static org.junit.jupiter.api.Assertions.*;
import static org.assertj.core.api.Assertions.*;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

class AdvertisementBoardTest {
    @Test
    public void ABoardHasAnAdvertisementWhenItIsCreated() {
        AdvertisementBoard ab = new AdvertisementBoard();
        assertThat(ab.numberOfPublishedAdvertisements()).isEqualTo(1);
    }

    @Test
    public void PublishAnAdvertisementByTheCompanyIncreasesTheNumberOfAdvertisementsByOne() {
        AdvertisementBoard ab = new AdvertisementBoard();
        int size = ab.numberOfPublishedAdvertisements();

        AdvertiserDatabase ad = Mockito.mock(AdvertiserDatabase.class);
        PaymentDatabase pd = Mockito.mock(PaymentDatabase.class);

        Advertisement advertisement = new Advertisement("tituloPrueba","contenidoPrueba","THE Company");
        ab.publish(advertisement,ad,pd);

        assertThat(ab.numberOfPublishedAdvertisements()).isEqualTo(size+1);
    }

    @Test
    public void WhenAnAdvertiserHasNoFoundsTheAdvertisementIsNotPublished() {
        AdvertisementBoard ab = new AdvertisementBoard();
        int size = ab.numberOfPublishedAdvertisements();
        Advertisement advertisement = new Advertisement("tituloPrueba","contenidoPrueba","Pepe Gotera y Otilio");

        //Creamos objetos mockito
        AdvertiserDatabase ad = Mockito.mock(AdvertiserDatabase.class);
        PaymentDatabase pd = Mockito.mock(PaymentDatabase.class);

        //Definimos comportamiento
        Mockito.when(ad.findAdviser(advertisement.advertiser)).thenReturn(true);
        Mockito.when(pd.advertiserHasFunds(advertisement.advertiser)).thenReturn(false);

        //Ejecutamos
        ab.publish(advertisement,ad,pd);

        assertThat(ab.numberOfPublishedAdvertisements()).isEqualTo(size);
    }

    @Test
    public void AnAdvertisementIsPublishedIfTheAdvertiserIsRegisteredAndHasFunds() {
        AdvertisementBoard ab = new AdvertisementBoard();
        Advertisement advertisement = new Advertisement("tituloPrueba","contenidoPrueba","Robin Robot");

        //Creamos objetos mockito
        AdvertiserDatabase ad = Mockito.mock(AdvertiserDatabase.class);
        PaymentDatabase pd = Mockito.mock(PaymentDatabase.class);

        //Definimos comportamiento
        Mockito.when(ad.findAdviser(advertisement.advertiser)).thenReturn(true);
        Mockito.when(pd.advertiserHasFunds(advertisement.advertiser)).thenReturn(true);

        //Ejecutamos
        ab.publish(advertisement,ad,pd);

        //Verificamos
        Mockito.verify(pd).advertisementPublished(advertisement.advertiser);
        Mockito.verify(pd,Mockito.times(1)).advertisementPublished(advertisement.advertiser);
    }

    @Test
    public void WhenTheOwnerMakesTwoPublicationsAndTheFirstOneIsDeletedItIsNotInTheBoard() {
        AdvertisementBoard ab = new AdvertisementBoard();
        Advertisement advertisement1 = new Advertisement("tituloPrueba1","contenidoPrueba1","THE Company");
        Advertisement advertisement2 = new Advertisement("tituloPrueba2","contenidoPrueba2","THE Company");

        AdvertiserDatabase ad = Mockito.mock(AdvertiserDatabase.class);
        PaymentDatabase pd = Mockito.mock(PaymentDatabase.class);

        ab.publish(advertisement1,ad,pd);
        ab.publish(advertisement2,ad,pd);
        ab.deleteAdvertisement(advertisement1.title,advertisement1.advertiser);

        assertThat(ab.findByTitle(advertisement1.title)).isNull();
    }

    @Test
    public void AnExistingAdvertisementIsNotPublished() {
        AdvertisementBoard ab = new AdvertisementBoard();
        String advertiser = "Robin Robot";
        Advertisement advertisement1 = new Advertisement("tituloPrueba","contenidoPrueba1",advertiser);
        Advertisement advertisement2 = new Advertisement("tituloPrueba","contenidoPrueba2",advertiser);

        //Creamos objetos mockito
        AdvertiserDatabase ad = Mockito.mock(AdvertiserDatabase.class);
        PaymentDatabase pd = Mockito.mock(PaymentDatabase.class);

        //Definimos comportamiento
        Mockito.when(ad.findAdviser(advertiser)).thenReturn(true);
        Mockito.when(pd.advertiserHasFunds(advertiser)).thenReturn(true);

        //Ejecutamos
        ab.publish(advertisement1,ad,pd);
        ab.publish(advertisement2,ad,pd);

        //Verificamos
        Mockito.verify(pd).advertisementPublished(advertiser);
        Mockito.verify(pd,Mockito.times(1)).advertisementPublished(advertiser);
    }

    @Test
    public void AnExceptionIsRaisedIfTheBoardIsFullAndANewAdvertisementIsPublished() {
        Advertisement advertisement = new Advertisement("tituloPrueba","contenidoPrueba","Tim O'Theo");

        //Creamos objetos mockito
        AdvertiserDatabase ad = Mockito.mock(AdvertiserDatabase.class);
        PaymentDatabase pd = Mockito.mock(PaymentDatabase.class);
        AdvertisementBoard ab = Mockito.spy(AdvertisementBoard.class);

        //Definimos comportamiento
        Mockito.when(ad.findAdviser(advertisement.advertiser)).thenReturn(true);
        Mockito.when(pd.advertiserHasFunds(advertisement.advertiser)).thenReturn(true);
        Mockito.doReturn(ab.getMaxBoardSize()).when(ab).numberOfPublishedAdvertisements();

        assertThrows(FullBoardException.class, ()-> ab.publish(advertisement,ad,pd));

    }
}