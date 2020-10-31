package org.sltb.userservice.controller;

import java.util.List;

import org.sltb.userservice.domain.GrantedAuthority;
import org.sltb.userservice.service.UserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class AuthController {

	@Autowired
	private UserDetailsService userDetailsService;

	@RequestMapping(method = {
			RequestMethod.GET }, path = "/users/{userName}/id", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	public Long authUserIdOf(@PathVariable(name = "userName") final String userName) {
		return userDetailsService.getUserIdOf(userName);
	}

	@RequestMapping(method = {
			RequestMethod.GET }, path = "/users/{userId}/authorities", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	public List<GrantedAuthority> authTokenOfUser(@PathVariable(name = "userId") final Long userId) {
		return userDetailsService.getGrantedAuthoritiesOf(userId);
	}

}
