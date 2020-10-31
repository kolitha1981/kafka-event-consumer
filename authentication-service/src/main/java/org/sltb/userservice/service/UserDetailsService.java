package org.sltb.userservice.service;

import java.util.List;

import org.sltb.userservice.domain.GrantedAuthority;

public interface UserDetailsService {

	Long getUserIdOf(final String userName);

	List<GrantedAuthority> getGrantedAuthoritiesOf(final Long userId);

}
