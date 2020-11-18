package org.sltb.transportmanagement.domain;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

public class Journey implements Serializable {
	
	private Long journayId;
	private String startingZone;
	private String endingZone;
	private Date startTime;
	private Date endTime;
	private Double amount;
	private JourneyState journeyState;
	private Long userId;
	
	public Journey(final Long journayId, final String startingZone, final String endingZone, 
			final Long userId, final Date startTime) {
		this.journayId = journayId;
		this.startingZone = startingZone;
		this.endingZone = endingZone;
		this.userId = userId;
		this.startTime = startTime;
		this.journeyState = JourneyState.STARTED;
	}

	public Double getAmount() {
		return amount;
	}

	public void setAmount(Double amount) {
		this.amount = amount;
	}

	public JourneyState getJourneyState() {
		return journeyState;
	}

	public void setJourneyState(JourneyState journeyState) {
		this.journeyState = journeyState;
	}

	public Long getJournayId() {
		return journayId;
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

	public Date getEndTime() {
		return endTime;
	}

	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}

	public Date getStartTime() {
		return startTime;
	}

	@Override
	public int hashCode() {
		return 31 * (((startTime == null) ? 0 : startTime.hashCode()) + 31) + ((userId == null) ? 0 : userId.hashCode());
	}

	@Override
	public boolean equals(Object obj) {
		if (obj == this)
			return true;
		if (obj == null || getClass() != obj.getClass())
			return false;
		Journey other = (Journey) obj;
		if (startTime == null) {
			if (other.startTime != null)
				return false;
		} else if (!startTime.equals(other.startTime))
			return false;
		if (userId == null) {
			if (other.userId != null)
				return false;
		} else if (!userId.equals(other.userId))
			return false;
		return true;
	}

	


}
