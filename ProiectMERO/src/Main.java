

import model.*;
import manager.*;
import java.time.LocalDateTime;

public class Main {
    public static void main(String[] args) {
        SalonManager manager = new SalonManager();
        SubscriptionService subService = new SubscriptionService();
        NotificationService notif = new NotificationService();

        // Creare salon și entități
        Salon salon = new Salon("MERO Salon");
        manager.adaugaSalon(salon);

        Serviciu tunsoare = new Serviciu("Tunsoare", 30, 50);
        salon.adaugaServiciu(tunsoare);

        Angajat ioana = new Angajat("Popescu", "Ioana", "0745123456", "ioana@ex.com", tunsoare);
        salon.adaugaAngajat(ioana);

        // Client alege abonament și serviciu
        Client ana = new Client("Ionescu", "Ana", "0722123456", "ana@ex.com", Abonament.LUNAR);
        subService.genereazaAbonament(ana);
        subService.consultareAbonamente();
        ana.selecteazaServiciu(tunsoare);

        // Make reservation
        if (salon.verificaDisponibilitate(ioana, LocalDateTime.of(2025, 5, 15, 10, 0))) {
            Rezervare r = new Rezervare(LocalDateTime.of(2025,5,15,10,0), ana, ioana);
            salon.adaugaRezervare(r);
            r.confirmare();
            notif.notificaClient(ana, r.genereazaConfirmare());
        }

        // Vizualizare date
        salon.consultaServicii();
        salon.consultaAngajati();
        salon.vizualizeazaProgramari();
    }
}