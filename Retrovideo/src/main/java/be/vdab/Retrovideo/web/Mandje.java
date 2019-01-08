package be.vdab.Retrovideo.web;

import java.util.List;

public interface Mandje {
	
	void addFilmId(long id);
	
	void removeFilmId(long removeid);
	
	List<Long> getFilmIds();
	
}