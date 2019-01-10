package be.vdab.Retrovideo.repositories;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.sql.Timestamp;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.junit4.AbstractTransactionalJUnit4SpringContextTests;
import org.springframework.test.context.junit4.SpringRunner;

import be.vdab.Retrovideo.enitities.Reservatie;

@RunWith(SpringRunner.class)
@JdbcTest
@AutoConfigureTestDatabase(replace = Replace.NONE)
@Import(JDBCReservatieRepository.class)
public class JDBCReservatieRepositoryTest extends AbstractTransactionalJUnit4SpringContextTests {
	
	private static final String RESERVATIES = "reservaties";
	@Autowired
	private JDBCReservatieRepository repository;
	
	@Test
	public void create() {
		
		final int aantalReservaties = super.countRowsInTable(RESERVATIES);
		final Timestamp toegevoegd = new Timestamp(System.currentTimeMillis());
		final Reservatie reservatie = new Reservatie(2, 2, toegevoegd);
		repository.create(reservatie);
		assertEquals(aantalReservaties + 1, this.countRowsInTable(RESERVATIES));
		assertEquals(1, super.countRowsInTableWhere(RESERVATIES, "reservatie=" + "'" + toegevoegd.toString() + "'"));
	}
}
