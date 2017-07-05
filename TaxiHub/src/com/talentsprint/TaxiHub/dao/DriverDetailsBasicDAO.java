package com.talentsprint.TaxiHub.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import com.talentsprint.TaxiHub.model.BookingModel;
import com.talentsprint.TaxiHub.model.DriverModel;

public class DriverDetailsBasicDAO {
	ConnectionDAO cdao = new ConnectionDAO();
	Connection conn = cdao.getCon();
	
	public String bookRide(String phone_num, String source, String destination) throws SQLException{
		Statement stmt = conn.createStatement();
		String query = "select driver_id,registration_num from driver where registration_num in(select registration_num from vehicle where status='available')";
		ResultSet rs = stmt.executeQuery(query);	
		while(rs.next()) {
			String query2 = "insert into bookings(phone_num, driver_id, registration_num, source,destination,amount_paid,cost_per_km,booking_status) values(?,?,?,?,?,?,?,?)";
			PreparedStatement stmt2 = conn.prepareStatement(query2);
			stmt2.setString(1, phone_num);
			stmt2.setString(2, rs.getString(1));
			stmt2.setString(3, rs.getString(2));
			stmt2.setString(4, source);
			stmt2.setString(5, destination);
			stmt2.setString(6, "0");
			stmt2.setInt(7, 6);
			stmt2.setString(8, "waiting");
			int row = stmt2.executeUpdate();
			if(row!=0)
				return ("WAITING FOR CONFIRMATION!!");
		} 
		return ("CABS NOT AVAILABLE");
	}
	
	public ArrayList<DriverModel> displayDriverDetails(String phone) throws SQLException {
		//List dataList = new ArrayList(); 
		ArrayList<DriverModel> driverList = new ArrayList<DriverModel>();
		String sql = "select driver_name,driver_phone_num,registration_num from driver where registration_num in (select registration_num from bookings where booking_status = ? and phone_num = ? and cost_per_km = 6)";
		PreparedStatement pstat = conn.prepareStatement(sql);
		pstat.setString(1, phone);
		pstat.setString(2, "accepted");
		ResultSet rs = pstat.executeQuery();
		/*ResultSetMetaData rsmd = rs.getMetaData();
		int columns = rsmd.getColumnCount();*/
		while(rs.next()) {
			DriverModel model = new DriverModel();
				model.setDriver_name(rs.getString(1));
				model.setDriver_phone_num(rs.getString(2));
				model.setRegistration_num(rs.getString(3));
				
				driverList.add(model);
		}
		System.out.println(driverList);
		return driverList;
	}
	public String retrieveCabDriver(String contact,String status) throws SQLException {
		String driver = null;
		String sql =  "select driver_name from driver where registration_num in (select registration_num from bookings where booking_status = ? and phone_num = ? and cost_per_km = 6)";
		PreparedStatement statement = conn.prepareStatement(sql);
		statement.setString(1, status);
		statement.setString(2, contact);
		ResultSet resultSet = statement.executeQuery();
		if(resultSet.next()) {
			driver = resultSet.getString(1);
		}
		System.out.println(driver);
		return driver;
	}

	public String retrieveDriverPhone(String status) throws SQLException {
		String phone = " ";
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
		statement.setString(1, "waiting");
		ResultSet resultSet = statement.executeQuery();
		while (resultSet.next()) {
			cabNum = resultSet.getString(1);
		}
		//System.out.println("driver");
		return cabNum;
	}

	public int retrieveDriverId(String status) throws SQLException {
		int did = 0;
		String sql =  "select driver_id from driver where registration_num in (select registration_num from vehicle where status = ? and cost_per_km = 6)";
		PreparedStatement statement = conn.prepareStatement(sql);
		statement.setString(1, status);
		ResultSet resultSet = statement.executeQuery();
		while (resultSet.next()) {
			did = resultSet.getInt(1);
		}
		//System.out.println("driver");
		// TODO Auto-generated method stub
		return did;
	}
}
