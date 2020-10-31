package org.sltb.transportmanagement.service;

import java.util.Date;

import org.sltb.transportmanagement.domain.Journey;

public interface JourneyManagementService {

	Journey create(String startingZone, String endingZone, Long userId, Date startTime);

	Double end(Long journeyId, Long userId, String startZone, String endZone);

}
