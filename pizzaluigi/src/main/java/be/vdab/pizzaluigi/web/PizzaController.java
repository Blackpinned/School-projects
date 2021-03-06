package be.vdab.pizzaluigi.web;

import java.math.BigDecimal;
import java.util.List;

import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import be.vdab.pizzaluigi.entities.Pizza;
import be.vdab.pizzaluigi.services.EuroService;
import be.vdab.pizzaluigi.services.PizzaService;

@Controller
@RequestMapping("pizzas")
class PizzaController {
	
	private static final String PIZZAS_VIEW = "pizzas";
	private final EuroService euroService;
	private final PizzaService pizzaService;
	
	public PizzaController(EuroService euroService, PizzaService pizzaService) {
		
		this.euroService = euroService;
		this.pizzaService = pizzaService;
	}
	
	@GetMapping
	ModelAndView pizzas() {
		
		return new ModelAndView(PIZZAS_VIEW, "pizzas", pizzaService.findAll());
	}
	
	private static final String PIZZA_VIEW = "pizza";
	
	@GetMapping("{id}")
	ModelAndView pizza(@PathVariable long id) {
		
		final ModelAndView modelAndView = new ModelAndView(PIZZA_VIEW);
		pizzaService.read(id).ifPresent(pizza -> {
			modelAndView.addObject(pizza);
			modelAndView.addObject("inDollar", euroService.naarDollar(pizza.getPrijs()));
		});
		return modelAndView;
	}
	
	private static final String PRIJZEN_VIEW = "prijzen";
	
	@GetMapping("prijzen")
	ModelAndView prijzen() {
		
		return new ModelAndView(PRIJZEN_VIEW, "prijzen", pizzaService.findUniekePrijzen());
	}
	
	@GetMapping(params = "prijs")
	ModelAndView pizzasVanPrijs(BigDecimal prijs) {
		
		return new ModelAndView(PRIJZEN_VIEW, "pizzas", pizzaService.findByPrijs(prijs)).addObject("prijs", prijs)
				.addObject("prijzen", pizzaService.findUniekePrijzen());
	}
	
	private static final String VAN_TOT_PRIJS_VIEW = "vantotprijs";
	
	@GetMapping("vantotprijs")
	ModelAndView findVanTotPrijs() {
		
		final VanTotPrijsForm form = new VanTotPrijsForm();
		form.setVan(BigDecimal.ZERO);
		form.setTot(BigDecimal.ZERO);
		return new ModelAndView(VAN_TOT_PRIJS_VIEW).addObject(form);
	}
	
	@GetMapping(params =
	{"van", "tot"})
	ModelAndView findVanTotPrijs(@Valid VanTotPrijsForm form, BindingResult bindingResult) {
		
		final ModelAndView modelAndView = new ModelAndView(VAN_TOT_PRIJS_VIEW);
		if (bindingResult.hasErrors()) {
			return modelAndView;
		}
		final List<Pizza> pizzas = pizzaService.findByPrijsBetween(form.getVan(), form.getTot());
		if (pizzas.isEmpty()) {
			bindingResult.reject("geenPizzas");
		} else {
			modelAndView.addObject("pizzas", pizzas);
		}
		return modelAndView;
	}
	
	private static final String TOEVOEGEN_VIEW = "toevoegen";
	
	@GetMapping("toevoegen")
	ModelAndView toevoegen() {
		
		return new ModelAndView(TOEVOEGEN_VIEW).addObject(new Pizza());
	}
	
	private static final String REDIRECT_URL_NA_TOEVOEGEN = "redirect:/pizzas";
	
	@PostMapping("toevoegen")
	ModelAndView toevoegen(@Valid Pizza pizza, BindingResult bindingResult, RedirectAttributes redirectAttributes) {
		
		if (bindingResult.hasErrors()) {
			return new ModelAndView(TOEVOEGEN_VIEW);
		}
		pizzaService.create(pizza);
		redirectAttributes.addAttribute("boodschap", "Pizza toegevoegd");
		return new ModelAndView(REDIRECT_URL_NA_TOEVOEGEN);
	}
}

// Class zonder database injection
// class PizzaController {
// private static final String PIZZAS_VIEW = "pizzas";
// private final Map<Long, Pizza> pizzas = new LinkedHashMap<>(); // keys: pizza
// ids
// private final EuroService euroService;
//
// public PizzaController(EuroService euroService) {
// pizzas.put(1L, new Pizza(1, "Prosciutto", BigDecimal.valueOf(4), true));
// pizzas.put(2L, new Pizza(2, "Margherita", BigDecimal.valueOf(5), false));
// pizzas.put(3L, new Pizza(3, "Calzone", BigDecimal.valueOf(4), false));
// pizzas.put(4L, new Pizza(4, "Fungi & Olive", BigDecimal.valueOf(5), false));
// this.euroService = euroService;
// }
//
// @GetMapping
// ModelAndView pizzas() {
// return new ModelAndView(PIZZAS_VIEW, "pizzas", pizzas);
// }
//
// private static final String PIZZA_VIEW = "pizza";
//
// @GetMapping("{id}")
// ModelAndView pizza(@PathVariable long id) {
// ModelAndView modelAndView = new ModelAndView(PIZZA_VIEW);
// Pizza pizza = pizzas.get(id);
// modelAndView.addObject(pizza);
// modelAndView.addObject("inDollar", euroService.naarDollar(pizza.getPrijs()));
// return modelAndView;
// }
//
// private static final String PRIJZEN_VIEW = "prijzen";
//
// @GetMapping("prijzen")
// ModelAndView prijzen() {
// return new ModelAndView(PRIJZEN_VIEW, "prijzen",
// pizzas.values().stream().map(pizza ->
// pizza.getPrijs()).distinct().collect(Collectors.toSet()));
// }
//
// @GetMapping(params = "prijs")
// ModelAndView pizzasVanPrijs(BigDecimal prijs) {
// return new ModelAndView(PRIJZEN_VIEW, "pizzas",
// pizzas.values().stream().filter(pizza ->
// pizza.getPrijs().equals(prijs)).collect(Collectors.toList()))
// .addObject("prijs", prijs).addObject("prijzen", pizzas.values().stream()
// .map(pizza -> pizza.getPrijs()).distinct().collect(Collectors.toSet()));
// }
// }
