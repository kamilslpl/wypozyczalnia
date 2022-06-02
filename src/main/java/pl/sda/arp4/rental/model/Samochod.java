package pl.sda.arp4.rental.model;
/*
* Model = Klasa bez logiki biznesowej = POJO = Plain Old Java Object
 * Posiada:
 *  - gettery, settery
 *  - konstruktor pełny
 *  - konstruktor pusty
 *  - prywatne pola
 *
 */
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Samochod {
    //dla indentyfikacji
    private String numerRejestracjny;

    //dla filtrow
    private SkrzyniaBiegow skrzynia;
    private TypNadwozia typ;

    //dla sprawdzenia dostepnosci
    private StatusSamochodu status;

     }

