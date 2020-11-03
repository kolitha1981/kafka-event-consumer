package org.sltb.kafkaeventconsumerservice.stream;

import org.springframework.cloud.stream.annotation.EnableBinding;

@EnableBinding(PassengerEventStream.class)
public class PassengerEventConfig {

}
