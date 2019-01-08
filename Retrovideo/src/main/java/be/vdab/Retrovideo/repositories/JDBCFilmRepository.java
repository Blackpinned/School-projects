package be.vdab.Retrovideo.repositories;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.dao.IncorrectResultSizeDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import be.vdab.Retrovideo.enitities.Film;
import be.vdab.Retrovideo.exceptions.FilmNietGevondenException;

@Repository
public class JDBCFilmRepository implements FilmRepository {
	
	private final RowMapper<Film> filmRowMapper = (resultSet, rowNum) -> new Film(
			resultSet.getLong("id"), resultSet.getLong("genreid"), resultSet.getString("titel"), resultSet.getInt("voorraad"), resultSet.getInt("gereserveerd"), resultSet.getBigDecimal("prijs")
	);
	private final JdbcTemplate filmtemplate;
	private static final String UPDATE_FILM =
			"update films set voorraad = :voorraad, gereserveerd = :gereserveerd where id = :id";
	private static final String SELECT_ALL =
			"select id, genreid, titel, voorraad, gereserveerd, prijs from films order by id";
	private static final String READ =
			"select id, genreid, titel, voorraad, gereserveerd, prijs from films where id = :id";
	private static final String SELECT_GENREID =
			"select id, genreid, titel, voorraad, gereserveerd, prijs from films where genreid = :genreid order by titel";
	
	public JDBCFilmRepository(JdbcTemplate filmtemplate) {

		this.filmtemplate = filmtemplate;
	}
	
	@Override
	public void update(Film film) {

		final Map<String, Object> parameters = new HashMap<>();
		parameters.put("voorraad", film.getVoorraad());
		parameters.put("gereserveerd", film.getGereserveerd());
		parameters.put("id", film.getId());
		if (filmtemplate.update(UPDATE_FILM, parameters) == 0) {
			throw new FilmNietGevondenException();
		}
	}
	
	@Override
	public Optional<Film> read(long id) {

		try {
			return Optional.of(filmtemplate.queryForObject(READ, filmRowMapper, Collections.singletonMap("id", id)));
		} catch (final IncorrectResultSizeDataAccessException ex) {
			return Optional.empty();
		}
	}
	
	@Override
	public List<Film> findAll() {

		return filmtemplate.query(SELECT_ALL, filmRowMapper);
	}

	@Override
	public List<Film> findGenreId(long genreid) {
		
		return filmtemplate.query(SELECT_GENREID, filmRowMapper, genreid);
	}

}
