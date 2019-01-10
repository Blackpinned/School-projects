package be.vdab.Retrovideo.web;

import javax.validation.constraints.NotBlank;

public class KlantForm {

	@NotBlank
	String familienaam;

	public String getFamilienaam() {

		return familienaam;
	}

	public void setFamilienaam(String familienaam) {

		this.familienaam = familienaam;
	}
}
