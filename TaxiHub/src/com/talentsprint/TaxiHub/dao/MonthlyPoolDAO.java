package com.talentsprint.TaxiHub.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class MonthlyPoolDAO {
	ConnectionDAO cdao = new ConnectionDAO();
	Connection conn = cdao.getCon();
	
	public boolean availFeature(String email,String number,String cabNumber,String source,String destination,String fromDate,int ridePackage) throws SQLException {
		boolean enabled = false;
		String sql = "insert into Monthly_pool_user values(?,?,?,?,?,?,?)";

		PreparedStatement preparedStatement = conn.prepareStatement(sql);
		preparedStatement.setString(1,email );
		preparedStatement.setString(2,number);
		preparedStatement.setString(3, cabNumber);
		preparedStatement.setString(4, source);
		preparedStatement.setString(5, destination);
		preparedStatement.setString(6, fromDate);
		preparedStatement.setInt(7, ridePackage);
		int i= preparedStatement.executeUpdate();
		if (i > 0) {
			enabled = true;
		}
		return enabled;
	}
}
