package be.vdab.frituurfrida.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import be.vdab.frituurfrida.services.SausService;

@Controller
@RequestMapping("sauzen")
class SausController {

	private static final String SAUZEN_VIEW = "sauzen";
	private final SausService sausService;

	SausController(SausService sausService) {
		this.sausService = sausService;
	}

	@GetMapping
	ModelAndView sauzen() {
		return new ModelAndView(SAUZEN_VIEW, "sauzen", sausService.findAll());
	}

}
