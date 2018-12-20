package be.vdab.pizzaluigi.web;

import java.util.List;

public interface Mandje {

	void addPizzaId(long pizzaId);
	public List<Long> getPizzaIds();
}
