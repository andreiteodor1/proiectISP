package manager;

import model.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Gestionează mai multe saloane și fluxul de utilizare.
 */
public class SalonManager {
    private List<Salon> saloane = new ArrayList<>();

    public void adaugaSalon(Salon s) {
        saloane.add(s);
    }
    public void consultareSaloane() {
        saloane.forEach(System.out::println);
    }

    public Salon gasesteSalon(String denumire) {
        return saloane.stream()
                .filter(s -> s.getDenumire().equalsIgnoreCase(denumire))
                .findFirst().orElse(null);
    }
}