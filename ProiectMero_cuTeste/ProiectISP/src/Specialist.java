import java.util.UUID;

public class Specialist {
    private String idSpecialist;
    private String nume;

    public Specialist(String nume) {
        this.idSpecialist = UUID.randomUUID().toString();
        this.nume = nume;
    }

    public String getIdSpecialist() { return idSpecialist; }
    public String getNume() { return nume; }
}
