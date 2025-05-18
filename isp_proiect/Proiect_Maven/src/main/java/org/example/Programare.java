package org.example;


import java.time.LocalDateTime;
import java.util.UUID;

public class Programare {
    public static final String STARE_IN_ASTEPTARE = "IN_ASTEPTARE";
    public static final String STARE_CONFIRMATA   = "CONFIRMATA";
    public static final String STARE_ANULATA      = "ANULATA";

    private String idProgramare;
    private Client client;
    private Salon salon;
    private Specialist specialist;
    private Serviciu serviciu;
    private LocalDateTime dataOra;
    private String stare;

    public Programare(Client client,
                       Salon salon,
                       Specialist specialist,
                       Serviciu serviciu,
                       LocalDateTime dataOra) {
        this.idProgramare = UUID.randomUUID().toString();
        this.client       = client;
        this.salon        = salon;
        this.specialist   = specialist;
        this.serviciu     = serviciu;
        this.dataOra      = dataOra;
        this.stare        = STARE_IN_ASTEPTARE;
    }


    public Client getClient()           { return client;       }
    public Specialist getSpecialist()   { return specialist;   }
    public LocalDateTime getDataOra()   { return dataOra;      }
    public String getStare()            { return stare;        }
    public Serviciu getServiciu() {
        return serviciu;
    }
    public Salon getSalon() {
        return salon;
    }

    public void confirma() {
        if (!STARE_IN_ASTEPTARE.equals(this.stare))
            throw new IllegalStateException("Poți confirma doar rezervările în așteptare.");
        this.stare = STARE_CONFIRMATA;
    }

    public void anuleaza() {
        this.stare = STARE_ANULATA;
    }

    public static boolean verificaDisponibilitate(Client client,
                                                  Specialist specialist,
                                                  LocalDateTime dataOra) {

        for (Programare p : client.vizualizeazaProgramari()) {
            if (p.getDataOra().equals(dataOra)) {
                return false;
            }
        }


        for (Programare p : AplicatieProgramari.programariGlobale) {
            if (p.getSpecialist().equals(specialist)
                    && p.getDataOra().equals(dataOra)) {
                return false;
            }
        }

        return true;
    }


    public static Programare creeazaRezervare(Client client,
                                              Salon salon,
                                              Specialist specialist,
                                              Serviciu serviciu,
                                              LocalDateTime dataOra) {
        if (!verificaDisponibilitate(client, specialist, dataOra)) {
            throw new IllegalArgumentException(
                    "Nu poți face o programare la acea oră");
        }
        return new Programare(client, salon, specialist, serviciu, dataOra);
    }
}
