package org.sltb.kafkaeventconsumerservice.events;

import java.net.URI;
import java.util.Date;
import java.util.Map;

import org.sltb.kafkaeventconsumerservice.payload.EventPayLoad;
import org.sltb.kafkaeventconsumerservice.payload.PayLoadDateFormatter;
import org.sltb.kafkaeventconsumerservice.payload.PayLoadLongFormatter;
import org.sltb.transportmanagement.core.CreateJourneyRequest;
import org.sltb.transportmanagement.core.WebResponse;
import org.springframework.http.HttpMethod;
import org.springframework.http.RequestEntity;
import org.springframework.web.client.RestTemplate;

public class JourneyCreateEvent extends PassengertEvent {

	public static final String MESSAGE_KEY_START_ZONE = "START-ZONE";
	public static final String MESSAGE_KEY_END_ZONE = "END-ZONE";
	public static final String MESSAGE_KEY_START_TIME = "START-TIME";
	public static final String MESSAGE_KEY_CRTEATED_ON = "END-TIME";
	public static final String MESSAGE_KEY_USER_ID = "USER-ID";
	public static final String MESSAGE_KEY_CREATED_BY = "CREATED-BY";

	private final String startingZone;
	private final String endingZone;
	private Date startTime;
	private Long userId;

	public static JourneyCreateEvent fromPayLoad(EventPayLoad eventPayLoad) {
		final Map<String, String> eventDataValues = eventPayLoad.getEventDataValues();
		return new JourneyCreateEvent(eventDataValues.get(MESSAGE_KEY_START_ZONE),
				eventDataValues.get(MESSAGE_KEY_END_ZONE),
				PayLoadLongFormatter.LONG_FORMATTER.toValue(eventDataValues.get(MESSAGE_KEY_USER_ID)),
				PayLoadDateFormatter.DATE_FORMATTER.toValue(eventDataValues.get(MESSAGE_KEY_START_TIME)),
				PayLoadDateFormatter.DATE_FORMATTER.toValue(eventDataValues.get(MESSAGE_KEY_CRTEATED_ON)),
				eventDataValues.get(MESSAGE_KEY_CREATED_BY));
	}

	private JourneyCreateEvent(final String startingZone, final String endingZone, final Long userId,
			final Date startTime, Date createdOn, String createdBy) {
		super(createdOn, createdBy);
		this.startingZone = startingZone;
		this.endingZone = endingZone;
		this.startTime = startTime;
		this.userId = userId;
	}

	public String getStartingZone() {
		return startingZone;
	}

	public String getEndingZone() {
		return endingZone;
	}

	public Date getStartTime() {
		return startTime;
	}

	public Long getUserId() {
		return userId;
	}

	@Override
	public int hashCode() {
		return 31
				* (31 * (((startTime == null) ? 0 : startTime.hashCode()) + 31)
						+ ((startingZone == null) ? 0 : startingZone.hashCode()))
				+ ((userId == null) ? 0 : userId.hashCode());
	}

	@Override
	public boolean equals(Object obj) {
		if (obj == this)
			return true;
		if (obj == null || getClass() != obj.getClass())
			return false;
		JourneyCreateEvent other = (JourneyCreateEvent) obj;
		if (startTime == null) {
			if (other.startTime != null)
				return false;
		} else if (!startTime.equals(other.startTime))
			return false;
		if (startingZone == null) {
			if (other.startingZone != null)
				return false;
		} else if (!startingZone.equals(other.startingZone))
			return false;
		if (userId == null) {
			if (other.userId != null)
				return false;
		} else if (!userId.equals(other.userId))
			return false;
		return true;
	}

	@Override
	public void execute(RestTemplate restTemplate, String serverURI) {
		final CreateJourneyRequest createJourneyRequest = new CreateJourneyRequest(this.startingZone, this.endingZone,
				this.userId, this.startTime);
		try {
			restTemplate.exchange(
					new RequestEntity<CreateJourneyRequest>(createJourneyRequest, HttpMethod.POST, new URI(serverURI)),
					WebResponse.class).getStatusCode();
		} catch (Exception e) {

		}

	}

}
