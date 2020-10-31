package org.sltb.transportmanagement.dao;

import org.sltb.transportmanagement.domain.CardStatus;

public interface PassengerManagementDao {
	
	Double accountBalanceOf(Long userId);
	
	boolean deductFromAccount(Long userid, Double amount);
	
	boolean changeCardStatus(Long userId, CardStatus cardStatus);	

}
