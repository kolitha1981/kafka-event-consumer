package org.sltb.transportmanagement.domain;

public enum CardStatus {

	ACTIVE(1), INACTIVE(0), ISSUE_REQUEST_PENDING(2);
	
	private int cardStatus;

	private CardStatus(final int cardStatus) {
		this.cardStatus = cardStatus;
	}

	public int getCardStatus() {
		return cardStatus;
	}	
	
}
