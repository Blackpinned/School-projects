package be.vdab.Retrovideo.enitities;

import java.sql.Timestamp;

import javax.validation.constraints.NotNull;

public class Reservatie {

	@NotNull
	private final int klantId;
	@NotNull
	private final int filmId;
	@NotNull
	private final Timestamp reservatie;
	
	public Reservatie(int klantId, int filmId, Timestamp reservatie) {

		this.klantId = klantId;
		this.filmId = filmId;
		this.reservatie = reservatie;
	}

	public int getKlantId() {

		return klantId;
	}

	public int getFilmId() {

		return filmId;
	}

	public Timestamp getReservatie() {

		return reservatie;
	}
	
}
