package be.vdab.Retrovideo.repositories;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import org.springframework.dao.IncorrectResultSizeDataAccessException;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import be.vdab.Retrovideo.enitities.Genre;

@Repository
public class JDBCGenreRepository implements GenreRepository {

	private final RowMapper<Genre> genreRowMapper =
			(resultSet, rowNum) -> new Genre(resultSet.getLong("id"), resultSet.getString("naam"));
	private final NamedParameterJdbcTemplate template;
	private final String SELECT_ALL = "select id, naam from genres order by naam";
	private final String READ = "select id, naam from genres where id = :id";
	
	public JDBCGenreRepository(NamedParameterJdbcTemplate template) {
		
		this.template = template;
	}

	@Override
	public Optional<Genre> read(long id) {
		
		try {
			return Optional.of(template.queryForObject(READ, Collections.singletonMap("id", id), genreRowMapper));
		} catch (final IncorrectResultSizeDataAccessException ex) {
			return Optional.empty();
		}
	}

	@Override
	public List<Genre> findAll() {
		
		return template.query(SELECT_ALL, genreRowMapper);
	}

}
