package be.vdab.pizzaluigi.valueobjects;

public class Persoon {

	private String voornaam;
	private String familienaam;
	private int aantalKinderen;
	private boolean gehuwd;
	private Adres adres;

	public Persoon(String voornaam, String familienaam, int aantalKinderen, boolean gehuwd, Adres adres) {
		super();
		this.voornaam = voornaam;
		this.familienaam = familienaam;
		this.aantalKinderen = aantalKinderen;
		this.gehuwd = gehuwd;
		this.adres = adres;
	}

	public Persoon() { // default constructor

	}

	public String getNaam() {
		return voornaam + " " + familienaam;
	}

	public String getVoornaam() {
		return voornaam;
	}

	public void setVoornaam(String voornaam) {
		this.voornaam = voornaam;
	}

	public String getFamilienaam() {
		return familienaam;
	}

	public void setFamilienaam(String familienaam) {
		this.familienaam = familienaam;
	}

	public int getAantalKinderen() {
		return aantalKinderen;
	}

	public void setAantalKinderen(int aantalKinderen) {
		this.aantalKinderen = aantalKinderen;
	}

	public boolean isGehuwd() {
		return gehuwd;
	}

	public void setGehuwd(boolean gehuwd) {
		this.gehuwd = gehuwd;
	}

	public Adres getAdres() {
		return adres;
	}
}
