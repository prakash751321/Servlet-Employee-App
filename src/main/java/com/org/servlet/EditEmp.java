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

@WebServlet("/edit")
public class EditEmp extends HttpServlet{
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		 String id = req.getParameter("id");
		 
		 try {
				Class.forName("com.mysql.cj.jdbc.Driver");
				Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/servlet_employee?user=root&password=root");
				String sql = "SELECT * FROM emp WHERE id = "+id;
				Statement st = con.createStatement();
				ResultSet rs = st.executeQuery(sql);
				rs.next();
				PrintWriter pw = resp.getWriter();
				pw.write("<html><body><form action=update >");			// Update Operation
				pw.write("Id  : <input type=text value="+rs.getInt(1)+" name=id readonly><br>");
				pw.write("Name  : <input type=text value="+rs.getString(2)+" name=name><br>");
				pw.write("Job  : <input type=text value="+ rs.getString(3) +" name=job><br>");
				pw.write("Sal  : <input type=text value="+ rs.getDouble(4) +" name =sal><br>");
				pw.write("DeptNo  : <input type=text value="+ rs.getInt(5) +" name=deptno><br>");
				pw.write("<input type=submit value=update >");		
				pw.write("</form></body></html>");
				
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
