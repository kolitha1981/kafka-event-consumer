package org.sltb.authservice.domain;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class UserToken {

	private Long tokenId;
	private String sessionId;
	private Long userId;
	private Date expieryDate;
	private List<GrantedAuthority> grantedAuthorities;

	public UserToken(Long tokenId, String sessionId, Long userId, Date expieryDate) {
		this.tokenId = tokenId;
		this.sessionId = sessionId;
		this.userId = userId;
		this.expieryDate = expieryDate;
		this.grantedAuthorities =  new ArrayList<GrantedAuthority>();
	}

	public Date getExpieryDate() {
		return expieryDate;
	}

	public void setExpieryDate(Date expieryDate) {
		this.expieryDate = expieryDate;
	}

	public Long getTokenId() {
		return tokenId;
	}

	public String getSessionId() {
		return sessionId;
	}

	public Long getUserId() {
		return userId;
	}	

	public List<GrantedAuthority> getGrantedAuthorities() {
		return grantedAuthorities;
	}

	public void addGrantedAuthorities(List<GrantedAuthority> grantedAuthorities) {
		this.grantedAuthorities.addAll(grantedAuthorities);
	}

	@Override
	public int hashCode() {
		return 31 * (((sessionId == null) ? 0 : sessionId.hashCode()) + 31) + ((userId == null) ? 0 : userId.hashCode());
	}

	@Override
	public boolean equals(Object obj) {
		if (obj == this)
			return true;
		if (obj == null || getClass() != obj.getClass())
			return false;
		UserToken other = (UserToken) obj;
		if (sessionId == null) {
			if (other.sessionId != null)
				return false;
		} else if (!sessionId.equals(other.sessionId))
			return false;
		if (userId == null) {
			if (other.userId != null)
				return false;
		} else if (!userId.equals(other.userId))
			return false;
		return true;
	}
	
	

}
