package com.talentsprint.TaxiHub.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DriverDAO {
	ConnectionDAO cdao = new ConnectionDAO();
	Connection conn = cdao.getCon();

	public boolean validateVehicle(String registration) throws SQLException {
		boolean isPresent = false;
		String sql = "select registration_num from vehicle";
		Statement statement = conn.createStatement();
		ResultSet rs = statement.executeQuery(sql);
		while(rs.next()) {
			String DBRegis = rs.getString(1);
			if(DBRegis.equals(registration)) {
				isPresent = true;
			}
		}
		System.out.println(isPresent);
		return isPresent;
	}
	
	public boolean validateDriver(String registration) throws SQLException {
		boolean validate = false;
		String DBPhone = null;
		String DBPassword = null;
		String sql = "select registration_num from driver";
		Statement statement = conn.createStatement();
		ResultSet resultSet = statement.executeQuery(sql);
		while (resultSet.next()) {
			String DBRegis = resultSet.getString(1);
			if ((DBRegis.equals(registration))) {
					validate = true;
			} 
		}
		System.out.println(validate);
		return validate;
	}
	
	public boolean registerDriver(String name, String number, String registration, String password) throws SQLException {
		boolean result = false;
		String sql = "insert into driver(driver_name, driver_phone_num,registration_num,password) values(?,?,?,?)";
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
	
	public boolean registerVehicle(String vname,String registration,String cabPack) throws SQLException {
		boolean result = false;
		String sql = "insert into vehicle values(?,?,?,?)";
		PreparedStatement statement = conn.prepareStatement(sql);
		statement.setString(1, vname);
		statement.setString(2, registration);
		statement.setString(3, cabPack);
		statement.setString(4, "unavailable");		
		int i= statement.executeUpdate();
		if (i > 0){
			result = true;
		}
		return result;
	}
}