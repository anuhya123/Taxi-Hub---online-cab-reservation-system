package com.talentsprint.TaxiHub.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.talentsprint.TaxiHub.dao.*;

//@WebServlet("/Registration")
public class Registration extends HttpServlet {
	private static final long serialVersionUID = 1L;
    public Registration() {
        super();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	try {
			doProcess(request, response);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	try {
			doProcess(request, response);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
    
    protected void doProcess(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException {
    	PrintWriter out = response.getWriter();
		String name = request.getParameter("username");
		String number = request.getParameter("number");
		String password = request.getParameter("password");
		
		RegisterDAO ldao = new RegisterDAO();
		boolean result = ldao.insertValidate(name, number, password);
		//boolean result = ldao.userNumberExists(number);
		//PrintWriter out = response.getWriter();

		if(result) {
			//System.out.println("Exists!");
			//response.sendRedirect("account.jsp");
			HttpSession session = request.getSession();
            session.setAttribute("username", name);
            session.setAttribute("number", number);
            response.sendRedirect("welcome.html");
			//RequestDispatcher rd = request.getRequestDispatcher("account.jsp");
		    //rd.forward(request, response);
		} else {
			//System.out.println("New!");
			out.println("Number already registered!");
			RequestDispatcher rd = request.getRequestDispatcher("register.html");
		    rd.forward(request, response);
			//out.println("Check all the credentials!!");
			//response.sendRedirect("register.html");
		}
	}
}

