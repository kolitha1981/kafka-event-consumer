package org.sltb.transportmanagement;

import static org.junit.Assert.assertNotNull;

import org.junit.jupiter.api.Test;
import org.sltb.transportmanagement.service.JourneyManagementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest(classes = TransportManagementApplication.class)
public class JourneyManagementServiceImplTest {
	
	@Autowired
	private JourneyManagementService journeyManagementService;
	
	
	@Test
	public void testJourneyOfIds()
	{
		assertNotNull(journeyManagementService.journeyOf(1L));
		assertNotNull(journeyManagementService.journeyOf(2L));
	}

}
