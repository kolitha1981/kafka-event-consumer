package org.sltb.journeymanagement.service;

import java.util.Date;

import org.sltb.journeymanagement.domain.Journey;

public interface JournayManagementService {

	Journey create(String startingZone, String endingZone, Long userId, Date startTime);

	Double end(Long journeyId, Long userId, String startZone, String endZone);

}
