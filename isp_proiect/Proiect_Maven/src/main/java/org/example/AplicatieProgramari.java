package org.example;

import java.util.ArrayList;
import java.util.List;

public class AplicatieProgramari {
    public static List<Salon> saloane = new ArrayList<>();
    public static List<Programare> programariGlobale = new ArrayList<>();

    public static void main(String[] args) {
        // 1) Cream saloanele
        Salon one       = new Salon("One Barbershop");
        Salon classic   = new Salon("Classic Barbershop");

        // 2) Definim serviciile cu prețuri
        Serviciu tunsoareBarbati  = new Serviciu("Tunsoare bărbați", 50.0);
        Serviciu tunsoareFemei    = new Serviciu("Tunsoare femei",   70.0);
        Serviciu coafat           = new Serviciu("Coafat",           65.0);
        Serviciu vopsit           = new Serviciu("Vopsit păr",      120.0);

        // 3) Adăugăm serviciile în ambele saloane
        for (Serviciu s : List.of(tunsoareBarbati, tunsoareFemei, coafat, vopsit)) {
            one.adaugaServiciu(s);
            classic.adaugaServiciu(s);
        }

        // 4) Definim câte un specialist pentru fiecare salon
        Specialist ionel   = new Specialist("Ionel Popescu");
        Specialist andrei  = new Specialist("Andrei Ionescu");

        one.adaugaSpecialist(ionel);
        classic.adaugaSpecialist(andrei);

        // 5) Încărcăm saloanele în lista globală
        saloane.add(one);
        saloane.add(classic);

        // 6) Afișăm ce am creat
        System.out.println("=== Salons Loaded ===");
        for (Salon s : saloane) {
            System.out.println("Salon: " + s.getNume());
            System.out.println("  Servicii disponibile:");
            for (Serviciu srv : s.getServicii()) {
                System.out.printf("    - %s: %.2f lei%n", srv.getNume(), srv.getPret());
            }
            System.out.println("  Specialiști:");
            for (Specialist sp : s.getSpecialisti()) {
                System.out.println("    - " + sp.getNume());
            }
            System.out.println();
        }
    }
}
