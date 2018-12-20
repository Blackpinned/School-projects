package be.vdab.pizzaluigi.web;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.when;

import java.math.BigDecimal;
import java.util.Optional;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.web.servlet.ModelAndView;

import be.vdab.pizzaluigi.entities.Pizza;
import be.vdab.pizzaluigi.services.EuroService;
import be.vdab.pizzaluigi.services.PizzaService;

@RunWith(MockitoJUnitRunner.class)
public class PizzaControllerTest {
	private PizzaController controller;

	@Mock
	private EuroService dummyEuroService;

	@Mock
	private PizzaService dummyPizzaService;

	@Before
	public void before() {
		when(dummyPizzaService.read(1)).thenReturn(Optional.of(new Pizza(1, "test", BigDecimal.ONE, true)));
		controller = new PizzaController(dummyEuroService, dummyPizzaService);
	}

	@Test
	public void pizzasWerktSamenMetPizzasDotJsp() {
		ModelAndView modelAndView = controller.pizzas();
		// werkt de method pizzas samen met de JSP (view) pizzas :
		assertEquals("pizzas", modelAndView.getViewName());
	}

	@Test
	public void pizzasGeeftPizzasDoor() {
		ModelAndView modelAndView = controller.pizzas();
		// geeft de method pizzas data door onder de naam pizzas aan de JSP:
		// het "model" onderdeel van ModelAndView gedraagt zich als een Map.
		// de key van een entry is de NAAM waaronder je data doorgeeft aan de JSP.
		// de value van een entry is de DATA ZELF die je doorgeeft aan de JSP.
		assertTrue(modelAndView.getModel().containsKey("pizzas"));
	}

	@Test
	public void pizzaWerktSamenMetPizzaDotJsp() {
		ModelAndView modelAndView = controller.pizza(1);
		assertEquals("pizza", modelAndView.getViewName());
	}

	@Test
	public void pizzaGeeftPizzaDoor() {
		ModelAndView modelAndView = controller.pizza(1);
		assertTrue(modelAndView.getModel().containsKey("pizza"));
	}

	@Test
	public void onbestaandePizza() {
		ModelAndView modelAndView = controller.pizza(-1);
		assertFalse(modelAndView.getModel().containsKey("pizza"));
	}
}
