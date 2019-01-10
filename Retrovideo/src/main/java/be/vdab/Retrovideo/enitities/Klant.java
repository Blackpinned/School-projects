package be.vdab.Retrovideo.enitities;

import javax.validation.constraints.NotBlank;

public class Klant {
	
	private final long id;
	@NotBlank
	private final String familienaam;
	@NotBlank
	private final String voornaam;
	@NotBlank
	private final String straatNummer;
	@NotBlank
	private final String postcode;
	@NotBlank
	private final String gemeente;
	
	public Klant(long id, String familienaam, String voornaam, String straatNummer, String postcode, String gemeente) {
		
		this.id = id;
		this.familienaam = familienaam;
		this.voornaam = voornaam;
		this.straatNummer = straatNummer;
		this.postcode = postcode;
		this.gemeente = gemeente;
	}
	
	public long getId() {
		
		return id;
	}
	
	public String getNaam() {
		
		return voornaam + " " + familienaam;
	}
	
	public String getStraatNummer() {
		
		return straatNummer;
	}
	
	public String getPostcode() {
		
		return postcode;
	}
	
	public String getGemeente() {
		
		return gemeente;
	}
	
}
