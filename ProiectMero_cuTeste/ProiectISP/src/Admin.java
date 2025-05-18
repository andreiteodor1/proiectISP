public class Admin extends Utilizator {

    public Admin(String nume, String email, String parola) {
        super(nume, email, parola);
    }

    public void adaugaSalon(Salon salon) {
        AplicatieProgramari.saloane.add(salon);
    }

    public void stergeSalon(Salon salon) {
        AplicatieProgramari.saloane.remove(salon);
        // Șterge și programările asociate
        AplicatieProgramari.programariGlobale.removeIf(p -> p.getSalon().equals(salon));
    }

    public void adaugaServiciuLaSalon(Salon salon, Serviciu serviciu) {
        salon.adaugaServiciu(serviciu);
    }

    public void stergeServiciuDinSalon(Salon salon, Serviciu serviciu) {
        salon.stergeServiciu(serviciu);
    }
}
