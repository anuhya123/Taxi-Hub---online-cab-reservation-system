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
	System.out.println("CheckForAccept.jsp"+(++counter));
	String number = (String) request.getSession().getAttribute("number");
	System.out.println(number);
	
	String query = "select * from bookings where phone_num = ? and booking_status=?";
	PreparedStatement stmt = conn.prepareStatement(query);
	stmt.setString(1,number);
	stmt.setString(2,"accepted");
	ResultSet rs = stmt.executeQuery();	
	
	if(rs.next()) {
		out.println("ACCEPTED");
		String query1 = "select driver_name,driver_phone_num,registration_num from driver where registration_num in (select registration_num from bookings where booking_status = ? and phone_num = ?)";
		PreparedStatement pstat2 = conn.prepareStatement(query1);
		pstat2.setString(1,"accepted");
		pstat2.setString(2,number);
		ResultSet rs1 = pstat2.executeQuery();
		if(rs1.next()){
			out.println("Driver Name: " + rs1.getString(1));
			out.println("Driver Number: " + rs1.getString(2));
			out.println("Vehicle Number: " + rs1.getString(3));
		}else {
		out.println("LOADING....");
		}
	}
%>
