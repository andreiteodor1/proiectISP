

import model.*;
import manager.*;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Scanner;

public class Main {
    private static final Scanner scanner = new Scanner(System.in);
    private static final DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");

    public static void main(String[] args) {
        SalonManager manager = new SalonManager();
        SubscriptionService subService = new SubscriptionService();
        NotificationService notif = new NotificationService();

        // Creare salon și adăugare servicii
        Salon salon = new Salon("MERO Salon");
        manager.adaugaSalon(salon);

        salon.adaugaServiciu(new Serviciu("Tunsoare", 30, 50));
        salon.adaugaServiciu(new Serviciu("Vopsit păr", 90, 120));
        salon.adaugaServiciu(new Serviciu("Manichiură", 45, 70));

        // Adăugare specialiști pentru fiecare serviciu
        List<Serviciu> servicii = salon.getListaServicii();
        int idxAng = 1;
        for (Serviciu s : servicii) {
            // Nume generic pentru angajat
            String nume = "Specialist" + idxAng;
            String prenume = s.getNume().replaceAll("\\s+", "");
            String telefon = String.format("07%08d", idxAng);
            String email = String.format("%s.%s@salon.com", nume.toLowerCase(), prenume.toLowerCase());
            Angajat angajat = new Angajat(nume, prenume, telefon, email, s);
            salon.adaugaAngajat(angajat);
            idxAng++;
        }

        System.out.println("--- Bine ai venit la MERO Salon! ---");

        // Introducere date client
        System.out.print("Introdu numele tău: ");
        String numeClient = scanner.nextLine();
        System.out.print("Introdu prenumele tău: ");
        String prenumeClient = scanner.nextLine();
        System.out.print("Telefon: ");
        String telefonClient = scanner.nextLine();
        System.out.print("Email: ");
        String emailClient = scanner.nextLine();

        // Alege abonament
        System.out.println("Alege un abonament:");
        for (Abonament a : Abonament.values()) {
            System.out.println("- " + a);
        }
        System.out.print("Abonament (nume): ");
        Abonament abonament = Abonament.valueOf(scanner.nextLine().toUpperCase());

        Client client = new Client(numeClient, prenumeClient, telefonClient, emailClient, abonament);
        client.afisare();

        // Alege serviciu
        System.out.println("Servicii disponibile:");
        for (int i = 0; i < servicii.size(); i++) {
            System.out.printf("%d) %s%n", i+1, servicii.get(i));
        }
        System.out.print("Selectează serviciul (număr): ");
        int idx = Integer.parseInt(scanner.nextLine()) - 1;
        Serviciu serviciuAles = servicii.get(idx);
        client.selecteazaServiciu(serviciuAles);

        // Introdu date rezervare
        System.out.print("Introdu data și ora rezervării (YYYY-MM-DD HH:mm): ");
        LocalDateTime data = LocalDateTime.parse(scanner.nextLine(), dtf);

        // Alege angajat specializat pentru serviciul ales
        List<Angajat> angajati = salon.getListaAngajati();
        System.out.println("Angajați disponibili pentru " + serviciuAles.getNume() + ":");
        for (int i = 0; i < angajati.size(); i++) {
            Angajat a = angajati.get(i);
            if (a.getSpecialitate().equals(serviciuAles)) {
                System.out.printf("%d) %s %s%n", i+1, a.getNume(), a.getPrenume());
            }
        }
        System.out.print("Selectează angajatul (număr): ");
        idx = Integer.parseInt(scanner.nextLine()) - 1;
        Angajat angajatAles = angajati.get(idx);

        // Creează și confirmă rezervarea
        if (salon.verificaDisponibilitate(angajatAles, data)) {
            Rezervare rezervare = new Rezervare(data, client, angajatAles);
            rezervare.confirmare();
            salon.adaugaRezervare(rezervare);
            System.out.println(rezervare.genereazaConfirmare());
            notif.notificaClient(client, rezervare.genereazaConfirmare());
        } else {
            System.out.println("Ne pare rău, angajatul nu este disponibil la această oră.");
        }

        // Rezumat
        System.out.println("\n--- Rezumat Comandă ---");
        client.afisare();
        salon.vizualizeazaProgramari();

        scanner.close();
    }
}