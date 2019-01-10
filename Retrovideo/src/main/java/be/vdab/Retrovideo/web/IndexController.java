package be.vdab.Retrovideo.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import be.vdab.Retrovideo.services.FilmService;
import be.vdab.Retrovideo.services.GenreService;

@Controller
@RequestMapping("/")
public class IndexController {

	GenreService genreService;
	FilmService filmService;

	public IndexController(GenreService genreService, FilmService filmService) {

		this.genreService = genreService;
		this.filmService = filmService;
	}

	@GetMapping
	ModelAndView index() {

		final ModelAndView modelandview = new ModelAndView("index", "genres", genreService.findAll());
		return modelandview;
	}

	@GetMapping("{id}")
	ModelAndView indexFilmLijst(@PathVariable long id) {

		final ModelAndView modelandview = new ModelAndView("index", "genres", genreService.findAll());
		if (id > 0) {
			modelandview.addObject("films", filmService.findGenreId(id));
			modelandview.addObject("current", id);
		}
		return modelandview;
	}
}
