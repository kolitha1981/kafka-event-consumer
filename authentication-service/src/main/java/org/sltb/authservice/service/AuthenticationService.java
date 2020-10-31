package org.sltb.authservice.service;

import org.sltb.authservice.domain.UserToken;

public interface AuthenticationService {

	UserToken createToken(String sessionId, Long userId);

	UserToken refresh(UserToken userToken);

	boolean isValid(Long tokenId);

}
