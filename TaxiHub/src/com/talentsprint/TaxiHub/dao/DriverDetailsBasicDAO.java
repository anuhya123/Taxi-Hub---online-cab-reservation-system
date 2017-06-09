package com.talentsprint.TaxiHub.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DriverDetailsBasicDAO {
	ConnectionDAO cdao = new ConnectionDAO();
	Connection conn = cdao.getCon();
	
	public void insertIntoBookings(String phone) throws SQLException {
		boolean inserted = false;
		String sql = "insert into bookings(phone_num,amount_paid) values(?,?)";
		

		PreparedStatement preparedStatement = conn.prepareStatement(sql);
		preparedStatement.setString(1, phone);
		preparedStatement.setString(2, "0");
		preparedStatement.executeUpdate();
	}
	
	public void insertIntoBookings(String phone, String source, String destination) throws SQLException {
		boolean inserted = false;
		String sql = "insert into bookings(phone_num, source,destination,amount_paid) values(?,?,?,?)";
		

		PreparedStatement preparedStatement = conn.prepareStatement(sql);
		preparedStatement.setString(1, phone);
		//preparedStatement.setString(2, registration_num);
		preparedStatement.setString(2, source);
		preparedStatement.setString(3, destination);
		preparedStatement.setString(4, "0");
		preparedStatement.executeUpdate();
	}
	public void insertIntoBookings(String phone, String registration_num, String source, String destination) throws SQLException {
		boolean inserted = false;
		String sql = "insert into bookings(phone_num, registration_num, source,destination,amount_paid) values(?,?,?,?,?)";
		

		PreparedStatement preparedStatement = conn.prepareStatement(sql);
		preparedStatement.setString(1, phone);
		preparedStatement.setString(2, registration_num);
		preparedStatement.setString(3, source);
		preparedStatement.setString(4, destination);
		preparedStatement.setString(5, "0");
		preparedStatement.executeUpdate();
	}
		/*if (i > 0) {
			Statement statement =conn.createStatement();
			ResultSet rs = statement.executeQuery(sql);
			if(rs.next()){
				if(rs.getString(2).equals(number) || (rs.getString(3).length() < 8)){
					status = false;
				}
		    }
	    }*/

	public String getDriverEmailBasic(String status) throws SQLException {
		String email = null;
		String sql = "select email from driver where registration_num in (select registration_num from vehicle where status = ? and cost_per_km = 6)";
		PreparedStatement pstat = conn.prepareStatement(sql);
		pstat.setString(1, status);
		ResultSet rs = pstat.executeQuery();
		while (rs.next()) {
			email = rs.getString(1);
		}
		//System.out.println(email);
		return email;
	}
	
	public String retrieveCabDriver(String status) throws SQLException {
		String driver = null;
		String sql =  "select driver_name from driver where registration_num in (select registration_num from vehicle where status = ? and cost_per_km = 6)";
		PreparedStatement statement = conn.prepareStatement(sql);
		statement.setString(1, status);
		ResultSet resultSet = statement.executeQuery();
		while (resultSet.next()) {
			driver = resultSet.getString(1);
		}
		//System.out.println("driver");
		return driver;
	}

	public String retrieveDriverPhone(String status) throws SQLException {
		String phone = null;
		String sql =  "select driver_phone_num from driver where registration_num in (select registration_num from vehicle where status = ? and cost_per_km = 6)";
		PreparedStatement statement = conn.prepareStatement(sql);
		statement.setString(1, status);
		ResultSet resultSet = statement.executeQuery();
		while (resultSet.next()) {
			phone = resultSet.getString(1);
		}
		//System.out.println("driver");
		return phone;
	}
	
	public String retrieveCabNumber(String status) throws SQLException {
		String cabNum = null;
		String sql =  "select registration_num from vehicle where status = ? and cost_per_km = 6";
		PreparedStatement statement = conn.prepareStatement(sql);
		statement.setString(1, status);
		ResultSet resultSet = statement.executeQuery();
		while (resultSet.next()) {
			cabNum = resultSet.getString(1);
		}
		//System.out.println("driver");
		return cabNum;
	}
}
