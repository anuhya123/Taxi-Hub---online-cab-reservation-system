package com.talentsprint.TaxiHub.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class RegisterDAO {
	ConnectionDAO cdao = new ConnectionDAO();
	Connection conn = cdao.getCon();
	
	public boolean insertValidate(String user, String number, String password) throws SQLException {
		boolean status = true;
		String sql = "insert into user values(?,?,?)";

			PreparedStatement preparedStatement = conn.prepareStatement(sql);
			preparedStatement.setString(1, user);
			preparedStatement.setString(2, number);
			preparedStatement.setString(3, password);
			int i= preparedStatement.executeUpdate();
			if (i > 0) {
				String stm = "select * from user";
				
				Statement statement =conn.createStatement();
				ResultSet rs = statement.executeQuery(stm);
				if(rs.next()){
					if(rs.getString(2).equals(number) || (rs.getString(3).length() < 8)){
						status = false;
					}
			    }
		    }
		return status;
	}

	public boolean userNumberExists(String number) throws SQLException {
		 boolean userNumberExists = false;
		 PreparedStatement st = conn.prepareStatement("select * from user");
	        ResultSet rs = st.executeQuery();
	        String userNumberCounter;
	         if(rs.next()) 
	         {
	           userNumberCounter =  rs.getString("phone_number");
	           if(userNumberCounter == number) //this part does not happen even if it should
	           {
	              // System.out.println("It already exists");
	              userNumberExists = true;
	           }
	         }
	         return userNumberExists;
	}
}
		/*	if(i>0){
				status = true;
			} else {
				status = false;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return status;*/

