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

import com.talentsprint.TaxiHub.dao.DriverEmailDAO;

@WebServlet("/DriverDetailsController")
public class DriverDetailsController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public DriverDetailsController() {
        super();
        // TODO Auto-generated constructor stub
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String status = "available";
		PrintWriter out = response.getWriter();
		
		DriverEmailDAO dedao = new DriverEmailDAO();
		try {
			String email = dedao.getDriverEmailBasic(status);
			HttpSession session = request.getSession();
			session.setAttribute("email", email);
			//response.sendRedirect("basic.jsp");
			RequestDispatcher rd = request.getRequestDispatcher("basic.jsp");
		    rd.forward(request, response);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		doGet(request, response);
	}

}
