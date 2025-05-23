package model;

import java.util.ArrayList;
import java.util.List;

public class Client extends Persoana {
    private Abonament abonament;
    private Serviciu serviciuAles;
    private List<Rezervare> rezervari = new ArrayList<>();

    public Client(String nume,
                  String prenume,
                  String nrTel,
                  String mail,
                  Abonament abonament) {
        super(nume, prenume, nrTel, mail);
        this.abonament = abonament;
    }

    public Abonament getAbonament() { return abonament; }
    public Serviciu getServiciuAles() { return serviciuAles; }
    public List<Rezervare> getRezervari() { return rezervari; }

    public void setAbonament(Abonament abonament) { this.abonament = abonament; }

    public void selecteazaServiciu(Serviciu s) {
        this.serviciuAles = s;
    }

    public void adaugaRezervare(Rezervare r) {
        rezervari.add(r);
    }

    public void anuleazaRezervare(Rezervare r) {
        rezervari.remove(r);
        r.cancelare();
    }

    public void oferaFeedback(Angajat a, String text) {
        a.adaugaRecenzie(text);
    }

    @Override
    public void afisare() {
        super.afisare();
        System.out.println("Abonament: " + abonament);
        System.out.println("Serviciu ales: " +
                (serviciuAles != null ? serviciuAles.getNume() : "-"));
        System.out.println("Număr rezervări: " + rezervari.size());
    }
}