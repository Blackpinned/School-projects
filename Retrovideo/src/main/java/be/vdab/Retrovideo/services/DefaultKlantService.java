package be.vdab.Retrovideo.services;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import be.vdab.Retrovideo.enitities.Klant;
import be.vdab.Retrovideo.repositories.KlantRepository;

@Service
@Transactional(
		readOnly = true,
		isolation = Isolation.READ_COMMITTED
)
public class DefaultKlantService implements KlantService {
	
	private final KlantRepository klantRepository;
	
	public DefaultKlantService(KlantRepository klantRepository) {
		
		this.klantRepository = klantRepository;
	}
	
	@Override
	public Optional<Klant> read(long id) {
		
		return klantRepository.read(id);
	}
	
	@Override
	public List<Klant> findAll() {
		
		return klantRepository.findAll();
	}
	
	@Override
	public List<Klant> findOpFamilieNaam(String FNInput) {
		
		return klantRepository.findOpFamilieNaam(FNInput);
	}
	
}
