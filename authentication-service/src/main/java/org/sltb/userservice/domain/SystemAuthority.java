package org.sltb.userservice.domain;

import java.util.ArrayList;
import java.util.List;

public enum SystemAuthority {

	START_JOURNEY(1, "START_JOURNEY"), END_JOURNEY(2, "END_JOURNEY"), CHECK_BALANACE(4, "CHECK_BALANACE");

	private int authorityBinValue;
	private String authority;

	private SystemAuthority(int authorityBinValue, final String authority) {
		this.authorityBinValue = authorityBinValue;
		this.authority = authority;
	}

	public int getAuthorityBinValue() {
		return authorityBinValue;
	}

	public String getAuthority() {
		return authority;
	}

	public static List<GrantedAuthority> getGrantedAuthrities(int authBinValue) {
		final List<GrantedAuthority> grantedAuthorities = new ArrayList<GrantedAuthority>();
		for (SystemAuthority authority : values()) {
			if ((authority.getAuthorityBinValue() & authBinValue) == authority.getAuthorityBinValue()) {
				grantedAuthorities.add(new GrantedAuthority(authority.getAuthority(), true));
			}
		}
		return grantedAuthorities;
	}

}
