package org.sltb.transportmanagement;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.Calendar;

import org.junit.jupiter.api.Test;
import org.sltb.transportmanagement.dao.JourneyManagementDao;
import org.sltb.transportmanagement.domain.Journey;
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
		Journey createdJourney = journeyManagementDao
				.create("Zone1", "Zone2", Long.valueOf(2), Calendar.getInstance().getTime());
		assertNotNull(createdJourney);
		assertTrue(createdJourney.getJournayId() > 0);
	}

}
