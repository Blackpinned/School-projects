package be.vdab.Retrovideo.repositories;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import org.springframework.dao.IncorrectResultSizeDataAccessException;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import be.vdab.Retrovideo.enitities.Klant;

@Repository
public class JDBCKlantRepository implements KlantRepository {
	
	private final RowMapper<Klant> klantRowMapper =
			(resultSet, rowNum) -> new Klant(resultSet.getLong("id"), resultSet.getString("familienaam"), resultSet.getString("voornaam"), resultSet.getString("straatNummer"), resultSet.getString("postcode"), resultSet.getString("gemeente"));
	private final NamedParameterJdbcTemplate template;
	private final String SELECT_ALL =
			"select id, familienaam, voornaam, straatNummer, postcode, gemeente from klanten order by id";
	private final String READ =
			"select id, familienaam, voornaam, straatNummer, postcode, gemeente from klanten where id = :id";
	
	public JDBCKlantRepository(NamedParameterJdbcTemplate template) {
		
		this.template = template;
	}

	@Override
	public Optional<Klant> read(long id) {

		try {
			return Optional.of(template.queryForObject(READ, Collections.singletonMap("id", id), klantRowMapper));
		} catch (final IncorrectResultSizeDataAccessException ex) {
			return Optional.empty();
		}
	}
	
	@Override
	public List<Klant> findAll() {

		return template.query(SELECT_ALL, klantRowMapper);
	}

}
