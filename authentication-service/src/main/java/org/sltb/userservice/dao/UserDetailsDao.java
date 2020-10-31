package org.sltb.userservice.dao;

import java.util.List;

import org.sltb.userservice.domain.GrantedAuthority;

public interface UserDetailsDao {
	
	Long getUserIdOf(final String userName);
	
	List<GrantedAuthority> getGrantedAuthoritiesOf(final Long userId);

}
