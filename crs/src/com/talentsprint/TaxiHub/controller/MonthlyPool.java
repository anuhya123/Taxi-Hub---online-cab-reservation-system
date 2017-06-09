package com.talentsprint.TaxiHub.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.swing.JOptionPane;

import com.talentsprint.TaxiHub.dao.MonthlyPoolDAO;

/**
 * Servlet implementation class MonthlyPool
 */
@WebServlet("/MonthlyPool")
public class MonthlyPool extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public MonthlyPool() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		HttpSession session = request.getSession();
		PrintWriter out = response.getWriter();
		String email = request.getParameter("email");
		String number = request.getParameter("number");
		String source = request.getParameter("source");
		String destination = request.getParameter("destination");
		String fromDate = request.getParameter("date");
		int ridePackage = Integer.parseInt(request.getParameter("package"));
		String cabNumber = request.getParameter("cabnumber");
		
		MonthlyPoolDAO mpdao = new MonthlyPoolDAO();
		try {
			boolean result = mpdao.availFeature(email,number,cabNumber,source,destination,fromDate,ridePackage);
			if(result) {
				System.out.println("Succesfully availed!!");
			} else {
				System.out.println("There was some problem availing this. Please check again!");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		doGet(request, response);
	}

}
