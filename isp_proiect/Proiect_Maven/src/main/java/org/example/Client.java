package org.example;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class Client extends Utilizator {
    private Abonament abonament;
    private List<Programare> programari;

    public Client(String nume, String email, String parola, Abonament abonament) {
        super(nume, email, parola);
        this.abonament = abonament;
        this.programari = new ArrayList<>();
    }

    public Abonament getAbonament() {
        return abonament;
    }

    public Programare creeazaProgramare(Salon salon, Specialist specialist,
                                        Serviciu serviciu, LocalDateTime dataOra) {
        // Verificări conflict
        for (Programare p : programari) {
            if (p.getDataOra().equals(dataOra)) {
                throw new IllegalArgumentException(
                        "Ai deja o programare la aceeasi ora.");
            }
        }
        // Verifică disponibilitatea specialistului în tot sistemul
        for (Salon s : AplicatieProgramari.saloane) {
            for (Programare p : s.getSpecialisti()
                    .stream()
                    .flatMap(sp -> AplicatieProgramari.programariGlobale.stream()
                            .filter(pg -> pg.getSpecialist().equals(sp)))
                    .toList()) {

            }
        }
        // Simplificare: verificăm doar în propriul salon
        for (Programare p : AplicatieProgramari.programariGlobale) {
            if (p.getSpecialist().equals(specialist)
                    && p.getDataOra().equals(dataOra)) {
                throw new IllegalArgumentException(
                        "Specialistul nu este disponibil la aceeasi ora.");
            }
        }

        Programare prog = new Programare(this, salon, specialist, serviciu, dataOra);
        programari.add(prog);
        AplicatieProgramari.programariGlobale.add(prog);
        return prog;
    }

    public List<Programare> vizualizeazaProgramari() {
        return new ArrayList<>(programari);
    }

    public void anuleazaProgramare(Programare prog) {
        prog.anuleaza();
        programari.remove(prog);
        AplicatieProgramari.programariGlobale.remove(prog);
    }
}
