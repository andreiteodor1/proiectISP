package model;



import java.time.LocalDateTime;

public class Rezervare {
    public enum Status { PENDING, CONFIRMED, CANCELLED }

    private LocalDateTime dataOra;
    private Client client;
    private Angajat angajat;
    private Status status = Status.PENDING;

    public Rezervare(LocalDateTime dataOra, Client client, Angajat angajat) {
        this.dataOra = dataOra;
        this.client = client;
        this.angajat = angajat;
        client.adaugaRezervare(this);
    }

    public LocalDateTime getDataOra() { return dataOra; }
    public Client getClient() { return client; }
    public Angajat getAngajat() { return angajat; }
    public Status getStatus() { return status; }

    public void confirmare() { this.status = Status.CONFIRMED; }
    public void cancelare() { this.status = Status.CANCELLED; }

    public String genereazaConfirmare() {
        return String.format("Confirmare: %s cu %s la %s",
                client.getNume(), angajat.getNume(), dataOra);
    }

    public void afisare() {
        System.out.printf("Rezervare [%s]: %s - %s la %s%n",
                status, client.getNume(), angajat.getNume(), dataOra);
    }
}