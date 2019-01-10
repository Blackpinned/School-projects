package be.vdab.Retrovideo.repositories;

import java.util.List;
import java.util.Optional;

import be.vdab.Retrovideo.enitities.Klant;

public interface KlantRepository {
	
	Optional<Klant> read(long id);
	
	List<Klant> findAll();
	
	List<Klant> findOpFamilieNaam(String FNInput);
}
