package org.sltb.journeymanagement.dao;

import java.math.BigDecimal;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;

import org.sltb.journeymanagement.domain.Journey;
import org.sltb.journeymanagement.domain.JourneyState;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementSetter;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

@Repository
public class JournayManagementDaoImpl implements JournayManagementDao {

	@Autowired
	private JdbcTemplate jdbcTemplate;

	@Override
	public Journey create(final String startingZone, final String endingZone, final Long userId, final Date startTime) {
		String createJourneySQL = "INSERT INTO TRANSPORT_MANAGEMENT.JOURNEY "
				+ "(START_ZONE,END_ZONE,START_TIME,JOURNEY_STATE,USER_ID) VALUES(?,?,?,?,?,?)";
		KeyHolder journeyPkHolder = new GeneratedKeyHolder();
		return this.jdbcTemplate.update(createJourneySQL, new PreparedStatementSetter() {
			@Override
			public void setValues(final PreparedStatement preparedStatement) throws SQLException {
				preparedStatement.setString(0, startingZone);
				preparedStatement.setString(1, endingZone);
				preparedStatement.setDate(2, new java.sql.Date(startTime.getTime()));
				preparedStatement.setInt(3, JourneyState.STARTED.getJourneyState());
				preparedStatement.setLong(4, userId);
			}
		}, journeyPkHolder) <= 0 ? null
				: new Journey(journeyPkHolder.getKey().longValue(), startingZone, endingZone, userId, startTime);
	}

	@Override
	public boolean end(final Long journeyId, Double amount, Date endDateTime) {
		return this.jdbcTemplate.update(
				"UPDATE TRANSPORT_MANAGEMENT.JOURNEY SET END_TIME = ? , AMOUNT = ? WHERE JOURNEY_ID = ?",
				new PreparedStatementSetter() {
					@Override
					public void setValues(PreparedStatement preparedStatement) throws SQLException {
						preparedStatement.setDate(0, new java.sql.Date(endDateTime.getTime()));
						preparedStatement.setDouble(1, amount);
					}
				}) > 0;
	}

	@Override
	public Double feeBetween(final String startZone, final String endZone) {
		return this.jdbcTemplate
				.queryForObject(("SELECT AMOUNT FROM TRANSPORT_MANAGEMENT.DESTINATION_CHARGES WHERE START_ZONE = ? "
						+ "AND END_ZONE = ?"), new Object[] { startZone, endZone }, new RowMapper<Double>() {
							@Override
							public Double mapRow(ResultSet resultSet, int arg1) throws SQLException {
								return resultSet.getDouble("AMOUNT");
							}
						});
	}

}
