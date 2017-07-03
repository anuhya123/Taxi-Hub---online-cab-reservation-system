package com.talentsprint.TaxiHub.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DriverStatusDAO {
	ConnectionDAO cdao = new ConnectionDAO();
	Connection conn = cdao.getCon();

	public boolean validateDriver(String number, String password) throws SQLException {
		// TODO Auto-generated method stub
		boolean validate = false;
		String DBPhone = null;
		String DBPassword = null;
		String sql = "select * from driver where driver_phone_num =?";
		
		PreparedStatement statement = conn.prepareStatement(sql);
		statement.setString(1, number);
		ResultSet resultSet = statement.executeQuery();
			while (resultSet.next()) {
				//DBName = resultSet.getString(1);
				DBPhone = resultSet.getString(2);
				DBPassword = resultSet.getString(4);
				if ((DBPhone.equals(number)) && (DBPassword.equals(password))) {
					validate = true;
				} 
			}
		return validate;
	}
	
	public String getVehicleNumber(String number) throws SQLException {
		// TODO Auto-generated method stub
		String VehNum = null;
		String sql = "select * from driver where driver_phone_num =?";
		PreparedStatement statement = conn.prepareStatement(sql);
		statement.setString(1, number);
		ResultSet resultSet = statement.executeQuery();
		while(resultSet.next()) {
			VehNum = resultSet.getString(3);
		}
		return VehNum;
	}

	public boolean updateStatus(String registration, String password) throws SQLException {
		boolean update = false;
		String sql = "update vehicle set status = ? where registration_num = ?";
		PreparedStatement statement = conn.prepareStatement(sql);
		statement.setString(1, "available");
		statement.setString(2, registration);
		int i= statement.executeUpdate();
		if (i > 0) {
			update = true;
		}
		return update;		
	}

	public String getUserName(String number) throws SQLException {
		// TODO Auto-generated method stub
		String DBName = null;
		String sql = "select * from driver where driver_phone_num =?";
		PreparedStatement statement = conn.prepareStatement(sql);
		statement.setString(1, number);
		ResultSet resultSet = statement.executeQuery();
		while(resultSet.next()) {
			DBName = resultSet.getString(1);
		}
		return DBName;
	}

	public String getVehicleName(String number) throws SQLException {
		String VehName = null;
		String sql = "select * from vehicle where registration_num in (select registration_num from driver where driver_phone_num =?)";
		PreparedStatement statement = conn.prepareStatement(sql);
		statement.setString(1, number);
		ResultSet resultSet = statement.executeQuery();
		while(resultSet.next()) {
			VehName = resultSet.getString(1);
		}
		System.out.println(VehName);
		return VehName;
	}

	public boolean resetStatus(String registration) throws SQLException {
		boolean update = false;
		System.out.println(registration);
		String sql = "update vehicle set status = ? where registration_num = ?";
		PreparedStatement statement = conn.prepareStatement(sql);
		statement.setString(1, "unavailable");
		statement.setString(2, registration);
		int i= statement.executeUpdate();
		if (i > 0) {
			update = true;
		}
		System.out.println(update);
		return update;		
	}
}
