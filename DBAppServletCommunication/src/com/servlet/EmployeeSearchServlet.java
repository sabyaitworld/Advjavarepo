package com.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class EmployeeSearchServlet extends HttpServlet {
	private static final String GET_EMP_DETAILS_BY_ID = "SELECT EMPID,EMPNAME,EMPSAL FROM EMP WHERE EMPID=?";

	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		PrintWriter pw = null;
		
		int eid = 0;
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		ServletConfig cfg;
		ServletContext sc = null;
		sc=getServletContext();
		cfg = getServletConfig();
		pw = res.getWriter();
		res.setContentType("text/html");
		RequestDispatcher rd=req.getRequestDispatcher("/headerurl");
		rd.include(req, res);
		
		eid = Integer.parseInt(req.getParameter("id"));
		String url = cfg.getInitParameter("url");
		String dbuser = cfg.getInitParameter("dbuse");
		String dbpwd = cfg.getInitParameter("dbpwd");
		
		try {
			

			Class.forName(cfg.getInitParameter("driverclass"));
			
			con = DriverManager.getConnection(url, dbuser, dbpwd);

			/*
			 * InitialContext context=new InitialContext(); DataSource
			 * ds=(DataSource)context.lookup("java:/DSjndi"); con=ds.getConnection();
			 */
			ps = con.prepareStatement(GET_EMP_DETAILS_BY_ID);
			ps.setInt(1, eid);
			rs = ps.executeQuery();
			
			if (rs.next()) {
				pw.println("<h1 style='color:blue;text-align:center'>Employee details are</h1>");
				pw.println("<center><b>empid::</b>" + rs.getInt(1) + "<br>");
				pw.println("<center><b>empname::</b>" + rs.getString(2) + "<br>");
				pw.println("<center><b>empsal::</b>" + rs.getDouble(3) + "<br>");

			} else {
				pw.println("<h1 style='color:red'>employee not found</h1>");
			}
			pw.println("<br><br><a href='input.html'>home</a>");
			RequestDispatcher rd2=req.getRequestDispatcher("footer.html");
			rd2.include(req, res);
		} 
		/*
		 * catch(ClassNotFoundException ce) { ce.printStackTrace();
		 * pw.println("<h1 style='color:red'>internal problem</h1>"); }
		 */
		catch (Exception e) {
			e.printStackTrace();
			 RequestDispatcher rd1=req.getRequestDispatcher("/errorurl"); 
			//RequestDispatcher rd=sc.getRequestDispatcher("/errorurl");
			//RequestDispatcher rd=sc.getNamedDispatcher("error.html");
			rd1.forward(req, res);
		} finally {
			try {
				if (rs != null) {
					rs.close();
				}
			} catch (SQLException se) {
				se.printStackTrace();
			}
			try {
				if (ps != null) {
					ps.close();
				}
			} catch (SQLException se) {
				se.printStackTrace();
			}
			try {
				if (con != null) {
					con.close();
				}
			} catch (SQLException se) {
				se.printStackTrace();
			}
			
			try {
				if (pw != null) {
					pw.close();
				}
			} catch (Exception e) {
				e.printStackTrace();
			}

		}
	}

	public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doGet(req, res);
	}
}
