package org.sltb.transportmanagement.dao;

import java.util.Date;

import org.sltb.transportmanagement.domain.Journey;

public interface JourneyManagementDao {
	
	Journey create(String startingZone, String endingZone, 
			Long userId, Date startTime);
	
	boolean end(Long journeyId, Double amount, Date endDateTime);
	
	Double feeBetween(String startZone, String endZone);

}
