package com.talentsprint.TaxiHub.controller;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.talentsprint.TaxiHub.dao.UserDetailsDAO;

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
		String registration = (String) request.getSession(false).getAttribute("registration");
		request.setAttribute("registration", registration);
		
		UserDetailsDAO udao = new UserDetailsDAO();
		//int did = udao.getDid(registration);
		
		try {
			String unum = udao.getUserPhone(registration);
			String source =  udao.getSource(registration);
			String destination =  udao.getDestination(registration);
			String datetime =  udao.getDateTime(registration);
			request.setAttribute("unum", unum);
			request.setAttribute("source", source);
			request.setAttribute("destination", destination);
			request.setAttribute("datetime", datetime);
			RequestDispatcher rd = request.getRequestDispatcher("driveraccount.jsp");
			rd.forward(request, response);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		doGet(request, response);
	}

}
