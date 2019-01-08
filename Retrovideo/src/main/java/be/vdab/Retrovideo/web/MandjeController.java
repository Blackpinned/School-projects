package be.vdab.Retrovideo.web;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import be.vdab.Retrovideo.enitities.Film;
import be.vdab.Retrovideo.services.FilmService;

@Controller
@RequestMapping("/mandje")
public class MandjeController {

	Mandje mandje;
	FilmService filmService;

	public MandjeController(Mandje mandje, FilmService filmService) {

		this.mandje = mandje;
		this.filmService = filmService;
	}

	@GetMapping
	ModelAndView index() {
		
		return new ModelAndView("mandje", "mandje", maakFilmsVanFilmIds(mandje.getFilmIds()));
	}
	
	private List<Film> maakFilmsVanFilmIds(List<Long> filmIds) {

		final List<Film> films = new ArrayList<>(filmIds.size());
		for (final long id : filmIds) {
			filmService.read(id).ifPresent(film -> films.add(film));
		}
		return films;
	}
}
