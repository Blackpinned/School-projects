package be.vdab.Retrovideo.web;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
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
	ModelAndView mandje() {

		final ModelAndView modelandview =
				new ModelAndView("mandje", "mandje", maakFilmsVanFilmIds(mandje.getFilmIds()));
		modelandview.addObject("totaal", getTotaal(maakFilmsVanFilmIds(mandje.getFilmIds())));
		return modelandview;
	}
	
	@GetMapping("{filmId}")
	ModelAndView mandjeToevoegen(@PathVariable long filmId) {
		
		if (filmId > 0 && !mandje.getFilmIds().contains(filmId)) {
			mandje.addFilmId(filmId);
		}
		final ModelAndView modelandview =
				new ModelAndView("mandje", "mandje", maakFilmsVanFilmIds(mandje.getFilmIds()));
		modelandview.addObject("totaal", getTotaal(maakFilmsVanFilmIds(mandje.getFilmIds())));
		return modelandview;
	}
	
	private List<Film> maakFilmsVanFilmIds(List<Long> filmIds) {

		final List<Film> films = new ArrayList<>(filmIds.size());
		for (final long id : filmIds) {
			filmService.read(id).ifPresent(film -> films.add(film));
		}
		return films;
	}

	private BigDecimal getTotaal(List<Film> filmsInMandje) {

		final BigDecimal totaal = BigDecimal.ZERO;

		for (final Film film : filmsInMandje) {
			totaal.add(film.getPrijs());
		}

		return totaal;
	}
}
