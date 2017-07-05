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
import javax.servlet.http.HttpSession;

import com.talentsprint.TaxiHub.dao.DriverDetailsBasicDAO;
import com.talentsprint.TaxiHub.model.BookingModel;
import com.talentsprint.TaxiHub.model.DriverModel;

@WebServlet("/BasicDriverDetailsController")
public class BasicDriverDetailsController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public BasicDriverDetailsController() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String status = "accepted";
		PrintWriter out = response.getWriter();
		String button = request.getParameter("button");
		String phone_num = (String) request.getSession(false).getAttribute("number");
		String source = request.getParameter("source");
		String destination = request.getParameter("destination");
		
		DriverDetailsBasicDAO dedao = new DriverDetailsBasicDAO();
		try {
			//String email = dedao.getDriverEmailBasic(status);
			String result = dedao.bookRide(phone_num,source,destination);
			//String driver = dedao.retrieveCabDriver(phone_num,status);
			//String phone = dedao.retrieveDriverPhone(status);
			//String cabNumber = dedao.retrieveCabNumber(status);
			/*int did = dedao.retrieveDriverId(status);
			//String cabName = dedao.retrieveCabName(status);
			//System.out.println(email);
			//request.setAttribute("email", email);
			
			request.setAttribute("phone", phone);*/
			//request.setAttribute("driver", driver);
			//request.setAttribute("cabNumber", cabNumber);
			request.setAttribute("result", result);
			RequestDispatcher rd = request.getRequestDispatcher("basic.jsp");
		    rd.forward(request, response);
		    /*if (button.equals("proceed")) {
		    	ArrayList<DriverModel> driverdetails = new ArrayList<DriverModel>();
		    	driverdetails = dedao.displayDriverDetails(phone_num);
		    	request.setAttribute("driverdetails", driverdetails);
		    	
		    }*/
		} catch (SQLException e) {
			e.printStackTrace();
		}
		doGet(request, response);
	}

}
