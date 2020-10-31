package org.sltb.transportmanagement.controller;

import org.sltb.transportmanagement.service.JourneyManagementService;
import org.sltb.transportmanagement.service.PassengerManagementService;
import org.sltb.transportmanagement.web.WebResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class TransportManagementController {

	@Autowired
	private PassengerManagementService memberShipManagementService;
	@Autowired
	private JourneyManagementService journayManagementService;

	@RequestMapping(method = {
			RequestMethod.GET }, path = "/members/{userId}/cantravel", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	public WebResponse canTakeJouney(@PathVariable("userId") long userId) {
		boolean canTakJourney;
		try {
			canTakJourney = memberShipManagementService.canTakeJouney(userId);

		} catch (Exception e) {
			return new WebResponse.WebResponseBuilder<Boolean>().addResponseData(null)
					.addResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR.value()).addMessage(e.getMessage()).build();
		}
		return new WebResponse.WebResponseBuilder<Boolean>().addResponseData(canTakJourney)
				.addResponseStatus(HttpStatus.OK.value()).addMessage("").build();
	}

	@RequestMapping(method = {
			RequestMethod.PUT }, path = "/journeys/{journeyId}", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	public WebResponse endJourney(@PathVariable("journeyId") final Long journeyId,
			@RequestParam("userId") final Long userId, @RequestParam("startZone") final String startZone,
			@RequestParam("endZone") final String endZone) {
		Double remainingBalance;
		try {
			remainingBalance = journayManagementService.end(journeyId, userId, startZone, endZone);

		} catch (Exception e) {
			return new WebResponse.WebResponseBuilder<Double>().addResponseData(null)
					.addResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR.value()).addMessage(e.getMessage()).build();
		}
		return new WebResponse.WebResponseBuilder<Double>().addResponseData(remainingBalance)
				.addResponseStatus(HttpStatus.OK.value()).addMessage("").build();
	}

	@RequestMapping(method = {
			RequestMethod.POST }, path = "/memberships/cards/{userId}", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	public WebResponse requestCard(@PathVariable("userId") final Long userId) {
		Boolean cardRequestCompleted;
		try {
			cardRequestCompleted = memberShipManagementService.requestCard(userId);

		} catch (Exception e) {
			return new WebResponse.WebResponseBuilder<Boolean>().addResponseData(null)
					.addResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR.value()).addMessage(e.getMessage()).build();
		}
		return new WebResponse.WebResponseBuilder<Boolean>().addResponseData(cardRequestCompleted)
				.addResponseStatus(HttpStatus.OK.value()).addMessage("").build();
	}

}