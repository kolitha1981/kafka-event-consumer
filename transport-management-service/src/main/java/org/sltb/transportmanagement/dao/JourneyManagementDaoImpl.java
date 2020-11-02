package org.sltb.transportmanagement.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;

import org.sltb.transportmanagement.domain.Journey;
import org.sltb.transportmanagement.domain.JourneyState;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementSetter;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

@Repository
public class JourneyManagementDaoImpl implements JourneyManagementDao {

	@Autowired
	private JdbcTemplate jdbcTemplate;

	@Override
	public Journey create(final String startingZone, final String endingZone, final Long userId, final Date startTime) {
		String createJourneySQL = "INSERT INTO TRANSPORT_MANAGEMENT.JOURNEY "
				+ "(START_ZONE,END_ZONE,START_TIME,JOURNEY_STATE,USER_ID) VALUES(?,?,?,?,?)";
		KeyHolder journeyPkHolder = new GeneratedKeyHolder();
		return this.jdbcTemplate.update(createJourneySQL, new PreparedStatementSetter() {
			@Override
			public void setValues(final PreparedStatement preparedStatement) throws SQLException {
				preparedStatement.setString(1, startingZone);
				preparedStatement.setString(2, endingZone);
				preparedStatement.setDate(3, new java.sql.Date(startTime.getTime()));
				preparedStatement.setInt(4, JourneyState.STARTED.getJourneyState());
				preparedStatement.setLong(5, userId);
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
						preparedStatement.setLong(2, journeyId);
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

	@Override
	public List<Journey> historyOf(Long userId, int recordLimit) {
		return this.jdbcTemplate.query(
				"SELECT JOURNEY_ID, START_ZONE, END_ZONE, START_TIME, END_TIME, AMOUNT FROM "
						+ "TRANSPORT_MANAGEMENT.JOURNEY WHERE USER_ID = ? " + "ORDER BY START_TIME DESC LIMIT ?",
				new Object[] { userId, Integer.valueOf(recordLimit) }, new RowMapper<Journey>() {
					@Override
					public Journey mapRow(ResultSet resultSet, int arg1) throws SQLException {
						final Journey journey = new Journey(resultSet.getLong("JOURNEY_ID"),
								resultSet.getString("START_ZONE"), resultSet.getString("END_ZONE"), userId,
								resultSet.getDate("START_TIME"));
						journey.setEndTime(resultSet.getDate("END_TIME"));
						return journey;
					}
				});
	}

}
