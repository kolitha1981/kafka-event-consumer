package org.sltb.transportmanagement.service;

public interface PassengerManagementService {
	
	boolean canTakeJouney(final long userId);
	
	boolean requestCard(final long userId);

}
