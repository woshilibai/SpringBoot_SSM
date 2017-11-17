package com.ifunyoung.rowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.ifunyoung.model.User;
/**
 * UserRowMapper用于将jdbcTemplate查询出来的结果集封装为User对象
 * @author lenovo
 *
 */
public class UserRowMapper implements RowMapper<User> {

	@Override
	public User mapRow(ResultSet rs, int rowNum) throws SQLException {
		User user = new User();
		user.setId(rs.getInt("id"));
		user.setUsername(rs.getString("username"));
		user.setAge(rs.getInt("age"));
		return user;
	}

}
