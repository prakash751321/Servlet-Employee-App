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
@WebServlet("/update")
public class UpdateEmp extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		 int id = Integer.parseInt(req.getParameter("id"));
		 String name = req.getParameter("name");
		 String job = req.getParameter("job");
		 double sal = Double.parseDouble(req.getParameter("sal"));
		 int deptno = Integer.parseInt(req.getParameter("deptno"));
		 
		 int res = EmpDao.updateEmp(id, name, job, sal, deptno);
			PrintWriter pw = resp.getWriter();
			RequestDispatcher rd = req.getRequestDispatcher("viewemp");
			if (res != 0) {
				pw.write("<html><body><h2>"+name+ " "+" Data Updated Successfully :)</h2></body></html>");
				rd.include(req, resp);
			} else {
				pw.write("<html><body><h2>Something Went Wrong !</h2></body></html>");
				rd.include(req, resp);
			}
		 
	}
}
