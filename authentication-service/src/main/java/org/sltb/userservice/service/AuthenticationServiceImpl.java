package org.sltb.userservice.service;

import org.sltb.userservice.dao.AuthenticationDao;
import org.sltb.userservice.domain.UserToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class AuthenticationServiceImpl implements AuthenticationService {

	@Autowired
	private AuthenticationDao authenticationDao;

	@Transactional
	public UserToken createToken(String sessionId, Long userId) {
		return authenticationDao.createToken(sessionId, userId);
	}

	@Transactional
	public UserToken refresh(UserToken userToken) {
		return authenticationDao.refresh(userToken);
	}

	@Override
	public boolean isValid(Long tokenId) {
		return authenticationDao.isValid(tokenId);
	}

}
