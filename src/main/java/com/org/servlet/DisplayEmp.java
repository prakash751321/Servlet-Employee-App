package com.org.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
@WebServlet("/viewemp")
public class DisplayEmp extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/servlet_employee","root","root");
			String sql = "SELECT * FROM emp";
			Statement st = con.createStatement();
			ResultSet rs = st.executeQuery(sql);
			
			PrintWriter pw = resp.getWriter();
			pw.write("<html><body>");
			pw.write("<table border = 6px>");
			pw.write("<tr>");
			pw.write("<th>Id</th>");
			pw.write("<th>Name</th>");
			pw.write("<th>Job</th>");
			pw.write("<th>Sal</th>");
			pw.write("<th>DeptNo</th>");
			
			
			pw.write("<th>Edit</th>");
			pw.write("<th>Delete</th>");
			
			pw.write("</tr>");
			
			while (rs.next()) {
				pw.write("<tr>");
				pw.write("<td>"+rs.getInt(1)+"</td>");
				pw.write("<td>"+rs.getString(2)+"</td>");
				pw.write("<td>"+rs.getString(3)+"</td>");
				pw.write("<td>"+rs.getDouble(4)+"</td>");
				pw.write("<td>"+rs.getInt(5)+"</td>");
				pw.write("<td> <a href=edit?id="+rs.getInt(1)+">edit</a></td>");	// URL forwarding to edit URL
				pw.write("<td> <a href=delete?id="+rs.getInt(1)+">delete</a></td>");	//  // URL forwarding to delete URL
				
				pw.write("</tr>");
			}
			
			pw.write("</table></body></html>");
			con.close();
			
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
}
