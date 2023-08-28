package com.org.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
@WebServlet("/log")
public class ValidateLogin extends HttpServlet {
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		String email = req.getParameter("email");
		String password = req.getParameter("password");
		int validate = 0;
		
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/servlet_employee?user=root&password=root");
			String sql = "SELECT * FROM signin WHERE email = ? AND password = ?";
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setString(1, email);
			ps.setString(2, password);
			
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				validate = 1;
			} 
			
			con.close();
			
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		if (validate != 0) {
			PrintWriter pw = resp.getWriter();
			pw.write("<html><body><h2>LOGIN SUCCESSFULL !</h2></body></html>");
			RequestDispatcher rd = req.getRequestDispatcher("home.html");
			rd.include(req, resp);
		} else {
			PrintWriter pw = resp.getWriter();
			pw.write("<html><body><h2>LOGIN FAILLED INVALID EMAIL & PASSWORD !</h2></body></html>");
			RequestDispatcher rd = req.getRequestDispatcher("login.html");
			rd.include(req, resp);
		}
	}
}
