package org.sltb.authservice.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.sltb.authservice.domain.GrantedAuthority;
import org.sltb.authservice.domain.SystemAuthority;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

@Repository
public class UserDetailsDaoImpl implements UserDetailsDao {

	@Autowired
	private JdbcTemplate jdbcTemplate;

	@Override
	public Long getUserIdOf(final String userName) {
		return jdbcTemplate.queryForObject("SELECT USER_ID FROM AUTHENTICATION_SERVICE.SYSTEM_USER WHERE USER_NAME=?",
				new Object[] { userName }, new RowMapper<Long>() {
					@Override
					public Long mapRow(ResultSet resultSet, int arg1) throws SQLException {
						return resultSet.getLong("USER_ID");
					}
				});
	}

	@Override
	public List<GrantedAuthority> getGrantedAuthoritiesOf(final Long userId) {
		return SystemAuthority.getGrantedAuthrities(jdbcTemplate
				.queryForObject("SELECT details.AUTH_BIN_VALUE FROM AUTHENTICATION_SERVICE.SYSTEM_ROLES roles INNER JOIN "
						+ "AUTHENTICATION_SERVICE.SYSTEM_USER_ROLES userroles  ON userroles.ROLE_ID = roles.ROLE_ID "
						+ "WHERE userroles.USER_ID = ?", new Object[] { Long.valueOf(userId) }, new RowMapper<Long>() {
							@Override
							public Long mapRow(ResultSet resultSet, int arg1) throws SQLException {
								return resultSet.getLong("AUTH_BIN_VALUE");
							}
						})
				.intValue());
	}

}
