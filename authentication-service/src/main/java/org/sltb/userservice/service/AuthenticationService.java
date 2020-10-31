package org.sltb.userservice.service;

import org.sltb.userservice.domain.UserToken;

public interface AuthenticationService {

	UserToken createToken(String sessionId, Long userId);

	UserToken refresh(UserToken userToken);

	boolean isValid(Long tokenId);

}
