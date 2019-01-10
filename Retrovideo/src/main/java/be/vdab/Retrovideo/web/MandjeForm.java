package be.vdab.Retrovideo.web;

import java.util.List;

public class MandjeForm {
	
	public List<Long> filmIds;
	
	public List<Long> getFilmIds() {
		
		return filmIds;
	}
	
	public void setFilmIds(List<Long> filmIds) {
		
		this.filmIds = filmIds;
	}
	
	public void addFilmId(long filmId) {
		
		filmIds.add(filmId);
	}
}
