package org.sltb.userservice.dao;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Calendar;

import org.sltb.userservice.domain.UserToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementSetter;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

@Repository
public class AuthenticationDaoImpl implements AuthenticationDao {

	@Autowired
	private JdbcTemplate jdbcTemplate;

	@Override
	public UserToken createToken(String sessionId, Long userId) {
		final String createTokenSQL = "INSERT INTO AUTHENTICATION_SERVICE.SYSTEM_USER_TOKENS(SESSION_ID,USER_ID,EXPIRED_DATE) "
				+ "VALUES(?,?,?)";
		final KeyHolder keyHolder = new GeneratedKeyHolder();
		final Calendar calendar = Calendar.getInstance();
		calendar.add(Calendar.HOUR, 3);
		return this.jdbcTemplate.update(createTokenSQL, new PreparedStatementSetter() {
			@Override
			public void setValues(PreparedStatement preparedStatement) throws SQLException {
				preparedStatement.setString(0, sessionId);
				preparedStatement.setLong(1, userId);
				preparedStatement.setDate(2, new Date(calendar.getTime().getTime()));
			}
		}, keyHolder) <= 0 ? null : new UserToken(keyHolder.getKey().longValue(), sessionId, userId, calendar.getTime());
	}

	@Override
	public UserToken refresh(UserToken userToken) {
		final Calendar calendar = Calendar.getInstance();
		calendar.setTime(userToken.getExpieryDate());
		calendar.add(Calendar.HOUR, 3);
		final int numberOfEffectedRows = this.jdbcTemplate.update(
				"UPDATE AUTHENTICATION_SERVICE.SYSTEM_USER_TOKENS SET EXPIRED_DATE = ? WHERE TOKEN_ID = ?",
				new PreparedStatementSetter() {
					@Override
					public void setValues(PreparedStatement preparedStatement) throws SQLException {

						preparedStatement.setDate(0, new java.sql.Date(calendar.getTime().getTime()));
						preparedStatement.setLong(1, userToken.getTokenId());
					}
				});
		userToken.setExpieryDate(calendar.getTime());
		return numberOfEffectedRows <= 0 ? null : userToken;
	}

	@Override
	public boolean isValid(Long tokenId) {
		return (new java.util.Date()).after(this.jdbcTemplate.queryForObject(
				"SELECT EXPIRED_DATE FROM AUTHENTICATION_SERVICE.SYSTEM_USER_TOKENS WHERE TOKEN_ID = ?",
				new Object[] { Long.valueOf(tokenId) }, new RowMapper<java.util.Date>() {
					@Override
					public java.util.Date mapRow(ResultSet resultSet, int arg1) throws SQLException {
						final Date expiredDate = resultSet.getDate("EXPIRED_DATE");
						return new java.util.Date(expiredDate.getTime());
					}
				}));
	}

}
