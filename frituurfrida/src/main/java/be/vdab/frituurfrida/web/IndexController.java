package be.vdab.frituurfrida.web;

import java.time.DayOfWeek;
import java.time.LocalDate;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import be.vdab.frituurfrida.valueobjects.Adres;
import be.vdab.frituurfrida.valueobjects.Gemeente;

// enkele imports
@Controller
@RequestMapping("/")
class IndexController {

	@GetMapping
	ModelAndView index(@CookieValue(name = "reedsBezocht", required = false) String reedsBezocht,
			HttpServletResponse response) {
		LocalDate vandaag = LocalDate.now();
		DayOfWeek weekdag = vandaag.getDayOfWeek();
		String openGesloten = weekdag == DayOfWeek.MONDAY || weekdag == DayOfWeek.THURSDAY ? "gesloten" : "open";
		Cookie cookie = new Cookie("reedsBezocht", "ja");
		cookie.setMaxAge(31_536_000);
		response.addCookie(cookie);
		ModelAndView modelAndView = new ModelAndView("index", "openGesloten", openGesloten)
				.addObject(new Adres("Grote markt", "7", new Gemeente("Brussel", 1000)));
		if (reedsBezocht != null) {
			modelAndView.addObject("reedsBezocht", true);
		}
		return modelAndView;
	}

}
