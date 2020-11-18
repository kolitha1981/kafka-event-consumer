package org.sltb.transportmanagement.service;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.sltb.transportmanagement.dao.JourneyManagementDao;
import org.sltb.transportmanagement.dao.PassengerManagementDao;
import org.sltb.transportmanagement.domain.Journey;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class JourneyManagementServiceImpl implements JourneyManagementService {

	@Autowired
	private JourneyManagementDao journayManagementDao;
	@Autowired
	private PassengerManagementDao passengerManagementDao;
	@Value("${org.sltb.memebership.historyrecordlimit}")
	private int historyRecordLimit;

	@Transactional
	public Journey create(String startingZone, String endingZone, Long userId, Date startTime) {
		return journayManagementDao.create(startingZone, endingZone, userId, startTime);
	}

	public Double end(final Long journeyId, final Long userId, final String startZone, final String endZone) {
		return updateBalance(journeyId, userId, journayManagementDao.feeBetween(startZone, endZone),
				passengerManagementDao.accountBalanceOf(userId));
	}

	@Transactional
	private Double updateBalance(final Long journeyId, final Long userId, final Double travelFee,
			Double remainingBalanace) {
		if (!journayManagementDao.end(journeyId, travelFee, Calendar.getInstance().getTime()))
			throw new RuntimeException("Travel fee could not be deducted from account .....");
		remainingBalanace -= travelFee;
		if (!passengerManagementDao.updateBalanceOf(userId, remainingBalanace))
			throw new RuntimeException("Travel fee could not be deducted from account .....");
		return remainingBalanace;
	}

	@Override
	public List<Journey> historyOf(Long userId) {
		return this.journayManagementDao.historyOf(userId, historyRecordLimit);
	}

	@Override
	public Journey journeyOf(Long journeyId) {
		return this.journayManagementDao.journeyOf(journeyId);
	}

}
