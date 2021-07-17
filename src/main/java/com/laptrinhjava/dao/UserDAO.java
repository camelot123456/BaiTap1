package com.laptrinhjava.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.laptrinhjava.models.RoleModel;
import com.laptrinhjava.models.UserModel;

public class UserDAO extends AbstractDAO{

	public static UserModel findOne(String username, String password) {
		
		Connection connection = null;
		PreparedStatement statement = null;
		ResultSet rs = null;
		UserModel user = null;
		
		try {
			connection = getConnection();
			statement = connection.prepareStatement("SELECT * FROM [USER] U, [ROLE] R WHERE R.ID = U.ROLE_ID AND U.USERNAME=? AND U.PASSWORD=?");
			statement.setString(1, username);
			statement.setString(2, password);
			rs = statement.executeQuery();
			while (rs.next()) {
				user = new UserModel();
				user.setUsername(rs.getString("USERNAME"));
				user.setPassword(rs.getString("PASSWORD"));
				user.setFullname(rs.getString("FULLNAME"));
				user.getRole().setName(rs.getString("NAME"));
				user.getRole().setCode(rs.getString("CODE"));
			}
			return user;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				if (rs != null) {
					rs.close();
				}
				if (statement != null) {
					statement.close();
				}
				if (connection != null) {
					connection.close();
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return null;
	}
	
}
