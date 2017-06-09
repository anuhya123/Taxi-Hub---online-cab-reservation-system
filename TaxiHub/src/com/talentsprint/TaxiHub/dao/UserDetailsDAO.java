package com.talentsprint.TaxiHub.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class UserDetailsDAO {
	ConnectionDAO cdao = new ConnectionDAO();
	Connection conn = cdao.getCon();
	
	public String getUserPhone(String registration) throws SQLException {
		//List<String> bookings = new ArrayList<String>();
		String unum = null;
		String sql = "select phone_num from bookings where registration_num = ?";
		PreparedStatement st = conn.prepareStatement(sql);
		st.setString(1, registration);
		ResultSet rs = st.executeQuery();
		while (rs.next()) {
			unum = rs.getString(1);
		}
		return unum;
	}
	

	public String getSource(String registration) throws SQLException {
		//List<String> bookings = new ArrayList<String>();
		String source = null;
		String sql = "select source from bookings where registration_num = ?";
		PreparedStatement st = conn.prepareStatement(sql);
		st.setString(1, registration);
		ResultSet rs = st.executeQuery();
		while(rs.next()){
			source = rs.getString(1);
		}
		return source;
	}
	
	public String getDestination(String registration) throws SQLException {
		//List<String> bookings = new ArrayList<String>();
		String destination = null;
		String sql = "select destination from bookings where registration_num = ?";
		PreparedStatement st = conn.prepareStatement(sql);
		st.setString(1, registration);
		ResultSet rs = st.executeQuery();
		while(rs.next()){
			destination = rs.getString(1);
		}
		return destination;
	}
	
	public String getDateTime(String registration) throws SQLException {
		//List<String> bookings = new ArrayList<String>();
		String datetime = null;
		String sql = "select date_time from bookings where registration_num = ?";
		PreparedStatement st = conn.prepareStatement(sql);
		st.setString(1, registration);
		ResultSet rs = st.executeQuery();
		while(rs.next()){
			datetime = rs.getString(1);
		}
		return datetime;
	}


	public int getDid(String registration) throws SQLException {
		int did = 0;
		String sql = "select driver_id from driver where registration_num = ?";
		PreparedStatement pstat = conn.prepareStatement(sql);
		pstat.setString(1, registration);
		ResultSet rs = pstat.executeQuery();
		while(rs.next()) {
			did = rs.getInt(1);
		}
		// TODO Auto-generated method stub
		return did;
	}

}
