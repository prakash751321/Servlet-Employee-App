package com.org.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class SigninDao {

	public static int saveSignIn(String name, long phone, String email, int age, String password) {
		 try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/servlet_employee?user=root&password=root");
			String sql = "INSERT INTO signin VALUES(?, ?, ?, ?, ?)";
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setString(1, name);
			ps.setLong(2, phone);
			ps.setString(3, email);
			ps.setInt(4, age);
			ps.setString(5, password);
			
			int res = ps.executeUpdate();
			con.close();
			return res;
			
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return 0;
	}
}