package org.sltb.transportmanagement.dao;

import java.util.Date;
import java.util.List;

import org.sltb.transportmanagement.domain.Journey;

public interface JourneyManagementDao {
	
	Journey create(String startingZone, String endingZone, 
			Long userId, Date startTime);
	
	boolean end(Long journeyId, Double amount, Date endDateTime);
	
	Double feeBetween(String startZone, String endZone);
	
	List<Journey> historyOf(Long userId, int recordLimit);
	
	Journey journeyOf(Long journeyId);

}
