import java.util.UUID;

public class Serviciu {
    private String idServiciu;
    private String nume;
    private double pret;              // nou

    public Serviciu(String nume, double pret) {
        this.idServiciu = UUID.randomUUID().toString();
        this.nume = nume;
        this.pret = pret;
    }

    public String getIdServiciu() {
        return idServiciu;
    }

    public String getNume() {
        return nume;
    }

    public double getPret() {         // getter pentru preț
        return pret;
    }
}
