package model;

import java.util.ArrayList;
import java.util.List;
import java.time.LocalDateTime;

/**
 * Conține servicii, angajați și rezervări pentru un salon.
 */
public class Salon {
    private String denumire;
    private List<Serviciu> listaServicii = new ArrayList<>();
    private List<Angajat> listaAngajati = new ArrayList<>();
    private List<Rezervare> listaRezervari = new ArrayList<>();

    public Salon(String denumire) {
        this.denumire = denumire;
    }

    public String getDenumire() { return denumire; }

    public void adaugaServiciu(Serviciu s) {
        listaServicii.add(s);
    }
    public void stergeServiciu(Serviciu s) {
        listaServicii.remove(s);
    }
    public void consultaServicii() {
        listaServicii.forEach(Serviciu::afisare);
    }

    public void adaugaAngajat(Angajat a) {
        listaAngajati.add(a);
    }
    public void stergeAngajat(Angajat a) {
        listaAngajati.remove(a);
    }
    public void consultaAngajati() {
        listaAngajati.forEach(Persoana::afisare);
    }

    public void adaugaRezervare(Rezervare r) {
        listaRezervari.add(r);
    }
    public void stergeRezervare(Rezervare r) {
        listaRezervari.remove(r);
        r.cancelare();
    }
    public void vizualizeazaProgramari() {
        listaRezervari.forEach(Rezervare::afisare);
    }
    public boolean verificaDisponibilitate(Angajat a, LocalDateTime data) {
        return listaRezervari.stream()
                .noneMatch(r -> r.getAngajat().equals(a) && r.getDataOra().equals(data));
    }

    @Override
    public String toString() {
        return "Salon: " + denumire;
    }
}