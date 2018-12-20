package be.vdab.pizzaluigi.restclients;

import java.io.IOException;
import java.math.BigDecimal;
import java.net.URL;
import java.util.Scanner;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import be.vdab.pizzaluigi.exceptions.KoersClientException;

@Component
@Order(1)
public class FixerKoersClient implements KoersClient {

	private static final Logger LOGGER = LoggerFactory.getLogger(FixerKoersClient.class);

	private final URL url;

	FixerKoersClient(@Value("${fixerKoersURL}") URL url) {
		this.url = url;
	}
//	FixerKoersClient(){
//		try {
//			url = new URL(
//					"http://data.fixer.io/api/latest?access_key=b6eefe98ba6ffd4f87e51517a148a7b8&symbols=USD");
//		} catch (MalformedURLException e) {
//			String fout = "Fixer URL is verkeerd.";
//			LOGGER.error(fout, e);
//			throw new KoersClientException(fout);
//		}
//	}

	@Override
	public BigDecimal getDollarKoers() {
		try (Scanner scanner = new Scanner(url.openStream())) {
			String lijn = scanner.nextLine();
			int beginPositieKoers = lijn.indexOf("USD") + 5;
			int accoladePositie = lijn.indexOf('}', beginPositieKoers);
			LOGGER.info("Koers gelezen via Fixer");
			return new BigDecimal(lijn.substring(beginPositieKoers, accoladePositie));
		} catch (IOException | NumberFormatException ex) {
			String fout = "kan koers niet lezen via Fixer";
			LOGGER.error(fout, ex);
			throw new KoersClientException(fout);
		}
	}
}
