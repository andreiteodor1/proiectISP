

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

        // Setup inițial
        Salon salon = new Salon("MERO Salon");
        manager.adaugaSalon(salon);
        salon.adaugaServiciu(new Serviciu("Tunsoare", 30, 50));
        salon.adaugaServiciu(new Serviciu("Vopsit păr", 90, 120));
        salon.adaugaServiciu(new Serviciu("Manichiură", 45, 70));
        int idxAng = 1;
        for (Serviciu s : salon.getListaServicii()) {
            Angajat a = new Angajat(
                    "Spec" + idxAng,
                    s.getNume().replaceAll("\\s+", ""),
                    String.format("07%08d", idxAng),
                    String.format("spec%d@salon.com", idxAng),
                    s
            );
            salon.adaugaAngajat(a);
            idxAng++;
        }

        System.out.println("--- Selectează rolul ---");
        System.out.println("1) Client");
        System.out.println("2) Administrator");
        System.out.print("Rol: ");
        int rol = Integer.parseInt(scanner.nextLine());

        if (rol == 1) {
            // Flux client complet cu meniu
            System.out.println(" --- Modul Client ---");
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
            for (Abonament a : Abonament.values()) System.out.println("- " + a);
            System.out.print("Abonament (nume): ");
            Abonament abonament = Abonament.valueOf(scanner.nextLine().toUpperCase());

            Client client = new Client(numeClient, prenumeClient, telefonClient, emailClient, abonament);
            client.afisare();

            // Buclă pentru operații client
            boolean exitClient = false;
            Rezervare ultimaRezervare = null;
            while (!exitClient) {
                System.out.println("1) Fă rezervare");
                System.out.println("2) Vizualizează programări");
                System.out.println("3) Anulează ultima rezervare");
                System.out.println("0) Ieșire client");
                System.out.print("Opțiune: ");
                int optClient = Integer.parseInt(scanner.nextLine());
                switch (optClient) {
                    case 1:
                        // Fă rezervare
                        List<Serviciu> servicii = salon.getListaServicii();
                        System.out.println("Servicii disponibile:");
                        for (int i = 0; i < servicii.size(); i++) {
                            System.out.printf("%d) %s%n", i+1, servicii.get(i));
                        }
                        System.out.print("Selectează serviciul (număr): ");
                        int idx = Integer.parseInt(scanner.nextLine()) - 1;
                        Serviciu serviciuAles = servicii.get(idx);
                        client.selecteazaServiciu(serviciuAles);

                        System.out.print("Introdu data și ora rezervării (YYYY-MM-DD HH:mm): ");
                        LocalDateTime data = LocalDateTime.parse(scanner.nextLine(), dtf);

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

                        if (salon.verificaDisponibilitate(angajatAles, data)) {
                            Rezervare rezervare = new Rezervare(data, client, angajatAles);
                            rezervare.confirmare();
                            salon.adaugaRezervare(rezervare);
                            ultimaRezervare = rezervare;
                            System.out.println(rezervare.genereazaConfirmare());
                            notif.notificaClient(client, rezervare.genereazaConfirmare());
                        } else {
                            System.out.println("Ne pare rău, angajatul nu este disponibil la această oră.");
                        }
                        break;
                    case 2:
                        // Vizualizează programări
                        System.out.println(" Programările tale:");
                        client.getRezervari().forEach(Rezervare::afisare);
                        break;
                    case 3:
                        // Anulează ultima rezervare
                        if (ultimaRezervare != null) {
                            client.anuleazaRezervare(ultimaRezervare);
                            salon.stergeRezervare(ultimaRezervare);
                            System.out.println("Ultima rezervare a fost anulată.");
                            ultimaRezervare = null;
                        } else {
                            System.out.println("Nu există rezervări de anulată.");
                        }
                        break;
                    case 0:
                        exitClient = true;
                        break;
                    default:
                        System.out.println("Opțiune invalidă.");
                }
            }
        } else if (rol == 2) {
            boolean running = true;
            while (running) {
                System.out.println("\n--- Meniu Administrator ---");
                System.out.println("1) Adaugă salon");
                System.out.println("2) Șterge salon");
                System.out.println("3) Adaugă angajat");
                System.out.println("4) Șterge angajat");
                System.out.println("5) Vezi saloane");
                System.out.println("6) Vezi angajați cu salon");
                System.out.println("0) Iesire");
                System.out.print("Opțiune: ");
                int opt = Integer.parseInt(scanner.nextLine());
                switch (opt) {
                    case 1:
                        System.out.print("Nume salon: ");
                        manager.adaugaSalon(new Salon(scanner.nextLine()));
                        System.out.println("Salon adăugat.");
                        break;
                    case 2:
                        System.out.print("Nume salon de șters: ");
                        manager.stergeSalon(scanner.nextLine());
                        System.out.println("Salon șters dacă a existat.");
                        break;
                    case 3:
                        System.out.print("Salon țintă: ");
                        Salon s3 = manager.gasesteSalon(scanner.nextLine());
                        if (s3 != null) {
                            System.out.print("Nume angajat: "); String na = scanner.nextLine();
                            System.out.print("Prenume: ");   String pa = scanner.nextLine();
                            System.out.print("Telefon: ");   String ta = scanner.nextLine();
                            System.out.print("Email: ");     String ea = scanner.nextLine();
                            System.out.print("Nume serviciu: ");
                            String sn = scanner.nextLine();
                            Serviciu srv = s3.getListaServicii().stream()
                                    .filter(x -> x.getNume().equalsIgnoreCase(sn))
                                    .findFirst().orElse(null);
                            if (srv != null) {
                                s3.adaugaAngajat(new Angajat(na, pa, ta, ea, srv));
                                System.out.println("Angajat adăugat.");
                            } else {
                                System.out.println("Serviciu inexistent.");
                            }
                        } else {
                            System.out.println("Salon inexistent.");
                        }
                        break;
                    case 4:
                        System.out.print("Salon țintă: ");
                        Salon s4 = manager.gasesteSalon(scanner.nextLine());
                        if (s4 != null) {
                            System.out.print("Telefon angajat de șters: ");
                            String telS = scanner.nextLine();
                            s4.getListaAngajati().removeIf(a -> a.getNrTel().equals(telS));
                            System.out.println("Angajat(s) șters(i) după telefon.");
                        } else {
                            System.out.println("Salon inexistent.");
                        }
                        break;
                    case 5:
                        manager.consultareSaloane();
                        break;
                    case 6:
                        System.out.println("Angajați și salon:");
                        for (Salon sal : manager.getSaloane()) {
                            for (Angajat ang : sal.getListaAngajati()) {
                                System.out.printf("%s - %s %s%n",
                                        sal.getDenumire(), ang.getNume(), ang.getPrenume());
                            }
                        }
                        break;
                    case 0:
                        running = false;
                        break;
                    default:
                        System.out.println("Opțiune invalidă.");
                }
            }
        } else {
            System.out.println("Rol invalid.");
        }

        scanner.close();
    }
}