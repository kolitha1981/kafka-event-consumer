package org.sltb.userservice.domain;

public class GrantedAuthority {

	private String authority;
	private boolean enabled;

	public GrantedAuthority(final String authority, final boolean enabled) {
		this.authority = authority;
		this.enabled = enabled;
	}

	public String getAuthority() {
		return authority;
	}

	public boolean isEnabled() {
		return enabled;
	}

}
