package com.talentsprint.TaxiHub.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.talentsprint.TaxiHub.dao.UserDetailsDAO;
import com.talentsprint.TaxiHub.model.BookingModel;

@WebServlet("/UserDetailsController")
public class UserDetailsController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public UserDetailsController() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PrintWriter out = response.getWriter();
		String button = request.getParameter("button");
		String registration = (String) request.getSession(false).getAttribute("registration");
		String phone_num = (String) request.getSession(false).getAttribute("number");
		
		request.setAttribute("registration", registration);
		ArrayList<BookingModel> details = new ArrayList<BookingModel>();
		UserDetailsDAO udao = new UserDetailsDAO();
		//int did = udao.getDid(registration);
		
		try {
			details = udao.displayDetails(registration);

			/*String unum = udao.getUserPhone(registration);
			String source =  udao.getSource(registration);
			String destination =  udao.getDestination(registration);
			String datetime =  udao.getDateTime(registration);*/
			
			/*String source = (String) details.get(1); 
			String destination = (String) details.get(2);
			String datetime = (String) details.get(3);
			String contact = (String) details.get(4);
			request.setAttribute("contact", contact);
			request.setAttribute("source", source);
			request.setAttribute("destination", destination);
			request.setAttribute("datetime", datetime);*/
			request.setAttribute("details", details);
			RequestDispatcher rd = request.getRequestDispatcher("driveraccount.jsp");
			rd.forward(request, response);
			if(button.equals("Accept")) {
				String result = udao.resetStatus(registration);
				//udao.retrieveCabDriver(phone_num);
			}
			if(button.equals("end")) {
				String result = udao.resetStatus(registration);
				//udao.retrieveCabDriver(phone_num);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		doGet(request, response);
	}

}
