package pl.sda.arp4.rental.service;

//    Czego funkcja nie powinna pozwalac:
//    - dodawania pojazdu o istniejacym numerze rejestracyjnym
//
//    Zasada z Test Driven Development - zasada dobrego testera
//    Tresc testu powinna mowic jakie sa warunki dzialania/zakres funkcji
//    Test mowi jak funkcja powinna sie zachowywac

import org.junit.Assert;
import org.junit.jupiter.api.Test;
import pl.sda.arp4.rental.model.Samochod;
import pl.sda.arp4.rental.model.SkrzyniaBiegow;
import pl.sda.arp4.rental.model.StatusSamochodu;
import pl.sda.arp4.rental.model.TypNadwozia;
import pl.sda.arp4.rental.serwis.Wypozyczalnia;

import java.util.List;

public class WypozyczalniaTest {

    @Test
    public void test_mozliweJestDodawanieSamochodu(){

        Samochod testowanySamochod = new Samochod("test1",
                SkrzyniaBiegow.AUTOMATYCZNA,
                TypNadwozia.CABRIO,
                StatusSamochodu.DOSTEPNY);

        Wypozyczalnia wypozyczalnia = new Wypozyczalnia();
        wypozyczalnia.dodajSamochod(
                testowanySamochod.getNumerRejestracjny(),
                testowanySamochod.getSkrzynia(),
                testowanySamochod.getTyp(),
                testowanySamochod.getStatus());

        List<Samochod> wynikZwroconaLista = wypozyczalnia.zwrocListe();
        Assert.assertEquals("Lista powinna zawierac dokladnie jeden pojazd, bo tylko tyle ich dodalismy test1", 1, wynikZwroconaLista.size());
        Assert.assertTrue("Lista powinna zawierac samochod testowany test1",
                wynikZwroconaLista.contains(testowanySamochod));
    }

    @Test
    // nie jest mozliwe 'nadpisanie' samochodu
    public void test_mozliweJestDodanieSamochoduAleNieNadpisanie(){
        Samochod testowanySamochod = new Samochod("test1",
                SkrzyniaBiegow.AUTOMATYCZNA,
                TypNadwozia.CABRIO,
                StatusSamochodu.DOSTEPNY);
        Samochod testowanySamochod2 = new Samochod("test1",
                SkrzyniaBiegow.MANUAL,
                TypNadwozia.SUV,
                StatusSamochodu.DOSTEPNY);

        // dodajemy 1 i zadziala poprawnie
        Wypozyczalnia wypozyczalnia = new Wypozyczalnia();
        wypozyczalnia.dodajSamochod(
                testowanySamochod.getNumerRejestracjny(),
                testowanySamochod.getSkrzynia(),
                testowanySamochod.getTyp(),
                testowanySamochod.getStatus());

//        dodajemy 2 i ma nie zadzialac
        wypozyczalnia.dodajSamochod(
                testowanySamochod2.getNumerRejestracjny(),
                testowanySamochod2.getSkrzynia(),
                testowanySamochod2.getTyp(),
                testowanySamochod2.getStatus());

        List<Samochod> wynikZwroconaLista = wypozyczalnia.zwrocListe();
        Assert.assertEquals("Lista powinna zawierac dokladnie jeden pojazd, bo tylko tyle ich dodalismy test2", 1, wynikZwroconaLista.size());
        Assert.assertTrue("Lista powinna zawierac samochod testowany test2", wynikZwroconaLista.contains(testowanySamochod));
    }

    @Test
    public void test_mozemyPobraćListeSamochodowDostepnych(){
        Samochod testowanySamochod = new Samochod("test1",
                SkrzyniaBiegow.AUTOMATYCZNA,
                TypNadwozia.CABRIO,
                StatusSamochodu.NIEDOSTEPNY);
        Samochod testowanySamochod2 = new Samochod("test2",
                SkrzyniaBiegow.MANUAL,
                TypNadwozia.SUV,
                StatusSamochodu.DOSTEPNY);

        // dodajemy 1 i zadziala poprawnie
        Wypozyczalnia wypozyczalnia = new Wypozyczalnia();
        wypozyczalnia.dodajSamochod(
                testowanySamochod.getNumerRejestracjny(),
                testowanySamochod.getSkrzynia(),
                testowanySamochod.getTyp(),
                testowanySamochod.getStatus());

//        dodajemy 2 i ma nie zadzialac
        wypozyczalnia.dodajSamochod(
                testowanySamochod2.getNumerRejestracjny(),
                testowanySamochod2.getSkrzynia(),
                testowanySamochod2.getTyp(),
                testowanySamochod2.getStatus());

        List<Samochod> wynikZwroconaListaWszystkichSamochodow = wypozyczalnia.zwrocListe();
        Assert.assertEquals("Lista powinna zawierac oba pojazdy test3", 2, wynikZwroconaListaWszystkichSamochodow.size());

        List<Samochod> dostepne = wypozyczalnia.zwrocListeDostepnych();
        Assert.assertEquals("Lista powinna zawierac jeden dostepny pojazd test3", 1,dostepne.size());
        Assert.assertTrue("Lista powinna zawierac tylko samochod dostepny test3", dostepne.contains(testowanySamochod2));

    }

    @Test
    public void test_mozemyZmienicStatusSamochoduNaNiedostepny(){

        Samochod testowanySamochod = new Samochod("test1",
                SkrzyniaBiegow.AUTOMATYCZNA,
                TypNadwozia.CABRIO,
                StatusSamochodu.DOSTEPNY);

        Wypozyczalnia wypozyczalnia = new Wypozyczalnia();
        wypozyczalnia.dodajSamochod(
                testowanySamochod.getNumerRejestracjny(),
                testowanySamochod.getSkrzynia(),
                testowanySamochod.getTyp(),
                testowanySamochod.getStatus());

        List<Samochod> wynikZwroconaLista = wypozyczalnia.zwrocListe();
        Assert.assertEquals("Lista powinna zawierac dokladnie jeden pojazd, bo tylko tyle ich dodalismy test1", 1, wynikZwroconaLista.size());
        Assert.assertTrue("Lista powinna zawierac samochod testowany test1",
                wynikZwroconaLista.contains(testowanySamochod));

        wypozyczalnia.usunSamochod("Test1");
        wynikZwroconaLista = wypozyczalnia.zwrocListe();
        Assert.assertEquals("Lista powinna zawierac dokladnie jeden pojazd, bo tylko tyle ich dodalismy test1", 1, wynikZwroconaLista.size());
        Assert.assertTrue("Samochod powinien miec status niedostepny",
                wynikZwroconaLista.get(0).getStatus() == StatusSamochodu.NIEDOSTEPNY);

        Samochod samochodDoPorownania = new Samochod("test1", SkrzyniaBiegow.AUTOMATYCZNA, TypNadwozia.CABRIO, StatusSamochodu.NIEDOSTEPNY);
        Assert.assertTrue("Samochod powinnien miec status niedostepny", wynikZwroconaLista.contains(samochodDoPorownania));


    }

    @Test
    public void test_uzytkownikNieZepsujeetodyZmianyStatusuNaNiedostepnyPrzekazujacNieistniejacySamochod(){


    }

}
