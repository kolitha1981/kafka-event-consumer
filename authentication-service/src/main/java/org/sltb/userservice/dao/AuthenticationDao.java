package org.sltb.userservice.dao;

import org.sltb.userservice.domain.UserToken;

public interface AuthenticationDao {

	UserToken createToken(String sessionId, Long userId);
	
	UserToken refresh(UserToken userToken);
	
	boolean isValid(Long tokenId);
}
