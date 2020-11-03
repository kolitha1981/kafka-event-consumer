package org.sltb.kafkaeventconsumerservice.payload;

import java.util.Date;

public class PayLoadDateFormatter implements PayLoadValueFormatter<Date> {

	public static final PayLoadDateFormatter DATE_FORMATTER = new PayLoadDateFormatter();

	private PayLoadDateFormatter() {

	}

	@Override
	public Date toValue(String payLoadValue) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String toPayloadValue(Date dataType) {
		return null;
	}

}
