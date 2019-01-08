package be.vdab.Retrovideo.services;

import java.util.List;
import java.util.Optional;

import be.vdab.Retrovideo.enitities.Klant;

public interface KlantService {

	Optional<Klant> read(long id);
	
	List<Klant> findAll();
}
