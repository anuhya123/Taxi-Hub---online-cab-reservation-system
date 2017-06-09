package com.talentsprint.TaxiHub.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.talentsprint.TaxiHub.dao.DriverDAO;

@WebServlet("/DriverLogin")
public class DriverLogin extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public DriverLogin() {
        super();
        // TODO Auto-generated constructor stub
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		PrintWriter out = response.getWriter();
		String number = request.getParameter("number");
		String password = request.getParameter("password");
		
		DriverDAO ddao = new DriverDAO();
		try {
			boolean result = ddao.validateDriver(number, password);
			String DBName = ddao.getUserName(number);
			String registration = ddao.getVehicleNumber(number);
			String vname = ddao.getVehicleName(number);
			if(result) {
				HttpSession session = request.getSession();
				session.setAttribute("username", DBName);
	            session.setAttribute("number", number);
	            session.setAttribute("registration", registration);
	            session.setAttribute("vname", vname);
	            response.sendRedirect("driveraccount.jsp");
			} else {
				out.println("Invalid Credentials!");
				RequestDispatcher rdsp = request.getRequestDispatcher("driverhomepage.html");
				rdsp.include(request, response);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		doGet(request, response);
	}

}
