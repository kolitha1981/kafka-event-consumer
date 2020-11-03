package org.sltb.kafkaeventconsumerservice.payload;

import java.util.HashMap;
import java.util.Map;

import org.sltb.kafkaeventconsumerservice.events.EventType;

public class EventPayLoad {

	private EventType eventType;
	private Map<String, String> eventDataValues = new HashMap<>();

	public EventPayLoad(EventType eventType, Map<String, String> eventDataValues) {
		this.eventType = eventType;
		this.eventDataValues = eventDataValues;
	}

	public EventPayLoad() {
	}

	public EventType getEventType() {
		return eventType;
	}

	public void setEventType(EventType eventType) {
		this.eventType = eventType;
	}

	public Map<String, String> getEventDataValues() {
		return eventDataValues;
	}

	public void setEventDataValues(Map<String, String> eventDataValues) {
		this.eventDataValues = eventDataValues;
	}

}
