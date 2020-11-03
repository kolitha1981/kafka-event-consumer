package org.sltb.transportmanagement.core;

import java.util.Date;

public class CreateJourneyRequest {

	private String startingZone;
	private String endingZone;
	private Long userId;
	private Date startTime;

	public CreateJourneyRequest() {

	}

	public CreateJourneyRequest(String startingZone, String endingZone, Long userId, Date startTime) {
		this.startingZone = startingZone;
		this.endingZone = endingZone;
		this.userId = userId;
		this.startTime = startTime;
	}

	public String getStartingZone() {
		return startingZone;
	}

	public String getEndingZone() {
		return endingZone;
	}

	public Long getUserId() {
		return userId;
	}

	public Date getStartTime() {
		return startTime;
	}	

}
