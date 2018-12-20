package be.vdab.Retrovideo.repositories;

import java.util.List;
import java.util.Optional;

import be.vdab.Retrovideo.enitities.Genre;

public interface GenreRepository {

	Optional<Genre> read(long id);
	
	List<Genre> findAll();
}
