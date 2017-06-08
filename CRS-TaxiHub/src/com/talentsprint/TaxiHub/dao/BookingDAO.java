package com.talentsprint.TaxiHub.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.talentsprint.TaxiHub.model.BookingModel;

public class BookingDAO {
	static ConnectionDAO cdao = new ConnectionDAO();
	static Connection conn = cdao.getCon();
	
	public static int bookARide(BookingModel booking) throws SQLException {
		// TODO Auto-generated method stub
		int result = 0;
		//try {
			String sql = "insert into bookings(user_name,phone_num,registration_num,source,destination,distance,total_fare) values(?,?,?,?,?,?,?)";
			PreparedStatement statement = conn.prepareStatement(sql);
			statement.setString(1, booking.getUser_name());
			statement.setString(2, booking.getPhone_num());
			statement.setString(3, booking.getRegistration_num());
			statement.setString(4, booking.getSource());
			statement.setString(5, booking.getDestination());
			statement.setString(6, booking.getTotal_fare());
			ResultSet rs = statement.executeQuery();
			while (rs.next()) {
				
			}
			result = statement.executeUpdate();
		//}
			return result;
	}
	
	public String retrieveCabDriver(String status) throws SQLException {
		String driver = null;
		
		//String query = "select registration_num from vehicle where status = '"+status+"'";
		String sql =  "select d.driver_name as DName, d.driver_phone_num, d.registration_num, v.vehicle_name from driver d, vehicle v where d.registration_num in (select v.registration_num from vehicle where v.status = '"+status+"')";
		Statement statement = conn.createStatement();
		//statement.setString(1, status);
		ResultSet resultSet = statement.executeQuery(sql);
		while (resultSet.next()) {
			//System.out.println("resultSet.getString(1) + resultSet.getString(2) + resultSet.getString(3) + resultSet.getString(4)");
			driver = resultSet.getString("DName");
		}
		System.out.println("driver");
		/*System.out.println("are you there?");
		for (int i = 0; i < 5; i++) {
			System.out.println(details[i]);
		}*/
		
		return driver;
	}

	public String retrieveDriverPhone(String status) throws SQLException {
		String phone = " ";
		String query = "select registration_num from vehicle where status = ?";
		String sql = "select d.driver_name, d.driver_phone_num, d.registration_num, v.vehicle_name from driver d, vehicle v where d.registration_num = query";
		PreparedStatement statement = conn.prepareStatement(sql);
		statement.setString(1, status);
		ResultSet resultSet = statement.executeQuery(sql);
		while (resultSet.next()) {
			//System.out.println("resultSet.getString(1) + resultSet.getString(2) + resultSet.getString(3) + resultSet.getString(4)");
			phone = resultSet.getString(2);
		}
		/*System.out.println("are you there?");
		for (int i = 0; i < 5; i++) {
			System.out.println(details[i]);
		}*/
		//System.out.println(phone);
		return phone;
	}
	
	public String retrieveCabNumber(String status) throws SQLException {
		String cabNum = null;
		String query = "select registration_num from vehicle where status = ?";
		String sql = "select d.driver_name, d.driver_phone_num, d.registration_num, v.vehicle_name from driver d, vehicle v where d.registration_num = query";
		PreparedStatement statement = conn.prepareStatement(sql);
		statement.setString(1, status);
		ResultSet resultSet = statement.executeQuery(sql);
		while (resultSet.next()) {
			//System.out.println("resultSet.getString(1) + resultSet.getString(2) + resultSet.getString(3) + resultSet.getString(4)");
			cabNum = resultSet.getString(3);
		}
		/*System.out.println("are you there?");
		for (int i = 0; i < 5; i++) {
			System.out.println(details[i]);
		}*/
		
		return cabNum;
	}

	public String retrieveCabName(String status) throws SQLException {
		String cabName = null;
		String query = "select registration_num from vehicle where status = ?";
		String sql = "select d.driver_name, d.driver_phone_num, d.registration_num, v.vehicle_name from driver d, vehicle v where d.registration_num = query";
		PreparedStatement statement = conn.prepareStatement(sql);
		statement.setString(1, status);
		ResultSet resultSet = statement.executeQuery(sql);
		while (resultSet.next()) {
			//System.out.println("resultSet.getString(1) + resultSet.getString(2) + resultSet.getString(3) + resultSet.getString(4)");
			cabName = resultSet.getString(4);
		}
		/*System.out.println("are you there?");
		for (int i = 0; i < 5; i++) {
			System.out.println(details[i]);
		}*/
		
		return cabName;
	}

}
