package org.sltb.journeymanagement.dao;

import org.sltb.journeymanagement.domain.CardStatus;

public interface MemberShipManagementDao {
	
	Double accountBalanceOf(Long userId);
	
	boolean deductFromAccount(Long userid, Double amount);
	
	boolean changeCardStatus(Long userId, CardStatus cardStatus);	

}
