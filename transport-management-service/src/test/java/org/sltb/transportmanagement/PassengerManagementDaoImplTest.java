package org.sltb.transportmanagement;

import static org.junit.Assert.assertEquals;

import org.junit.jupiter.api.Test;
import org.sltb.transportmanagement.dao.PassengerManagementDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

@SpringBootTest(classes = TransportManagementApplication.class)
@Transactional
class PassengerManagementDaoImplTest {
	
	@Autowired
	private PassengerManagementDao passengerManagementDao;

	@Test
	public void testGetAccountBalanceOfUser() {
		assertEquals(passengerManagementDao.accountBalanceOf(Long.valueOf(2)), Double.valueOf(120.00));
		assertEquals(passengerManagementDao.accountBalanceOf(Long.valueOf(1)), Double.valueOf(0));
	}
	
	/* @Test
	public void testUpdateBlanceFromAccount() {
		assertTrue(passengerManagementDao.updateBalanceOf(Long.valueOf(2), Double.valueOf(60.00)));
		assertEquals(passengerManagementDao.accountBalanceOf(Long.valueOf(2)), Double.valueOf(60.00));
	}
	
	@Test
	public void changeCardStatusOfUser() {
		assertTrue(passengerManagementDao.changeCardStatus(Long.valueOf(2), CardStatus.INACTIVE));
	}*/

}
