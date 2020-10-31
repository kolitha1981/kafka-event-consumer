package org.sltb.userservice.dao;

import java.util.List;

import org.sltb.userservice.domain.GrantedAuthority;

public interface UserDetailsDao {
	
	Long getUserIdOf(String userName);
	
	List<GrantedAuthority> getGrantedAuthoritiesOf(Long userId);	

}
