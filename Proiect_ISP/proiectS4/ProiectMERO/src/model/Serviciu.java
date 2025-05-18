package model;


public class Serviciu {
    private String nume;
    private int durataMinute;
    private double pret;

    public Serviciu(String nume, int durataMinute, double pret) {
        this.nume = nume;
        this.durataMinute = durataMinute;
        this.pret = pret;
    }

    public String getNume() { return nume; }
    public int getDurataMinute() { return durataMinute; }
    public double getPret() { return pret; }

    public void afisare() {
        System.out.println(toString());
    }

    @Override
    public String toString() {
        return nume + " (" + durataMinute + " min, " + pret + " lei)";
    }
}