package org.sltb.transportmanagement.domain;

public enum JourneyState {

	STARTED(1), ENDED(2), INRPROGRESS(3), CANCELLED(4);

	private int journeyState;

	private JourneyState(final int journeyState) {
		this.journeyState = journeyState;
	}

	public int getJourneyState() {
		return journeyState;
	}

	public static JourneyState valueOf(int journeyState) {
		for (JourneyState journeyStateVal : values())
			if (journeyStateVal.getJourneyState() == journeyState)
				return journeyStateVal;
		return null;
	}

}
