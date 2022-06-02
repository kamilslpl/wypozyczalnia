package pl.sda.arp4.rental.serwis;

import pl.sda.arp4.rental.model.Samochod;
import pl.sda.arp4.rental.model.SkrzyniaBiegow;
import pl.sda.arp4.rental.model.StatusSamochodu;
import pl.sda.arp4.rental.model.TypNadwozia;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Wypozyczalnia {

    private Map<String, Samochod> pojazdy = new HashMap<>();

//  Mozemy dodac samochod
//   Co ta funkcja powinna robic
//    - dodawac samochody
//    - jesli dodam samochod <X Y Z) to ten samochod mus byc w wypozyczalni
//    - dodawac samochody
//    - jesli dodam 5 samochodow (roznych) to powinno byc 5 roznych samochodow w wypozyczalni
//
//    Czego funkcja nie powinna pozwalac:
//    - dodawania pojazdu o istniejacym numerze rejestracyjnym
//
//    Zasada z Test Driven Development - zasada dobrego testera
//    Tresc testu powinna mowic jakie sa warunki dzialania/zakres funkcji
//    Test mowi jak funkcja powinna sie zachowywac
//

    public void dodajSamochod(String numerRejestracyjny,
                              SkrzyniaBiegow skrzyniaBiegow,
                              TypNadwozia typNadwozia,
                              StatusSamochodu statusSamochodu){
        if (!pojazdy.containsKey(numerRejestracyjny)){
            pojazdy.put(numerRejestracyjny, new Samochod(numerRejestracyjny,
                                                           skrzyniaBiegow,
                                                            typNadwozia,
                                                             statusSamochodu));
        }
    }
    public List<Samochod> zwrocListe(){
        return new ArrayList<>(pojazdy.values());
    }

    public List<Samochod> zwrocListeDostepnych(){
        List<Samochod> listaDostepnych = new ArrayList<>();
        for (Samochod pojazd : pojazdy.values()) {
            if (pojazd.getStatus() == StatusSamochodu.DOSTEPNY){
                listaDostepnych.add(pojazd);
        }
        }
        return listaDostepnych;
    }

    public List<Samochod> zwrocListeWynajetych(){
        List<Samochod> listaWynajetych = new ArrayList<>();
        for (Samochod pojazd : pojazdy.values()) {
            if (pojazd.getStatus() == StatusSamochodu.WYNAJETY) {
                listaWynajetych.add(pojazd);
            }
        }
        return listaWynajetych;
    }

    public void usunSamochod(String numerRejestracyjny){
    pojazdy.get(numerRejestracyjny).setStatus(StatusSamochodu.NIEDOSTEPNY);
    }


}
