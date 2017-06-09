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

import com.talentsprint.TaxiHub.dao.BookingDAO;
//import com.talentsprint.TaxiHub.model.BookingModel;
//import com.talentsprint.TaxiHub.model.VehicleModel;

@WebServlet("/BookingController")
public class BookingController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
  
    public BookingController() {
        super();
    }
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doPost(request,response);
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//HttpSession session = request.getSession();
		//PrintWriter out = response.getWriter();
		BookingDAO bdao = new BookingDAO();
		String status = "available";
		
		try {
			String driver = bdao.retrieveCabDriver(status);
			String phone = bdao.retrieveDriverPhone(status);
			String cabNumber = bdao.retrieveCabNumber(status);
			String cabName = bdao.retrieveCabName(status);
			HttpSession session = request.getSession();
			session.setAttribute("drivername", driver);
	        session.setAttribute("phone", phone);
	        session.setAttribute("cabnum", cabNumber);
	        session.setAttribute("cabname", cabName);
	        response.sendRedirect("basic.jsp");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		/*BookingModel booking = new BookingModel();
		VehicleModel vehicle = new VehicleModel();
		booking.setSource((String)request.getParameter("source"));
		booking.setSource((String)request.getParameter("destination"));
		booking.setSource((String)request.getParameter("distance"));
		booking.setSource((String)request.getParameter("rideEstimate"));*/
		
		//System.out.println(request.getParameter("source"));
		/*booking.setDestination((String)request.getParameter("destination"));
		String customerID = (String) (session.getAttribute("customerID"));
		try {
			BookingDAO.bookARide(booking);
			RequestDispatcher requestDispatcher = request.getRequestDispatcher("JSP/RideHistory.jsp");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}*/
		doGet(request, response);
	}
}
