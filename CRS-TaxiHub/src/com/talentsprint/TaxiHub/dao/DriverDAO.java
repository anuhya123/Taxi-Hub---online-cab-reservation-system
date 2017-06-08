package com.talentsprint.TaxiHub.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DriverDAO {
	ConnectionDAO cdao = new ConnectionDAO();
	Connection conn = cdao.getCon();
	
	public boolean isDriverRegistered(String name, String number, String registration, String password) throws SQLException {
		boolean result = false;
		String sql = "insert into driver values(?,?,?,?)";
		PreparedStatement statement = conn.prepareStatement(sql);
		statement.setString(1, name);
		statement.setString(2, number);
		statement.setString(3, registration);
		statement.setString(4, password);		
		int i= statement.executeUpdate();
		if (i > 0){
			result = true;
		}
		return result;
	}
	
	public boolean isVehicleRegistered(String vname,String registration,String cabPack) throws SQLException {
		boolean result = false;
		String sql = "insert into vehicle values(?,?,?,?)";
		PreparedStatement statement = conn.prepareStatement(sql);
		statement.setString(1, vname);
		statement.setString(2, registration);
		statement.setString(3, cabPack);
		statement.setString(4, "available");		
		int i= statement.executeUpdate();
		if (i > 0){
			result = true;
		}
		return result;
	}
	
	public boolean validateDriver(String number, String password) throws SQLException {
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
	
	public String getUserName(String number) throws SQLException {
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
	
	public String getVehicleNumber(String number) throws SQLException {
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
	
	public String getVehicleName(String number) throws SQLException {
		String VehName = null;
		String sql = "select * from vehicle where (select registration_num from driver where driver_phone_num =?)";
		PreparedStatement statement = conn.prepareStatement(sql);
		statement.setString(1, number);
		ResultSet resultSet = statement.executeQuery();
		while(resultSet.next()) {
			VehName = resultSet.getString(1);
		}
		return VehName;
	}
	
	
}
