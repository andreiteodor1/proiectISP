package model;

public abstract class Persoana {
	private String nume;
	private String prenume;
	private String nrTel;
	private String mail;

	public Persoana(String nume, String prenume, String nrTel, String mail) {
		this.nume = nume;
		this.prenume = prenume;
		this.nrTel = nrTel;
		this.mail = mail;
	}

	public String getNume() { return nume; }
	public String getPrenume() { return prenume; }
	public String getNrTel() { return nrTel; }
	public String getMail() { return mail; }

	public void setNume(String nume) { this.nume = nume; }
	public void setPrenume(String prenume) { this.prenume = prenume; }
	public void setNrTel(String nrTel) { this.nrTel = nrTel; }
	public void setMail(String mail) { this.mail = mail; }

	public void afisare() {
		System.out.println("Nume: " + nume);
		System.out.println("Prenume: " + prenume);
		System.out.println("Telefon: " + nrTel);
		System.out.println("Email: " + mail);
	}
}