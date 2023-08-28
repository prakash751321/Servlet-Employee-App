package com.org.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.org.dao.SigninDao;
@WebServlet("/sign")
public class SigninServlet extends HttpServlet {
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String name = req.getParameter("name");
		Long phone = Long.parseLong(req.getParameter("phone"));
		String email = req.getParameter("email");
		int age = Integer.parseInt(req.getParameter("age"));
		String password = req.getParameter("password");
		
		int res = SigninDao.saveSignIn(name, phone, email, age, password);
		

		
		RequestDispatcher rd = req.getRequestDispatcher("login.html");
		PrintWriter pw = resp.getWriter();
		if (res != 0) {
			pw.write("<html><body><h2>SIGNIN SUCCESS YOU CAN LOGIN NOW.</h2></body></html>");
			rd.include(req, resp);
		} else {
			pw.write("<html><body><h2>SIGNIN FAILED PLEASE LOGIN.</h2></body></html>");
		}
	}
}
