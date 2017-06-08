package com.talentsprint.TaxiHub.dao;

import static com.talentsprint.TaxiHub.controller.Provider.*;

import java.sql.Connection;
import java.sql.DriverManager;

public class ConnectionDAO {
	static Connection con=null;
	static{
		try{
			Class.forName(DRIVER);
			con=DriverManager.getConnection(CONNECTION_URL,USERNAME,PASSWORD);
			}catch(Exception e){}
	}
	public static Connection getCon(){
		return con;
	}
}
