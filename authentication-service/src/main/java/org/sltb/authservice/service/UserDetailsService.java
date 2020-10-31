package org.sltb.authservice.service;

import java.util.List;

import org.sltb.authservice.domain.GrantedAuthority;

public interface UserDetailsService {

	Long getUserIdOf(String userName);

	List<GrantedAuthority> getGrantedAuthoritiesOf(Long userId);

}
