package org.sltb.kafkaeventconsumerservice.payload;

public class PayLoadLongFormatter implements PayLoadValueFormatter<Long>{
	
	public static final PayLoadLongFormatter LONG_FORMATTER = new PayLoadLongFormatter();
	
	private PayLoadLongFormatter() {
	}

	@Override
	public Long toValue(String payLoadValue) {
		return Long.valueOf(payLoadValue);
	}

	@Override
	public String toPayloadValue(Long dataType) {
		return String.valueOf(dataType);
	}

}
