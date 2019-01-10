package be.vdab.Retrovideo.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import be.vdab.Retrovideo.services.FilmService;

@Controller
@RequestMapping("/film")
public class FilmController {

	FilmService filmService;
	Mandje mandje;

	public FilmController(FilmService filmService, Mandje mandje) {

		this.filmService = filmService;
		this.mandje = mandje;
	}

	@GetMapping("{filmid}")
	ModelAndView film(@PathVariable long filmid) {

		final ModelAndView modelandview = new ModelAndView("film");
		filmService.read(filmid).ifPresent(film -> modelandview.addObject(film));
		return modelandview;
	}
}
