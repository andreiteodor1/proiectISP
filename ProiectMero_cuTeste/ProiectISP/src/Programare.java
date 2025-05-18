import java.time.LocalDateTime;
import java.util.UUID;

public class Programare {
    private String idProgramare;
    private Client client;
    private Salon salon;
    private Specialist specialist;
    private Serviciu serviciu;
    private LocalDateTime dataOra;
    private String stare;

    public Programare(Client client, Salon salon, Specialist specialist,
                      Serviciu serviciu, LocalDateTime dataOra) {
        this.idProgramare = UUID.randomUUID().toString();
        this.client = client;
        this.salon = salon;
        this.specialist = specialist;
        this.serviciu = serviciu;
        this.dataOra = dataOra;
        this.stare = "IN_ASTEPTARE";
    }

    public String getIdProgramare() { return idProgramare; }
    public Client getClient() { return client; }
    public Salon getSalon() { return salon; }
    public Specialist getSpecialist() { return specialist; }
    public Serviciu getServiciu() { return serviciu; }
    public LocalDateTime getDataOra() { return dataOra; }
    public String getStare() { return stare; }

    public void anuleaza() {
        this.stare = "ANULATA";
    }
}
