package org.sltb.kafkaeventconsumerservice.stream;

import org.sltb.kafkaeventconsumerservice.events.PassengertEvent;
import org.sltb.kafkaeventconsumerservice.events.JourneyCreateEvent;
import org.sltb.kafkaeventconsumerservice.payload.EventPayLoad;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class PassengerEventsConsumer {

	@Autowired
	private RestTemplate restTemplate;
	@Value("${org.sltb.journeymanagement.service.uri}")
	private String jouneyManagementServerURI;

	@StreamListener(PassengerEventStream.PASSENGER_EVENTS_TOPIC_INPUT)
	public void processEvent(@Payload EventPayLoad eventPayLoad) {
		PassengertEvent passengertEvent;
		switch (eventPayLoad.getEventType()) {
			case CREATE_JOURNEY: {
				passengertEvent = JourneyCreateEvent.fromPayLoad(eventPayLoad);
				passengertEvent.execute(restTemplate, jouneyManagementServerURI);
			}
				break;
			default: {
	
			}
		}

	}

}
