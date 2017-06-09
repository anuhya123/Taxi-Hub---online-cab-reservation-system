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

@WebServlet("/DriverRegister")
public class DriverRegister extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public DriverRegister() {
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
		String name = request.getParameter("username");
		String contact = request.getParameter("number");
		String registration = request.getParameter("vehiclenum");
		String password = request.getParameter("password");
		String vname = request.getParameter("vname");
		String cabPack = request.getParameter("cabPackage");
		//String status = "unavailable";
		
		DriverDAO ddao = new DriverDAO();
		try {
			boolean isVehicleSet = ddao.isVehicleRegistered(vname, registration, cabPack);
			if (isVehicleSet) {
				boolean isDriverJoined = ddao.isDriverRegistered(name, contact, registration, password);
				if (isDriverJoined) {
					HttpSession session = request.getSession();
		            session.setAttribute("username", name);
		            session.setAttribute("number", contact);
		            session.setAttribute("vehiclenum", registration);
		            session.setAttribute("password", password);
		            session.setAttribute("vname", vname);
		            session.setAttribute("cabPpackage", cabPack);
		            response.sendRedirect("driverhomepage.html");
				} else {
					out.println("Number already registered!");
					RequestDispatcher rd = request.getRequestDispatcher("driverhomepage.html");
				    rd.forward(request, response);
				}
		    } else {
		    	out.println("Problem registering. try again!!");
		    	RequestDispatcher rd = request.getRequestDispatcher("driverhomepage.html");
			    rd.forward(request, response);
		    }
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
}
