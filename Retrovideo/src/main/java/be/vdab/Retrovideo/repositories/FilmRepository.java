package be.vdab.Retrovideo.repositories;

import java.util.List;
import java.util.Optional;

import be.vdab.Retrovideo.enitities.Film;

public interface FilmRepository {
	
	void update(Film film);
	
	Optional<Film> read(long id);
	
	List<Film> findAll();
	
	List<Film> findGenreId(long id);
	
}
