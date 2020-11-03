package org.sltb.kafkaeventconsumerservice.events;

import java.util.Date;

import org.sltb.kafkaeventconsumerservice.payload.EventPayLoad;
import org.springframework.web.client.RestTemplate;

public abstract class PassengertEvent {

	private Date createdOn;
	private String createdBy;

	public PassengertEvent(Date createdOn, String createdBy) {
		this.createdOn = createdOn;
		this.createdBy = createdBy;
	}

	public Date getCreatedOn() {
		return createdOn;
	}

	public String getCreatedBy() {
		return createdBy;
	}

	protected Date valueOf(String payloadDate) {
		return new Date();
	}

	public abstract void execute(final RestTemplate restTemplate, String serverURI);

}
