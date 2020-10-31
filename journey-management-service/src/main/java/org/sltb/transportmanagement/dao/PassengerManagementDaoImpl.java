package org.sltb.transportmanagement.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.sltb.transportmanagement.domain.CardStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementSetter;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

@Repository
public class PassengerManagementDaoImpl implements PassengerManagementDao {

	@Autowired
	private JdbcTemplate jdbctemplate;

	@Override
	public Double accountBalanceOf(final Long userId) {
		return jdbctemplate.queryForObject(
				"SELECT AVAILABLE_BALANCE FROM TRANSPORT_MANAGEMENT.PASSENGER WHERE USER_ID = ?",
				new Object[] { Long.valueOf(userId) }, new RowMapper<Double>() {
					@Override
					public Double mapRow(ResultSet resultSet, int index) throws SQLException {
						return resultSet.getDouble("AVAILABLE_BALANCE");
					}
				});
	}

	@Override
	public boolean deductFromAccount(final Long userid, final Double amount) {
		return this.jdbctemplate.update(
				"UPDATE TRANSPORT_MANAGEMENT.PASSENGER SET AVAILABLE_BALANCE = ? WHERE USER_ID = ?",
				new PreparedStatementSetter() {
					@Override
					public void setValues(PreparedStatement preparedStatement) throws SQLException {
						preparedStatement.setLong(0, userid);
						preparedStatement.setDouble(1, amount);
					}
				}) > 0;
	}

	@Override
	public boolean changeCardStatus(Long userId, CardStatus cardStatus) {
		return this.jdbctemplate.update("UPDATE TRANSPORT_MANAGEMENT.PASSENGER SET CARD_STATUS = ? WHERE USER_ID = ?",
				new PreparedStatementSetter() {
					@Override
					public void setValues(PreparedStatement preparedStatement) throws SQLException {
						preparedStatement.setInt(0, cardStatus.getCardStatus());
						preparedStatement.setLong(1, userId);
					}
				}) > 0;
	}

}
