package org.sltb.userservice.controller;

import java.util.List;

import org.sltb.userservice.domain.GrantedAuthority;
import org.sltb.userservice.domain.UserToken;
import org.sltb.userservice.service.AuthenticationService;
import org.sltb.userservice.service.UserDetailsService;
import org.sltb.userservice.web.WebResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class AuthController {

	@Autowired
	private UserDetailsService userDetailsService;
	@Autowired
	private AuthenticationService authenticationService;

	@RequestMapping(method = {
			RequestMethod.GET }, path = "/users/{userName}/id", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	public WebResponse authUserIdOf(@PathVariable(name = "userName") final String userName) {
		Long authenticatedUserId;
		try {
			authenticatedUserId = userDetailsService.getUserIdOf(userName);
		} catch (Exception e) {
			return new WebResponse.WebResponseBuilder<Long>().addResponseData(null)
					.addResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR.value()).addMessage(e.getMessage()).build();
		}
		return new WebResponse.WebResponseBuilder<Long>().addResponseData(authenticatedUserId)
				.addResponseStatus(HttpStatus.OK.value()).addMessage("").build();
	}

	@RequestMapping(method = {
			RequestMethod.GET }, path = "/users/{userId}/token", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	public WebResponse authUserTokenOf(@PathVariable(name = "userId") final Long userId,
			@RequestParam("sessionId") final String sessionId) {
		UserToken userToken;
		try {
			userToken = this.authenticationService.createToken(sessionId, userId);
			userToken.addGrantedAuthorities(userDetailsService.getGrantedAuthoritiesOf(userId));
		} catch (Exception e) {
			return new WebResponse.WebResponseBuilder<Long>().addResponseData(null)
					.addResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR.value()).addMessage(e.getMessage()).build();
		}
		return new WebResponse.WebResponseBuilder<UserToken>().addResponseData(userToken)
				.addResponseStatus(HttpStatus.OK.value()).addMessage("").build();
	}

	@RequestMapping(method = {
			RequestMethod.GET }, path = "/users/token/${tokenId}/validity", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	public WebResponse isValid(@PathVariable("tokenId") final Long tokenId) {
		Boolean isTokenValid;
		try {
			isTokenValid = this.authenticationService.isValid(tokenId);
		} catch (Exception e) {
			return new WebResponse.WebResponseBuilder<Boolean>().addResponseData(null)
					.addResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR.value()).addMessage(e.getMessage()).build();
		}
		return new WebResponse.WebResponseBuilder<Boolean>().addResponseData(isTokenValid)
				.addResponseStatus(HttpStatus.OK.value()).addMessage("").build();
	}
	
	@RequestMapping(method = {
			RequestMethod.POST }, path = "/users/token/extension", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	public WebResponse refresh(@RequestBody UserToken userToken) {
		UserToken refreshedUserToken;
		try {
			refreshedUserToken = this.authenticationService.refresh(userToken);
		} catch (Exception e) {
			return new WebResponse.WebResponseBuilder<UserToken>().addResponseData(null)
					.addResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR.value()).addMessage(e.getMessage()).build();
		}
		return new WebResponse.WebResponseBuilder<UserToken>().addResponseData(refreshedUserToken)
				.addResponseStatus(HttpStatus.OK.value()).addMessage("").build();
	}

}
