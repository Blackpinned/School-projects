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
	
	public IndexController(GenreService genreService) {
		
		this.genreService = genreService;
	}

	@GetMapping
	ModelAndView index() {
		
		final ModelAndView modelandview = new ModelAndView("index", "genres", genreService.findAll());
		return modelandview;
	}

	// private static final String FILMFOTO_VIEW = "filmfoto";

	@GetMapping("{id}")
	ModelAndView index(@PathVariable long id) {

		final ModelAndView modelAndView = new ModelAndView("index", "genres", genreService.findAll());
		modelAndView.addObject("films", filmService.findGenreId(id));
		return modelAndView;
	}
}
