package be.vdab.Retrovideo.services;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import be.vdab.Retrovideo.enitities.Film;
import be.vdab.Retrovideo.repositories.FilmRepository;

@Service
@Transactional(
		readOnly = true,
		isolation = Isolation.READ_COMMITTED
)
public class DefaultFilmService implements FilmService {
	
	private final FilmRepository filmRepository;
	
	public DefaultFilmService(FilmRepository filmRepository) {
		
		this.filmRepository = filmRepository;
	}
	
	@Override
	@Transactional(
			readOnly = false,
			isolation = Isolation.READ_COMMITTED
	)
	public void update(Film film) {
		
		filmRepository.update(film);
		
	}
	
	@Override
	public Optional<Film> read(long id) {
		
		return filmRepository.read(id);
	}
	
	@Override
	public List<Film> findAll() {
		
		return filmRepository.findAll();
	}
	
	@Override
	public List<Film> findGenreId(long id) {
		
		return filmRepository.findGenreId(id);
	}
	
}
