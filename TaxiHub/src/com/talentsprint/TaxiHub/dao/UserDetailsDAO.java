package com.talentsprint.TaxiHub.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.talentsprint.TaxiHub.model.BookingModel;

public class UserDetailsDAO {
	ConnectionDAO cdao = new ConnectionDAO();
	Connection conn = cdao.getCon();
	
	/*public String showRides(String registration) {
		Statement stmt = conn.createStatement();
		String query = "select phone_num,source,destination from bookings where registration_num= 'registration' and status='waiting'";
		ResultSet rs = stmt.executeQuery(query);	
		if(rs.next()) {
			out.println("<form action='Accept.jsp'>");
			out.println(rs.getInt(2));
			out.println("<input type='hidden' name='cid' value='"+rs.getInt(2)+"'>");
			out.println("<input type='submit' value='Accept'>");
			out.println("</form>");
		}
		return ("NO BOOKING AVAILABLE");
	}*/
	
	
	/*public void accept(String phone_num) {
		Statement stmt = conn.createStatement();
		String cid = request.getParameter("cid");
		
		String query1 = "update ride set status='accepted' where did=101 and cid="+cid;
		int row = stmt.executeUpdate(query1);	
		if(row!=0) {
			out.println("DONE");
			String query2 = "update driver set dstatus='unavailable' where did=101";
			int row2 = stmt.executeUpdate(query2);
			if(row2!=0) {
				out.println("DONE");
			}
		}
	}*/
	public String getUserPhone(String registration) throws SQLException {
		//List<String> bookings = new ArrayList<String>();
		String unum = " ";
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
		String source = " ";
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
		String destination = " ";
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
		String datetime = " ";
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
	
	public ArrayList<BookingModel> displayDetails(String registration) throws SQLException {
		//List dataList = new ArrayList(); 
		ArrayList<BookingModel> dataList = new ArrayList<BookingModel>();
		String sql = "select phone_num,source,destination,date_time from bookings where registration_num = ?";
		PreparedStatement pstat = conn.prepareStatement(sql);
		pstat.setString(1, registration);
		ResultSet rs = pstat.executeQuery();
		/*ResultSetMetaData rsmd = rs.getMetaData();
		int columns = rsmd.getColumnCount();*/
		while(rs.next()) {
			BookingModel model = new BookingModel();
				model.setPhone_num(rs.getString(1));
				model.setSource(rs.getString(2));
				model.setDestination(rs.getString(3));
				
				dataList.add(model);
		}
		System.out.println(dataList);
		return dataList;
	}
	
	public String resetStatus(String registration) throws SQLException {
		//boolean update = false;
		System.out.println(registration);
		String sql = "update vehicle set status = ? where registration_num = ?";
		PreparedStatement statement = conn.prepareStatement(sql);
		statement.setString(1, "accepted");
		statement.setString(2, registration);
		int i= statement.executeUpdate();
		if (i != 0) {
			String query2 = "update driver set driver_status=? where registration_num=?";
			PreparedStatement stat = conn.prepareStatement(query2);
			stat.setString(1, "unavailable");
			stat.setString(2, registration);
			int row2 = statement.executeUpdate();
			if(row2!=0) {
				return("END OF RIDE!");
			}
		}
		//System.out.println(update);
		return "";		
	}
}
