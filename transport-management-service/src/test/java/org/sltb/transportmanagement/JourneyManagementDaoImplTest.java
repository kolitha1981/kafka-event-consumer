package org.sltb.transportmanagement;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.util.Calendar;

import org.junit.jupiter.api.Test;
import org.sltb.transportmanagement.dao.JourneyManagementDao;
import org.sltb.transportmanagement.domain.Journey;
import org.sltb.transportmanagement.domain.JourneyState;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

@SpringBootTest(classes = TransportManagementApplication.class)
@Transactional
public class JourneyManagementDaoImplTest {
	
	@Autowired
	private JourneyManagementDao journeyManagementDao;
	
	@Test
	public void testCreateJourney()
	{
		assertNotNull(journeyManagementDao.create("Zone1", "Zone2", Long.valueOf(2), Calendar.getInstance().getTime()));
	}
	
	@Test
	public void testJourneyOfIds()
	{
		assertNotNull(journeyManagementDao.journeyOf(1L));
		assertNotNull(journeyManagementDao.journeyOf(2L));
	}
	
	@Test
	public void testEndJourney()
	{
		final Calendar currentDateTime = Calendar.getInstance();
		final Journey createdJourney = journeyManagementDao.create("Zone1", "Zone2", Long.valueOf(2), currentDateTime.getTime());
		assertNotNull(createdJourney);
		assertNotNull(createdJourney.getJournayId());
		currentDateTime.add(Calendar.HOUR, 2);
		journeyManagementDao.end(createdJourney.getJournayId(), 40.00d, currentDateTime.getTime());
		final Journey finshedJouney = journeyManagementDao.journeyOf(createdJourney.getJournayId());
		assertNotNull(finshedJouney);
		assertEquals(finshedJouney.getJourneyState(), JourneyState.ENDED);
	}

}
