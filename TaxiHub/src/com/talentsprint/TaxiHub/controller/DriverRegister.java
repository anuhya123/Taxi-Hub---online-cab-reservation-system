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
		String name = request.getParameter("user");
		String contact = request.getParameter("number");
		String registration = request.getParameter("registration");
		String password = request.getParameter("password");
		String vname = request.getParameter("vname");
		String cabPack = request.getParameter("cost");
		//String status = "unavailable";
		
		DriverDAO ddao = new DriverDAO();
		try {
			boolean isVehicleSet = ddao.validateVehicle(registration);
			if (isVehicleSet) {
				out.println("<script type=\"text/javascript\">");
			    out.println("alert('Vehicle already registered!!');");
			    out.println("</script>");
			    RequestDispatcher rd = request.getRequestDispatcher("dhomepage.jsp");
			    rd.forward(request, response);
			} else {
				boolean isDriverJoined = ddao.validateDriver(registration);
				if (isDriverJoined) {
					out.println("<script type=\"text/javascript\">");
				    out.println("alert('Driver already registered!!');");
				    out.println("</script>");
				    RequestDispatcher rd = request.getRequestDispatcher("dhomepage.jsp");
				    rd.forward(request, response);
				} else {
					ddao.registerVehicle(vname, registration, cabPack);
					ddao.registerDriver(name, contact, registration, password);
		            RequestDispatcher rd = request.getRequestDispatcher("dhomepage.jsp");
		            rd.forward(request, response);
				}
		    }
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
}
