package com.talentsprint.TaxiHub.controller;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.talentsprint.TaxiHub.dao.DriverStatusDAO;

/**
 * Servlet implementation class DriverAcceptance
 */
@WebServlet("/DriverAcceptance")
public class DriverAcceptance extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DriverAcceptance() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String registration = (String) request.getSession().getAttribute("registration");
		String name = request.getParameter("accept");
        String name2 = request.getParameter("decline");
		DriverStatusDAO dsdao = new DriverStatusDAO();
		try {
			if ("accept".equals(name)) {
				boolean result = dsdao.resetStatus(registration);
				if (result) {
					System.out.println("updated!");	
				}
			} 
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
