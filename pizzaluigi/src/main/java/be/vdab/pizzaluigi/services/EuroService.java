package be.vdab.pizzaluigi.services;

import java.math.BigDecimal;

import org.springframework.stereotype.Service;

@Service
public interface EuroService {

	BigDecimal naarDollar(BigDecimal euro);

}
