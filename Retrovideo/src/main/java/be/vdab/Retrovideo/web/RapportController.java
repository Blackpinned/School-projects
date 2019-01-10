package be.vdab.Retrovideo.web;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import be.vdab.Retrovideo.enitities.Film;
import be.vdab.Retrovideo.enitities.Reservatie;
import be.vdab.Retrovideo.services.FilmService;
import be.vdab.Retrovideo.services.ReservatieService;

@Controller
@RequestMapping("/rapport")
public class RapportController {

	Mandje mandje;
	FilmService filmService;
	ReservatieService reservatieService;

	public RapportController(Mandje mandje, FilmService filmService, ReservatieService reservatieService) {

		this.mandje = mandje;
		this.filmService = filmService;
		this.reservatieService = reservatieService;
	}
	
	@GetMapping("{klantId}")
	ModelAndView rapport(@PathVariable long klantId) {
		
		final List<Film> filmsInMandje = maakFilmsVanFilmIds(mandje.getFilmIds());
		
		List<String> fouten = new ArrayList<>();
		
		for (Film film : filmsInMandje) {

			System.out.println(film.getBeschikbaar());
			System.out.println(film.getVoorraad());

			if (film.getVoorraad() > film.getGereserveerd()) {
				
				reservatieService
						.create(new Reservatie(klantId, film.getId(), new Timestamp(System.currentTimeMillis())));
				
				film.setGereserveerd(film.getGereserveerd() + 1);
				filmService.update(film);
				
				for (Iterator<Long> iterator = mandje.getFilmIds().iterator(); iterator.hasNext();) {
					iterator.next();
					iterator.remove();
				}
				
			} else {
				
				fouten.add(film.getTitel());
			}
		}
		
		return new ModelAndView("rapport", "fouten", fouten);
	}
	
	public List<Film> maakFilmsVanFilmIds(List<Long> filmIds) {

		final List<Film> films = new ArrayList<>(filmIds.size());
		for (final long id : filmIds) {
			filmService.read(id).ifPresent(film -> films.add(film));
		}
		return films;
	}
}
