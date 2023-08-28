package com.org.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.org.dao.EmpDao;
@WebServlet("/delete")
public class DeleteEmpServlet extends HttpServlet{
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		int id = Integer.parseInt(req.getParameter("id"));
		
		int res = EmpDao.deleteEmp(id);
		PrintWriter pw = resp.getWriter();
		RequestDispatcher rd = req.getRequestDispatcher("viewemp");
		if (res != 0) {
			pw.write("<html><body><h2> Selected Id : "+id+" "+" Deleted Successfully </h2></body></html>");
			rd.include(req, resp);
		} else {
			pw.write("<html><body><h2>Something Went Wrong !</h2></body></html>");
			rd.include(req, resp);
		}
	}
}
