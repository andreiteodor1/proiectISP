package manager;


import model.Salon;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;



public class SalonManager {
    private List<Salon> saloane = new ArrayList<>();

    public void adaugaSalon(Salon s) { saloane.add(s); }
    public void stergeSalon(String denumire) {
        Iterator<Salon> it = saloane.iterator();
        while(it.hasNext()) {
            if(it.next().getDenumire().equalsIgnoreCase(denumire)) {
                it.remove(); break;
            }
        }
    }
    public void consultareSaloane() { saloane.forEach(System.out::println); }
    public Salon gasesteSalon(String denumire) {
        return saloane.stream()
                .filter(s -> s.getDenumire().equalsIgnoreCase(denumire))
                .findFirst().orElse(null);
    }
    public List<Salon> getSaloane() { return saloane; }
}