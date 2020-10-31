package org.sltb.journeymanagement.domain;

public enum JourneyState {

	STARTED(1), ENDED(2), INRPROGRESS(3), CANCELLED(4);

	private int journeyState;

	private JourneyState(final int journeyState) {
		this.journeyState = journeyState;
	}

	public int getJourneyState() {
		return journeyState;
	}	
	
}
