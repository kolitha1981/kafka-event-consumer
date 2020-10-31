package org.sltb.transportmanagement.domain;

import java.math.BigDecimal;
import java.util.Calendar;
import java.util.Date;

public class Member {

	private Long membershipId;
	private Long userId;
	private String name;
	private String phone;
	private String email;
	private BigDecimal availableBalance;
	private Date membershipStartDate;
	private Date memebershipEndDate;
	private CardStatus cardStatus;

	public Member(Long membershipId, Long userId, String name, String email, Date memebershipEndDate) {
		this.membershipId = membershipId;
		this.userId = userId;
		this.name = name;
		this.email = email;
		this.membershipStartDate = Calendar.getInstance().getTime();
		this.memebershipEndDate = memebershipEndDate;
		this.cardStatus = CardStatus.ACTIVE;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public BigDecimal getAvailableBalance() {
		return availableBalance;
	}

	public void setAvailableBalance(BigDecimal availableBalance) {
		this.availableBalance = availableBalance;
	}

	public Long getMembershipId() {
		return membershipId;
	}

	public Long getUserId() {
		return userId;
	}

	public String getName() {
		return name;
	}

	public String getEmail() {
		return email;
	}

	public Date getMembershipStartDate() {
		return membershipStartDate;
	}

	public Date getMemebershipEndDate() {
		return memebershipEndDate;
	}

	public void setCardStatus(CardStatus cardStatus) {
		this.cardStatus = cardStatus;
	}	

	public CardStatus getCardStatus() {
		return cardStatus;
	}

	@Override
	public int hashCode() {
		return 31 + ((userId == null) ? 0 : userId.hashCode());
	}

	@Override
	public boolean equals(Object obj) {
		if (obj == this)
			return true;
		if (obj == null || getClass() != obj.getClass())
			return false;
		Member other = (Member) obj;
		if (userId == null) {
			if (other.userId != null)
				return false;
		} else if (!userId.equals(other.userId))
			return false;
		return true;
	}

}
