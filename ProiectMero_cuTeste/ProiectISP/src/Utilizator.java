public class Utilizator {
    private String nume;
    private String email;
    private String parola;

    public Utilizator(String nume, String email, String parola) {
        this.nume = nume;
        this.email = email;
        this.parola = parola;
    }

    public String getNume() { return nume; }
    public String getEmail() { return email; }
    public String getParola() { return parola; }

    void setNume(String nume) { this.nume = nume; }
    void setEmail(String email) { this.email = email; }
    void setParola(String parola) { this.parola = parola; }
}
