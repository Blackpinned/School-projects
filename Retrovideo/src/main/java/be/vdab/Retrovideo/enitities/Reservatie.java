package be.vdab.Retrovideo.enitities;

import java.sql.Timestamp;

public class Reservatie {
	
	private final int klantId;
	private final int filmId;
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
