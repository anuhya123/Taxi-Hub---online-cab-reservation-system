package com.talentsprint.TaxiHub.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import java.sql.ResultSet;
import com.talentsprint.TaxiHub.dao.*;

@WebServlet("/Login")
public class Login extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public Login() {
        super();
    }
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			doProcess(request,response);
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			try {
				doProcess(request,response);
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
	}
	
	protected void doProcess(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, ClassNotFoundException, SQLException {
		PrintWriter out = response.getWriter();
		String number = request.getParameter("number");
		String password = request.getParameter("password");
		LoginDAO ldao =new LoginDAO();
		boolean result = ldao.validate(number,password);
		String DBName = ldao.getUserName(number);
		if(result) {
			//response.sendRedirect("account.jsp");
			HttpSession session = request.getSession();
			session.setAttribute("username", DBName);
            session.setAttribute("number", number);
            response.sendRedirect("account.jsp");
			//RequestDispatcher rd = request.getRequestDispatcher("account.jsp");
		    //rd.forward(request, response);
			//out.println("You have succesfully logged in!");	
		} else {
			//response.sendRedirect("welcome.html");
			out.println("Invalid Credentials!");
			RequestDispatcher rdsp = request.getRequestDispatcher("welcome.html");
			rdsp.include(request, response);
		}
	}
	
}
