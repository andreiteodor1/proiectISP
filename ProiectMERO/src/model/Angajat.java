package model;

import java.util.ArrayList;
import java.util.List;

/**
 * Reprezintă un angajat al salonului.
 */
public class Angajat extends Persoana {
    private Serviciu specialitate;
    private List<String> recenzii = new ArrayList<>();

    public Angajat(String nume,
                   String prenume,
                   String nrTel,
                   String mail,
                   Serviciu specialitate) {
        super(nume, prenume, nrTel, mail);
        this.specialitate = specialitate;
    }

    public Serviciu getSpecialitate() { return specialitate; }
    public List<String> getRecenzii() { return recenzii; }

    /**
     * Adaugă o recenzie.
     */
    public void adaugaRecenzie(String text) {
        recenzii.add(text);
    }

    @Override
    public void afisare() {
        super.afisare();
        System.out.println("Specialitate: " + specialitate.getNume());
        System.out.println("Recenzii: " + recenzii.size());
    }
}