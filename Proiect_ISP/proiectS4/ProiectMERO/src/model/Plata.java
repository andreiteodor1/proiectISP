package model;


import java.time.LocalDateTime;

public class Plata {
    private double suma;
    private String metoda;
    private LocalDateTime dataOra;
    private boolean primita = false;

    public Plata(double suma, String metoda, LocalDateTime dataOra) {
        this.suma = suma;
        this.metoda = metoda;
        this.dataOra = dataOra;
    }

    public double getSuma() { return suma; }
    public String getMetoda() { return metoda; }
    public LocalDateTime getDataOra() { return dataOra; }
    public boolean isPrimita() { return primita; }

    public void confirmarePrimire() { this.primita = true; }

    public void afisare() {
        System.out.printf("Plata: %.2f lei via %s la %s [%s]%n",
                suma, metoda, dataOra, primita ? "PRIMITA" : "IN ASTEPTARE");
    }
}