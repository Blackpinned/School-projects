package be.vdab.Retrovideo.enitities;

import java.sql.Timestamp;

import javax.validation.constraints.NotNull;

public class Reservatie {
	
	@NotNull
	private final long klantId;
	@NotNull
	private final long filmId;
	@NotNull
	private final Timestamp reservatie;
	
	public Reservatie(long klantId, long filmId, Timestamp reservatie) {
		
		this.klantId = klantId;
		this.filmId = filmId;
		this.reservatie = reservatie;
	}
	
	public long getKlantId() {
		
		return klantId;
	}
	
	public long getFilmId() {
		
		return filmId;
	}
	
	public Timestamp getReservatie() {
		
		return reservatie;
	}
	
}
