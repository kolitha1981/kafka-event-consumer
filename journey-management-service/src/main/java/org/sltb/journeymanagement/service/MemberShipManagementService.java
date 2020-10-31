package org.sltb.journeymanagement.service;

public interface MemberShipManagementService {
	
	boolean canTakeJouney(final long userId);
	
	boolean requestCard(final long userId);

}
