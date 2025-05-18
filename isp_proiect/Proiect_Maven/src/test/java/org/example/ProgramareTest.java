package org.example;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

class ProgramareTest {

    private Client client;
    private Salon salon;
    private Serviciu tunsoare;
    private Specialist ionel;
    private LocalDateTime dt;

    @BeforeEach
    void setup() {

        AplicatieProgramari.saloane.clear();
        AplicatieProgramari.programariGlobale.clear();


        salon    = new Salon("Salon Test");
        tunsoare = new Serviciu("Tunsoare", 50.0);
        ionel    = new Specialist("Ionel Popescu");

        salon.adaugaServiciu(tunsoare);
        salon.adaugaSpecialist(ionel);
        AplicatieProgramari.saloane.add(salon);


        client = new Client(
                "Client Test",
                "c@test.ro",
                "parola",
                new Abonament(SubscriptionType.LUNAR)
        );

        // Data/Ora de test
        dt = LocalDateTime.of(2025, 5, 20, 10, 0);
    }

    @Test
    void testVerificaDisponibilitateInitiala() {

        assertTrue(
                Programare.verificaDisponibilitate(client, ionel, dt),
                "Disponibil"
        );
    }

    @Test
    void testCreeazaProgramareSiAdaugaInListe() {

        Programare p = client.creeazaProgramare(salon, ionel, tunsoare, dt);

        assertNotNull(p, "Programarea nu trebuie să fie null");
        assertEquals(client, p.getClient());
        assertEquals(salon, p.getSalon());
        assertEquals(ionel, p.getSpecialist());
        assertEquals(tunsoare, p.getServiciu());
        assertEquals(dt, p.getDataOra());
        assertEquals(
                Programare.STARE_IN_ASTEPTARE,
                p.getStare(),
                "La creare, starea trebuie să fie IN_ASTEPTARE"
        );


        assertTrue(client.vizualizeazaProgramari().contains(p),
                "Trebuie să fie în lista clientului");
        assertTrue(AplicatieProgramari.programariGlobale.contains(p),
                "Trebuie să fie în lista globală");
    }

    @Test
    void testVerificaDisponibilitateDupaCreare() {
        // Creare programare (confirmată implicit în liste)
        Programare p = client.creeazaProgramare(salon, ionel, tunsoare, dt);

        // Acum clientul și specialistul NU mai sunt disponibili la dt
        assertFalse(
                Programare.verificaDisponibilitate(client, ionel, dt),
                "După creare, nu mai trebuie să fie disponibil"
        );
    }

    @Test
    void testCreeazaProgramareAruncaLaConflictClient() {
        // Prima rezervare
        client.creeazaProgramare(salon, ionel, tunsoare, dt);

        // Încercăm din nou la aceeași oră cu alt serviciu sau specialist
        assertThrows(
                IllegalArgumentException.class,
                () -> client.creeazaProgramare(salon, ionel, tunsoare, dt),
                "Nu ar trebui să permită două programări la aceeași oră pentru același client"
        );
    }

    @Test
    void testCreeazaProgramareAruncaLaConflictSpecialist() {
        // Un alt client
        Client altClient = new Client(
                "Alt Client", "alt@salon.ro", "pass",
                new Abonament(SubscriptionType.STUDENT)
        );

        // Prima rezervare pentru specialistul Ionel
        client.creeazaProgramare(salon, ionel, tunsoare, dt);

        // Alt client încearcă cu același specialist la aceeași oră
        assertThrows(
                IllegalArgumentException.class,
                () -> altClient.creeazaProgramare(salon, ionel, tunsoare, dt),
                "Nu ar trebui să permită două programări la aceeași oră pentru același specialist"
        );
    }

    @Test
    void testConfirmareSchimbaStarea() {
        Programare p = client.creeazaProgramare(salon, ionel, tunsoare, dt);
        p.confirma();
        assertEquals(
                Programare.STARE_CONFIRMATA,
                p.getStare(),
                "Confirmare() trebuie să schimbe starea în CONFIRMATA"
        );
    }

    @Test
    void testConfirmareAruncaDacaNuEInAsteptare() {
        Programare p = client.creeazaProgramare(salon, ionel, tunsoare, dt);
        p.confirma();  // devine CONFIRMATA
        assertThrows(
                IllegalStateException.class,
                p::confirma,
                "Trebuie să arunce IllegalStateException dacă confirmi din nou"
        );
    }

    @Test
    void testAnulareSchimbaStarea() {
        Programare p = client.creeazaProgramare(salon, ionel, tunsoare, dt);
        p.anuleaza();
        assertEquals(
                Programare.STARE_ANULATA,
                p.getStare(),
                "Anulare() trebuie să schimbe starea în ANULATA"
        );
    }
}
