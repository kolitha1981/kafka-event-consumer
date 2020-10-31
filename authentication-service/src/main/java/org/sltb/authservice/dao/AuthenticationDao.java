package org.sltb.authservice.dao;

import org.sltb.authservice.domain.UserToken;

public interface AuthenticationDao {

	UserToken createToken(String sessionId, Long userId);
	
	UserToken refresh(UserToken userToken);
	
	boolean isValid(Long tokenId);
}
