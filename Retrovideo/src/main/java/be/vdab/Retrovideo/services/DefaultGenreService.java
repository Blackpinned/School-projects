package be.vdab.Retrovideo.services;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import be.vdab.Retrovideo.enitities.Genre;
import be.vdab.Retrovideo.repositories.GenreRepository;

@Service
@Transactional(
		readOnly = true,
		isolation = Isolation.READ_COMMITTED
)
public class DefaultGenreService implements GenreService {
	
	private final GenreRepository genreRepositry;

	public DefaultGenreService(GenreRepository genreRepositry) {

		this.genreRepositry = genreRepositry;
	}
	
	@Override
	public Optional<Genre> read(long id) {

		return genreRepositry.read(id);
	}
	
	@Override
	public List<Genre> findAll() {

		return genreRepositry.findAll();
	}

}
