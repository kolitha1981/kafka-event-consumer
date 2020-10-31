package org.sltb.authservice.dao;

import java.util.List;

import org.sltb.authservice.domain.GrantedAuthority;

public interface UserDetailsDao {
	
	Long getUserIdOf(String userName);
	
	List<GrantedAuthority> getGrantedAuthoritiesOf(Long userId);	

}
