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

	@GetMapping("{id}")
	ModelAndView index(@PathVariable long id) {
		
		final ModelAndView modelandview = new ModelAndView("film", "film", filmService.read(id));
		return modelandview;
	}
}
