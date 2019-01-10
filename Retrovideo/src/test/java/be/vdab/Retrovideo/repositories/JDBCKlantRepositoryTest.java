package be.vdab.Retrovideo.repositories;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

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

import be.vdab.Retrovideo.enitities.Klant;

@RunWith(SpringRunner.class)
@JdbcTest
@AutoConfigureTestDatabase(replace = Replace.NONE)
@Import(JDBCKlantRepository.class)
@Sql("/insertKlant.sql")
public class JDBCKlantRepositoryTest extends AbstractTransactionalJUnit4SpringContextTests {
	
	private static final String KLANTEN = "klanten";
	@Autowired
	private JDBCKlantRepository repository;
	
	@Test
	public void findAll() {
		
		final List<Klant> klanten = repository.findAll();
		assertEquals(super.countRowsInTable(KLANTEN), klanten.size());
		
		long vorigeId = 0;
		for (final Klant klant : klanten) {
			assertTrue(klant.getId() > vorigeId);
			vorigeId = klant.getId();
		}
	}
	
	private long idVanTestKlant() {
		
		return super.jdbcTemplate.queryForObject("select id from klanten where familienaam = 'testo'", Long.class);
	}
	
	@Test
	public void read() {
		
		assertEquals("tester testo", repository.read(idVanTestKlant()).get().getNaam());
	}
	
	@Test
	public void readOnbestaandeKlant() {
		
		assertFalse(repository.read(-1).isPresent());
	}
}
