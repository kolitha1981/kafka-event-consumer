package org.sltb.kafkaeventconsumerservice.payload;

public interface PayLoadValueFormatter<T> {
	
	T toValue(String payLoadValue);
	
	String toPayloadValue(T dataType);

}
