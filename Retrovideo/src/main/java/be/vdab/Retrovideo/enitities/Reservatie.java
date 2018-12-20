package be.vdab.Retrovideo.enitities;

import java.sql.Date;

public class Reservatie {

	private final int klantId;
	private final int filmId;
	private final Date reservatie;
	
	public Reservatie(int klantId, int filmId, Date reservatie) {

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

	public Date getReservatie() {

		return reservatie;
	}
	
}
