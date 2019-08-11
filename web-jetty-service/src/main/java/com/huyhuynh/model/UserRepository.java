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
	
	//Đăng kí tài khoản
	public User createUser(User u) {
		String query = "INSERT INTO LoginJetty(email,name,phone,pass) VALUES(?,?,?,?)";
		try {
			PreparedStatement preStatement = conn.prepareStatement(query);
			preStatement.setString(1, u.getEmail());
			preStatement.setString(2, u.getName());
			preStatement.setString(3, u.getPhone());
			preStatement.setString(4, u.getPass());
			preStatement.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return u;
	}
	//kiểm tra trùng
	public boolean checkUser(String email) {
		String query = "SELECT * FROM LoginJetty WHERE email = ?";
		try {
			PreparedStatement preparedStatement = conn.prepareStatement(query);
			preparedStatement.setString(1, email);
			ResultSet rs = preparedStatement.executeQuery();
			if(rs.next()) {
				return false;
			}
		}catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return true;
	}
}
