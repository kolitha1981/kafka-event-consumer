package org.sltb.journeymanagement.service;

import org.sltb.journeymanagement.dao.MemberShipManagementDao;
import org.sltb.journeymanagement.domain.CardStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class MemberShipManagementServiceImpl implements MemberShipManagementService {

	@Autowired
	private MemberShipManagementDao membershipDao;
	@Autowired
	private CardIssuePushNotificationService cardIssuePushNotificationService;
	@Value("${org.sltb.memebership.minaccountbalancefortravel}")
	private Double minimumAccountBalanceForTravel;
	@Value("${org.sltb.memebership.cardrenewalFee}")
	private Double cardRenewalFee;

	@Override
	public boolean canTakeJouney(long userId) {
		return membershipDao.accountBalanceOf(userId).compareTo(minimumAccountBalanceForTravel) > 0;
	}

	@Override
	public boolean requestCard(long userId) {
		Double accountBalance = membershipDao.accountBalanceOf(userId);
		if (accountBalance < cardRenewalFee)
			throw new RuntimeException("Insufficient balance for issuing the card.");
		if (deductFromAccountAndNotify(userId, accountBalance)) {
			this.cardIssuePushNotificationService.pushIssueRequest(userId);
		}
		return false;
	}

	@Transactional
	private boolean deductFromAccountAndNotify(long userId, Double accountBalance) {
		if (!membershipDao.changeCardStatus(userId, CardStatus.INACTIVE))
			return false;
		accountBalance -= cardRenewalFee;
		return membershipDao.deductFromAccount(userId, accountBalance);
	}

}
