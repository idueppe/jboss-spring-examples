package io.crowdcode.vehicle.doa.jpa;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import io.crowdcode.vehicle.controller.spi.DBFixture;
import io.crowdcode.vehicle.dao.EngineDao;
import io.crowdcode.vehicle.domain.Engine;

import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "/applicationContext.xml" })
@Transactional
public class EngineJpaDaoTest {

	@Autowired
	private EngineDao dao;

	@Autowired
	private DBFixture dbFixture;

	@Before
	public void setUp() {
		dbFixture.createDefaultDataInDatabase();
	}

	@After
	public void tearDown() {
		dbFixture.removeAll();
	}

	@Test
	public void testDependencyInjectionConfiguration() {
		assertNotNull(dao);
	}

	@Test
	public void testFindAllEngines() {
		List<Engine> all = dao.findAll();
		assertNotNull("Results of EngineDao must not be null.", all);
		assertEquals("The number of found engines is wrong.", 4, all.size());
	}

	@Test
    public void testFindByManufacturerId() {
    	List<Engine> owned = dao.findByManufacturer(DBFixture.MANUFACTURER_BUGGATI);
    	assertNotNull("Results of EngineDao must not be null.", owned);
    	assertEquals("The number of found engines is wrong.", 2, owned.size());
    }

	@Test
	public void testFindById() {
		Engine engine = dao.find(dbFixture.firstEngine().getId());
		assertNotNull("Result of EngineDao must not be null.", engine);
	}
	
	@Test
	@Transactional
	public void testDeleteEngine() {
		Engine find = dao.find(dbFixture.firstEngine().getId());
		dao.delete(find);
		assertNull("Engine should be deleted.", dao.find(dbFixture.firstEngine().getId()));
	}

}
