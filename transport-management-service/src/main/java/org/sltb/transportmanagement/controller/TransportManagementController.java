package org.sltb.transportmanagement.controller;

import java.util.List;

import org.sltb.transportmanagement.core.CreateJourneyRequest;
import org.sltb.transportmanagement.web.WebResponse;
import org.sltb.transportmanagement.domain.Journey;
import org.sltb.transportmanagement.exception.InsufficientBalanceException;
import org.sltb.transportmanagement.service.JourneyManagementService;
import org.sltb.transportmanagement.service.PassengerManagementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TransportManagementController {

	@Autowired
	private PassengerManagementService memberShipManagementService;
	@Autowired
	private JourneyManagementService journayManagementService;

	@GetMapping(path = "/passengers/{userId}/cantravel", produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public WebResponse canTakeJouney(@PathVariable("userId") long userId) {
		boolean canTakJourney;
		try {
			canTakJourney = memberShipManagementService.canTakeJouney(userId);

		} catch (InsufficientBalanceException e) {
			return new WebResponse.WebResponseBuilder<Boolean>().addResponseData(false)
					.addResponseStatus(HttpStatus.BAD_REQUEST.value()).addMessage(e.getMessage()).build();
		} catch (Exception e) {
			return new WebResponse.WebResponseBuilder<Boolean>().addResponseData(false)
					.addResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR.value()).addMessage(e.getMessage()).build();
		}
		return new WebResponse.WebResponseBuilder<Boolean>().addResponseData(canTakJourney)
				.addResponseStatus(HttpStatus.OK.value()).addMessage("").build();
	}

	@PostMapping(path = "/journeys", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public WebResponse createJourney(@RequestBody CreateJourneyRequest journeyRequest) {
		Journey createdJourney;
		try {
			createdJourney = journayManagementService.create(journeyRequest.getStartingZone(),
					journeyRequest.getEndingZone(), journeyRequest.getUserId(), journeyRequest.getStartTime());

		} catch (Exception e) {
			return new WebResponse.WebResponseBuilder<Journey>().addResponseData(null)
					.addResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR.value()).addMessage(e.getMessage()).build();
		}
		return new WebResponse.WebResponseBuilder<Journey>().addResponseData(createdJourney)
				.addResponseStatus(HttpStatus.OK.value()).addMessage("").build();
	}

	@PutMapping(path = "/journeys/end/{journeyId}", produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
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

	@PostMapping(path = "/passengers/cards/{userId}", produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
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

	@GetMapping(path = "/journeys/users/{userId}", produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public WebResponse historyOf(@PathVariable("userId") final Long userId) {
		List<Journey> journeys;
		try {
			journeys = journayManagementService.historyOf(userId);

		} catch (Exception e) {
			return new WebResponse.WebResponseBuilder<List<Journey>>().addResponseData(null)
					.addResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR.value()).addMessage(e.getMessage()).build();
		}
		return new WebResponse.WebResponseBuilder<List<Journey>>().addResponseData(journeys)
				.addResponseStatus(HttpStatus.OK.value()).addMessage("").build();
	}

	@GetMapping(path = "/journeys/{journeyId}", produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public ResponseEntity<Journey> journeyOf(@PathVariable final Long journeyId) {
		Journey journey;
		try {
			journey = journayManagementService.journeyOf(journeyId);

		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return new ResponseEntity<Journey>(journey, HttpStatus.INTERNAL_SERVER_ERROR);
	}

}
