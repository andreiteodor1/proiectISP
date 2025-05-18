package org.example;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class Salon {
    private String idSalon;
    private String nume;
    private List<Serviciu> servicii;
    private List<Specialist> specialisti;

    public Salon(String nume) {
        this.idSalon = UUID.randomUUID().toString();
        this.nume = nume;
        this.servicii = new ArrayList<>();
        this.specialisti = new ArrayList<>();
    }

    public String getIdSalon() { return idSalon; }
    public String getNume() { return nume; }

    public void adaugaServiciu(Serviciu s) {
        servicii.add(s);
    }
    public void stergeServiciu(Serviciu s) {
        servicii.remove(s);
    }
    public List<Serviciu> getServicii() {
        return new ArrayList<>(servicii);
    }

    public void adaugaSpecialist(Specialist sp) {
        specialisti.add(sp);
    }
    public void stergeSpecialist(Specialist sp) {
        specialisti.remove(sp);
    }
    public List<Specialist> getSpecialisti() {
        return new ArrayList<>(specialisti);
    }
}
