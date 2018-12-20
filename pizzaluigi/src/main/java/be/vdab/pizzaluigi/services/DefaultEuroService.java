package be.vdab.pizzaluigi.services;

import java.math.BigDecimal;
import java.math.RoundingMode;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import be.vdab.pizzaluigi.exceptions.KoersClientException;
import be.vdab.pizzaluigi.restclients.KoersClient;

@Service
public class DefaultEuroService implements EuroService {

	private final KoersClient[] koersClients;
	private static final Logger LOGGER = LoggerFactory.getLogger(DefaultEuroService.class);

	DefaultEuroService(KoersClient[] koersClients) {
		this.koersClients = koersClients;
	}

	/* (non-Javadoc)
	 * @see be.vdab.pizzaluigi.services.EuroService#naarDollar(java.math.BigDecimal)
	 */
	@Override
	public BigDecimal naarDollar(BigDecimal euro) {
		for(KoersClient koersClient : koersClients) {
			try {
				return euro.multiply(koersClient.getDollarKoers()).setScale(2, RoundingMode.HALF_UP);
			} catch (KoersClientException e) {
				LOGGER.error("Kan dollar koers niet lezen", e);
			}
		}
		LOGGER.error("kan dollar koers van geen enkele bron lezen");
		return null;
	}
}
