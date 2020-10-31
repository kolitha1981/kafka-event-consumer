package org.sltb.authservice.service;

import java.util.List;

import org.sltb.authservice.dao.UserDetailsDao;
import org.sltb.authservice.domain.GrantedAuthority;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

	@Autowired
	private UserDetailsDao userDetailsDao;

	@Override
	public Long getUserIdOf(String userName) {
		return userDetailsDao.getUserIdOf(userName);
	}

	@Override
	public List<GrantedAuthority> getGrantedAuthoritiesOf(Long userId) {
		return userDetailsDao.getGrantedAuthoritiesOf(userId);
	}

}
