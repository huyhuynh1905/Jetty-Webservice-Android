package com.huyhuynh.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.huyhuynh.controller.ConnectJettyLogin;

public class UserRepository {
	Connection conn = null;
	public UserRepository() {
		conn = ConnectJettyLogin.getConnectJetty();
	}
	//Lấy nick trong csdl
	public User getUser(String email,String pass) {
		User u = null;
		String query = "SELECT * FROM LoginJetty WHERE email = ? AND pass = ?";
		try {
			PreparedStatement preparedStatement = conn.prepareStatement(query);
			preparedStatement.setString(1, email);
			preparedStatement.setString(2, pass);
			ResultSet rs = preparedStatement.executeQuery();
			if(rs.next()) {
				int id = rs.getInt(1);
				String name = rs.getString(3);
				String phone = rs.getString(4);
				u = new User(id, email, name, phone, pass);
			}
		}catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if(u!=null) {
			return u;
		} else return null;
	}
}
