package be.vdab.Retrovideo.repositories;

import java.util.HashMap;
import java.util.Map;

import javax.sql.DataSource;

import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

import be.vdab.Retrovideo.enitities.Reservatie;

@Repository
public class JDBCReservatieRepository implements ReservatieRepository {
	
	private final NamedParameterJdbcTemplate template;
	private final SimpleJdbcInsert insert;
	
	public JDBCReservatieRepository(NamedParameterJdbcTemplate template, DataSource dataSource) {
		
		this.template = template;
		this.insert = new SimpleJdbcInsert(dataSource);
		insert.withTableName("reservaties");
	}
	
	@Override
	public void create(Reservatie reservatie) {
		
		final Map<String, Object> kolomWaarden = new HashMap<>();
		kolomWaarden.put("klantid", reservatie.getKlantId());
		kolomWaarden.put("filmid", reservatie.getFilmId());
		kolomWaarden.put("reservatie", reservatie.getReservatie());
		insert.execute(kolomWaarden);
	}
	
}
