package com.talentsprint.TaxiHub.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DriverEmailDAO {
	ConnectionDAO cdao = new ConnectionDAO();
	Connection conn = cdao.getCon();
	
	public String getDriverEmailBasic(String status) throws SQLException {
		String email = null;
		String sql = "select email from driver where registration_num in (select registration_num from vehicle where status = ? and cost_per_kn = 6";
		PreparedStatement pstat = conn.prepareStatement(sql);
		pstat.setString(1, status);
		ResultSet rs = pstat.executeQuery();
		while (rs.next()) {
			email = rs.getString(1);
		}
		System.out.println(email);
		return email;
	}
}
