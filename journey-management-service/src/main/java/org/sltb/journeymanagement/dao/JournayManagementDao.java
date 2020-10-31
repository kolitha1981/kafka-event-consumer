package org.sltb.journeymanagement.dao;

import java.util.Date;

import org.sltb.journeymanagement.domain.Journey;

public interface JournayManagementDao {
	
	Journey create(String startingZone, String endingZone, 
			Long userId, Date startTime);
	
	boolean end(Long journeyId, Double amount, Date endDateTime);
	
	Double feeBetween(String startZone, String endZone);

}
