package be.vdab.pizzaluigi.entities;

import java.math.BigDecimal;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.SafeHtml;
import org.springframework.format.annotation.NumberFormat;

public class Pizza {

	private long id;
	@NotBlank
	@SafeHtml
	private String naam;
	@NotNull
	@Min(0)
	@NumberFormat(pattern = "0.00")
	private BigDecimal prijs;
	private boolean pikant;

	public Pizza(long id, String naam, BigDecimal prijs, boolean pikant) {

		this.id = id;
		this.naam = naam;
		this.prijs = prijs;
		this.pikant = pikant;
	}

	public Pizza(String naam, BigDecimal prijs, boolean pikant) {

		this.naam = naam;
		this.prijs = prijs;
		this.pikant = pikant;
	}

	public Pizza() {}

	public long getId() {

		return id;
	}

	public void setId(long id) {

		this.id = id;
	}

	public String getNaam() {

		return naam;
	}

	public void setNaam(String naam) {

		this.naam = naam;
	}

	public BigDecimal getPrijs() {

		return prijs;
	}

	public void setPrijs(BigDecimal prijs) {

		this.prijs = prijs;
	}

	public boolean isPikant() {

		return pikant;
	}

	public void setPikant(boolean pikant) {

		this.pikant = pikant;
	}

}
