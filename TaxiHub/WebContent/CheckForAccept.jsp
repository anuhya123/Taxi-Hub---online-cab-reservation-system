<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@page import="java.sql.ResultSet"%>
<%@page import="java.sql.PreparedStatement"%>
<%@page import="java.sql.DriverManager"%>
<%@page import="java.sql.Connection"%>
<%@page import="com.talentsprint.TaxiHub.dao.*"%>
<%
	ConnectionDAO cdao = new ConnectionDAO();
	Connection conn = cdao.getCon();
	System.out.println("CheckForAccept.jsp");
	String number = (String)session.getAttribute("phone_num");
	String registration = (String)session.getAttribute("cabNumber");
	String query = "select * from bookings where registration_num =? and phone_num= ? and booking_status=?";
	PreparedStatement stmt = conn.prepareStatement(query);
	stmt.setString(1,registration);
	stmt.setString(2,"number");
	stmt.setString(3,"accepted");
	
	ResultSet rs = stmt.executeQuery();	
	
	if(rs.next()) {
		out.println("ACCEPTED");
	} else {
		out.println("STILL NOT ACCEPTED");
	}
%>