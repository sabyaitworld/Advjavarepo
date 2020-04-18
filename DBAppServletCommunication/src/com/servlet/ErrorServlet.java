package com.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
@WebServlet(urlPatterns = "/errorurl",name = "error")
public class ErrorServlet extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		PrintWriter pw=res.getWriter();
		res.setContentType("text/html");
		RequestDispatcher rd1,rd2=null;
		rd1=req.getRequestDispatcher("/headerurl");
		rd1.include(req, res);
		pw.println("<h1 style='color:red'>internal  program</h1>");
		pw.println("<br><br><a href='input.html'>home</a>");
		rd2=req.getRequestDispatcher("/footer.html");
		rd2.include(req, res);
		
		System.out.println("ErrorServlet.doGet()");
		pw.close();
		
		
	}
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doGet(req, resp);
	}

}
