package com.talentsprint.TaxiHub.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class LoginDAO {
	ConnectionDAO cdao = new ConnectionDAO();
	Connection conn = cdao.getCon();
		
	public  boolean validate(String number, String password) throws SQLException{
		boolean status = false;
		//String DBName = null;
		String DBPhone = null;
		String DBPassword = null;
		String sql = "select * from user where phone_num =?";
		
		PreparedStatement statement = conn.prepareStatement(sql);
		statement.setString(1, number);
		ResultSet resultSet = statement.executeQuery();
			while (resultSet.next()) {
				//DBName = resultSet.getString(1);
				DBPhone = resultSet.getString(2);
				DBPassword = resultSet.getString(3);
				if ((DBPhone.equals(number)) && (DBPassword.equals(password))) {
					status = true;
				} 
			}
		
		return status;
	}

	public String getUserName(String number) throws SQLException {
		String DBName = null;
		String sql = "select * from user where phone_num =?";
		PreparedStatement statement = conn.prepareStatement(sql);
		statement.setString(1, number);
		ResultSet resultSet = statement.executeQuery();
		while(resultSet.next()) {
			DBName = resultSet.getString(1);
		}
		return DBName;
	}
}			
			/*if (resultSet.getString(3).equals(password)) {
				status = true;
			} else {
				status = false;
			}
		}
		return status;
	}
} */
		
