package com.talentsprint.TaxiHub.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class SampleController
 */
@WebServlet("/SampleController")
public class SampleController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SampleController() {
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

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String phone_num = (String) request.getSession(false).getAttribute("number");
		String source = request.getParameter("source");
		String destination = request.getParameter("destination");
		request.setAttribute("phone_num", phone_num);
		request.setAttribute("source", source);
		request.setAttribute("destination", destination);
		RequestDispatcher rd = request.getRequestDispatcher("driveraccount.jsp");
		rd.forward(request, response);
		doGet(request, response);
	}

}
