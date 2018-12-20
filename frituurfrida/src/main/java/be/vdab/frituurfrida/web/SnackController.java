package be.vdab.frituurfrida.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import be.vdab.frituurfrida.services.SnackService;

@Controller
@RequestMapping("snacks")
class SnackController {

	private static final char[] ALFABET = "ABCDEFGHIJKLMNOPQRSTUVWXYZ".toCharArray();
	private final SnackService snackService;

	SnackController(SnackService snackService) {
		this.snackService = snackService;
	}

	private static final String ALFABET_VIEW = "alfabet";

	@GetMapping("alfabet")
	ModelAndView alfabet() {
		return new ModelAndView(ALFABET_VIEW, "alfabet", ALFABET);
	}

	@GetMapping(params = "beginletter")
	ModelAndView findByBeginletter(char beginletter) {
		return new ModelAndView(ALFABET_VIEW, "alfabet", ALFABET).addObject("snacks",
				snackService.findByBeginNaam(String.valueOf(beginletter)));
	}

}
