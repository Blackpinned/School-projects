package be.vdab.Retrovideo.services;

import java.util.List;
import java.util.Optional;

import be.vdab.Retrovideo.enitities.Genre;

public interface GenreService {

	Optional<Genre> read(long id);
	
	List<Genre> findAll();
}
