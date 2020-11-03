package org.sltb.kafkaeventconsumerservice.events;

public enum EventType {
	
	CREATE_JOURNEY,	
	END_JOURNEY,
	REQUEST_NEW_CARD,
	INACTIVATE_CARD,
	VALIDATE_TRAVEL_REQUEST,
	RELOAD_ACCOUNT,
	CHECK_HISTORY;
}
