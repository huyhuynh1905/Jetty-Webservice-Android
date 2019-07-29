package com.huyhuynh.controller;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectJettyLogin {
	public static Connection getConnectJetty() {
		Connection conn = null;
		String url = "jdbc:jtds:sqlserver://localhost:1433/TEST";
		try {
			conn = DriverManager.getConnection(url,"sa","123456789");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return conn;
	}

}
