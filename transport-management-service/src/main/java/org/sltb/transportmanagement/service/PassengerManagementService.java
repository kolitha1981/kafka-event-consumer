package org.sltb.transportmanagement.service;

public interface PassengerManagementService {
	
	boolean canTakeJouney(long userId);
	
	boolean requestCard(long userId);

}
