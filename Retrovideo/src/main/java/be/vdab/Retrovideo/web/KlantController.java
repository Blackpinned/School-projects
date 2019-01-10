package be.vdab.Retrovideo.web;

import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import be.vdab.Retrovideo.repositories.FilmRepository;
import be.vdab.Retrovideo.repositories.KlantRepository;

@Controller
@RequestMapping("/klanten")
public class KlantController {

	KlantRepository klantrepository;
	FilmRepository filmrepository;
	Mandje mandje;

	public KlantController(KlantRepository klantrepository, FilmRepository filmrepository, Mandje mandje) {
		
		this.klantrepository = klantrepository;
		this.filmrepository = filmrepository;
		this.mandje = mandje;
	}

	@GetMapping
	ModelAndView klanten() {

		KlantForm form = new KlantForm();
		return new ModelAndView("klanten").addObject(form);
	}

	@GetMapping(params = "familienaam")
	ModelAndView findKlanten(@Valid KlantForm form, BindingResult bindingResult) {

		if (!bindingResult.hasErrors()) {
			final String fuzzyFamilienaam = "%" + form.familienaam + "%";
			ModelAndView modelandview = new ModelAndView("klanten");
			modelandview.addObject(new KlantForm());
			modelandview.addObject("klanten", klantrepository.findOpFamilieNaam(fuzzyFamilienaam));
			return modelandview;
		} else {

			return new ModelAndView("klanten").addObject(new KlantForm());
		}
	}
	
	@GetMapping("bevestigen/{klantId}")
	ModelAndView bevestigen(@PathVariable long klantId) {
		
		ModelAndView modelandview = new ModelAndView("bevestigen");
		klantrepository.read(klantId).ifPresent(klant -> {
			modelandview.addObject(klant);
		});

		int aantal = 0;

		if (!mandje.getFilmIds().isEmpty()) {
			
			for (long id : mandje.getFilmIds()) {
				aantal = aantal + 1;
			}
		}
		
		modelandview.addObject("aantal", aantal);

		return modelandview;
	}
}
