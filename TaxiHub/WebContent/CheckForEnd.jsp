<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@page import="java.sql.ResultSet"%>
<%@page import="java.sql.PreparedStatement"%>
<%@page import="java.sql.DriverManager"%>
<%@page import="java.sql.Connection"%>
<%@page import="com.talentsprint.TaxiHub.dao.*"%>
<%!
	int counter = 0;
%>
<%
	ConnectionDAO cdao = new ConnectionDAO();
	Connection conn = cdao.getCon();
	System.out.println("CheckForEnd.jsp"+(++counter));
	String number = (String) request.getSession().getAttribute("number");
	String registration = (String) request.getSession().getAttribute("registration");
	int estimate = (int) request.getAttribute("fare");
	System.out.println(number);
	
	String query = "update bookings set booking_status = ? where phone_num = ? and registration_num = ? ";
	PreparedStatement stmt = conn.prepareStatement(query);
	stmt.setString(1,"completed");
	stmt.setString(2,number);
	stmt.setString(3,registration);
	ResultSet rs = stmt.executeQuery();	
	
	String query1 = "update vehicle set status = ? where registration_num = ? ";
	PreparedStatement stmt1 = conn.prepareStatement(query);
	stmt1.setString(1,"available");
	stmt1.setString(2,registration);
	ResultSet rs1 = stmt.executeQuery();
	
	String query2 = "update bookings set amount_paid = ? where booking_status = ? and phone_num = ? and registration_num = ? ";
	PreparedStatement stmt2 = conn.prepareStatement(query);
	stmt2.setInt(1,estimate);
	stmt2.setString(2,"completed");
	stmt2.setString(3,number);
	stmt2.setString(4,registration);
	ResultSet rs2 = stmt.executeQuery();	
	
%>
