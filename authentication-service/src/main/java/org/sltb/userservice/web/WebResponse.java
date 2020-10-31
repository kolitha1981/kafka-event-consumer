package org.sltb.userservice.web;

public class WebResponse {

	private final Object responseData;
	private final int responseStatus;
	private final String message;

	private WebResponse(Object responseData, int responseStatus, String message) {
		this.responseData = responseData;
		this.responseStatus = responseStatus;
		this.message = message;
	}

	public static class WebResponseBuilder<T> {

		private T responseData;
		private int responseStatus;
		private String message;

		public WebResponseBuilder addResponseData(final T responseData) {
			this.responseData = responseData;
			return this;
		}

		public WebResponseBuilder addResponseStatus(final int status) {
			this.responseStatus = status;
			return this;
		}

		public WebResponseBuilder addMessage(final String message) {
			this.message = message;
			return this;
		}

		public WebResponse build() {
			return new WebResponse(this.responseData, this.responseStatus, this.message);
		}

	}

}
