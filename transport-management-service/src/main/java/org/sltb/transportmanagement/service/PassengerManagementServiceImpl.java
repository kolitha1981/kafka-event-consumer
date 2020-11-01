package org.sltb.transportmanagement.service;

import org.sltb.transportmanagement.dao.PassengerManagementDao;
import org.sltb.transportmanagement.domain.CardStatus;
import org.sltb.transportmanagement.exception.InsufficientBalanceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class PassengerManagementServiceImpl implements PassengerManagementService {

	@Autowired
	private PassengerManagementDao passengerManagementDao;
	@Autowired
	private CardIssuePushNotificationService cardIssuePushNotificationService;
	@Value("${org.sltb.memebership.minaccountbalancefortravel}")
	private double minimumAccountBalanceForTravel;
	@Value("${org.sltb.memebership.cardrenewalFee}")
	private double cardRenewalFee;

	@Override
	public boolean canTakeJouney(long userId) {
		double accountBalance = passengerManagementDao.accountBalanceOf(userId);
		if (accountBalance < 0) {
			throw new InsufficientBalanceException(
					"You have negative account balanace. " + "Pls recarge your account first.");
		}
		if (accountBalance < minimumAccountBalanceForTravel) {
			throw new InsufficientBalanceException(
					"You are required to have a minimu balance  before making the request");
		}
		return true;
	}

	@Override
	public boolean requestCard(long userId) {
		Double accountBalance = passengerManagementDao.accountBalanceOf(userId);
		if (accountBalance < cardRenewalFee)
			throw new RuntimeException("Insufficient balance for issuing the card.");
		if (deductFromAccountAndNotify(userId, accountBalance)) {
			this.cardIssuePushNotificationService.pushIssueRequest(userId);
		}
		return false;
	}

	@Transactional
	private boolean deductFromAccountAndNotify(long userId, Double accountBalance) {
		if (!passengerManagementDao.changeCardStatus(userId, CardStatus.INACTIVE))
			return false;
		accountBalance -= cardRenewalFee;
		return passengerManagementDao.updateBalanceOf(userId, accountBalance);
	}

}
