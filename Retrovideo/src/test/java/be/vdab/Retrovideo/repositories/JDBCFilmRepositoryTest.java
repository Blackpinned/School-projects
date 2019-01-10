package be.vdab.Retrovideo.repositories;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.math.BigDecimal;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.AbstractTransactionalJUnit4SpringContextTests;
import org.springframework.test.context.junit4.SpringRunner;

import be.vdab.Retrovideo.enitities.Film;
import be.vdab.Retrovideo.exceptions.FilmNietGevondenException;

@RunWith(SpringRunner.class)
@JdbcTest
@AutoConfigureTestDatabase(replace = Replace.NONE)
@Import(JDBCFilmRepository.class)
@Sql("/insertFilm.sql")
public class JDBCFilmRepositoryTest extends AbstractTransactionalJUnit4SpringContextTests {
	
	private static final String FILMS = "films";
	@Autowired
	private JDBCFilmRepository repository;
	
	@Test
	public void findAll() {
		
		final List<Film> films = repository.findAll();
		assertEquals(super.countRowsInTable(FILMS), films.size());
		
		long vorigeId = 0;
		for (final Film film : films) {
			assertTrue(film.getId() > vorigeId);
			vorigeId = film.getId();
		}
	}
	
	private long idVanTestFilm() {
		
		return super.jdbcTemplate.queryForObject("select id from films where titel = 'test'", Long.class);
	}
	
	@Test
	public void read() {
		
		assertEquals("test", repository.read(idVanTestFilm()).get().getTitel());
	}
	
	@Test
	public void readOnbestaandeFilm() {
		
		assertFalse(repository.read(-1).isPresent());
	}
	
	@Test
	public void update() {
		
		final long id = idVanTestFilm();
		final Film film = new Film(id, 2, "test", 5, 0, BigDecimal.ONE);
		repository.update(film);
		assertEquals(5, repository.read(idVanTestFilm()).get().getVoorraad());
	}
	
	@Test(expected = FilmNietGevondenException.class)
	public void updateOnbestaandePizza() {
		
		repository.update(new Film(-1, 2, "test", 5, 0, BigDecimal.ONE));
	}
}
