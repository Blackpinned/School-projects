package be.vdab.Retrovideo.repositories;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.Iterator;
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

import be.vdab.Retrovideo.enitities.Genre;

@RunWith(SpringRunner.class)
@JdbcTest
@AutoConfigureTestDatabase(replace = Replace.NONE)
@Import(JDBCGenreRepository.class)
@Sql("/insertGenre.sql")
public class JDBCGenreRepositoryTest extends AbstractTransactionalJUnit4SpringContextTests {
	
	private static final String GENRES = "genres";
	@Autowired
	private JDBCGenreRepository repository;
	
	@Test
	public void findAll() {
		
		final List<Genre> genres = repository.findAll();
		assertEquals(super.countRowsInTable(GENRES), genres.size());

		long vorigeId = 0;
		for (int i = 0; i < genres.size() - 1; i++) {
			assertTrue(genres.get(i).getId() > vorigeId);
			vorigeId = genres.get(i).getId();
		}
	}

	private long idVanTestGenre() {

		return super.jdbcTemplate.queryForObject("select id from genres where naam = 'test'", Long.class);
	}

	@Test
	public void read() {
		
		assertEquals("test", repository.read(idVanTestGenre()).get().getNaam());
	}

	@Test
	public void readOnbestaandGenre() {
		
		assertFalse(repository.read(-1).isPresent());
	}
	
}
