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

import com.talentsprint.TaxiHub.dao.DriverStatusDAO;

@WebServlet("/DriverStatusController")
public class DriverStatusController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public DriverStatusController() {
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
		String drivernumber = request.getParameter("user");
		String password = request.getParameter("password");
		DriverStatusDAO dsdao = new DriverStatusDAO();
		try {
			boolean result = dsdao.validateDriver(drivernumber, password);
			out.println(result);
			if (result) {
				String driverName = dsdao.getUserName(drivernumber);
				String registration = dsdao.getVehicleNumber(drivernumber);
				boolean isUpdated = dsdao.updateStatus(registration, password);
				if(isUpdated){
					out.println("Okay!");
				}
				String vname = dsdao.getVehicleName(drivernumber);
				out.println(vname);
				HttpSession session = request.getSession();
				session.setAttribute("driverName", driverName);
	            session.setAttribute("drivernumber", drivernumber);
	            session.setAttribute("registration", registration);
	            session.setAttribute("vname", vname);
	            response.sendRedirect("driveraccount.jsp");
			} else {
				out.println("<script type=\"text/javascript\">");
			    out.println("alert('User or password incorrect');");
			    out.println("</script>");
				//out.println("Invalid Credentials!");
				RequestDispatcher rdsp = request.getRequestDispatcher("dhomepage.jsp");
				rdsp.include(request, response);
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		doGet(request, response);
	}

}
