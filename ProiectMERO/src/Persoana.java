

public class Persoana {
private String nume;
private String prenume;
private String nrTel;
private String mail;

public Persoana(String nume, String prenume, String nrTel, String mail)
{
	this.nume=nume;
	this.prenume=prenume;
	this.nrTel=nrTel;
	this.mail=mail;
	
}
public void afisare()
{
	System.out.println("Nume"+this.nume);
	System.out.println("Prenume"+this.prenume);
	System.out.println("NrTelefon"+this.nrTel);
	System.out.println("Mail"+this.mail);
}
public void modificare_Nume(String numeNou, String prenumeNou)
{
	this.nume=numeNou;
	this.prenume=prenumeNou;
}

}
