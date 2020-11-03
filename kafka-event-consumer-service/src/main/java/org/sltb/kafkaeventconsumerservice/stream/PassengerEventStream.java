package org.sltb.kafkaeventconsumerservice.stream;

import org.springframework.cloud.stream.annotation.Input;
import org.springframework.messaging.SubscribableChannel;

public interface PassengerEventStream {
	
	String PASSENGER_EVENTS_TOPIC_INPUT = "passenger-events";
	
	@Input(PASSENGER_EVENTS_TOPIC_INPUT)
	SubscribableChannel getPassengerEventSubscriber();

}
