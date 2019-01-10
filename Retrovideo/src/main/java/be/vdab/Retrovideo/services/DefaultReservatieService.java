package be.vdab.Retrovideo.services;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import be.vdab.Retrovideo.enitities.Reservatie;
import be.vdab.Retrovideo.repositories.ReservatieRepository;

@Service
@Transactional(
		readOnly = true,
		isolation = Isolation.READ_COMMITTED
)
public class DefaultReservatieService implements ReservatieService {
	
	private final ReservatieRepository reservatieRepository;
	
	public DefaultReservatieService(ReservatieRepository reservatieRepository) {
		
		this.reservatieRepository = reservatieRepository;
	}
	
	@Override
	@Transactional(
			readOnly = false,
			isolation = Isolation.READ_COMMITTED
	)
	public void create(Reservatie reservatie) {
		
		reservatieRepository.create(reservatie);
		
	}
}
