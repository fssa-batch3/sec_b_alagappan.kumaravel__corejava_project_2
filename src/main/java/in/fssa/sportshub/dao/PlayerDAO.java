package in.fssa.sportshub.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import in.fssa.sportshub.model.Player;
import in.fssa.sportshub.util.ConnectionUtil;

public class PlayerDAO {
public boolean playerExist(int id){
	boolean value;
	Connection con = null;
	PreparedStatement ps = null;
	ResultSet rs = null;
	try {
		String query = "SELECT * FROM players WHERE is_active=1 && id = ?";
		
		con = ConnectionUtil.getConnection();
		ps = con.prepareStatement(query);
		ps.setInt(1, id);
		rs = ps.executeQuery();
	    if (rs.next()) {
	    	value = true;
	    }else {
	    	System.out.println("no");
	    	value = false;
	    }
		
	}catch(SQLException e) {
		e.printStackTrace();
		System.out.println(e.getMessage());
		throw new RuntimeException(e);
	}finally {
		ConnectionUtil.close(con,ps,rs);
	}
	
	return value;
}
	
public void create(Player player) {

	Connection con = null;
	PreparedStatement ps = null;
	ResultSet rs = null;
	try {
		String query = "Insert into players (phone_number, user_name,first_name,last_name,image,password,gender,address_id,date_of_birth,about)"
				+ " Values (?,?,?,?,?,?,?,?,?,?)";
		
		con = ConnectionUtil.getConnection();
		ps = con.prepareStatement(query);
		ps.setLong(1, player.getPhoneNumber());
		ps.setString(2, player.getUserName());
		ps.setString(3, player.getFirstName());
		ps.setString(4, player.getLastName());
		ps.setString(5, player.getUrl());
		ps.setString(6, player.getPassword());
		ps.setString(7, player.getGender().toString());
		ps.setInt(8, player.getAddress().getId());
		ps.setDate(9,java.sql.Date.valueOf(player.getDateOfBirth()));
		ps.setString(10,player.getAbout());
		int rowsAffected = ps.executeUpdate();
		if (rowsAffected > 0) {
			System.out.println("Player created");
		}else {
			throw new RuntimeException("Sql issue: Player not created");
		}
		
	}catch(SQLException e) {
		e.printStackTrace();
		System.out.println(e.getMessage());
		throw new RuntimeException(e);
	}finally {
		ConnectionUtil.close(con,ps,rs);
	}
	
}
public boolean phoneNumberAlreadyExist(long phoneNumber) {
	boolean value;
	Connection con = null;
	PreparedStatement ps = null;
	ResultSet rs = null;
	try {
		String query = "SELECT * FROM players WHERE phone_number = ?";
		
		con = ConnectionUtil.getConnection();
		ps = con.prepareStatement(query);
		ps.setLong(1, phoneNumber);
		rs = ps.executeQuery();
	    if (rs.next()) {
	    	value = true;
	    }else {
	    	value = false;
	    }
		
	}catch(SQLException e) {
		e.printStackTrace();
		System.out.println(e.getMessage());
		throw new RuntimeException(e);
	}finally {
		ConnectionUtil.close(con,ps,rs);
	}
	
	return value;
}

public void update(Player player) {

	Connection con = null;
	PreparedStatement ps = null;
	ResultSet rs = null;
	try {
		String query = "UPDATE players SET user_name =? ,first_name =?,last_name =? ,image =? ,gender =? ,address_id =? ,date_of_birth =?,about =?"
				+ "WHERE id = ?";
		
		con = ConnectionUtil.getConnection();
		ps = con.prepareStatement(query);
		ps.setString(1, player.getUserName());
		ps.setString(2, player.getFirstName());
		ps.setString(3, player.getLastName());
		ps.setString(4, player.getUrl());
		ps.setString(5, player.getGender().toString());
		ps.setInt(6, player.getAddress().getId());
		ps.setDate(7,java.sql.Date.valueOf(player.getDateOfBirth()));
		ps.setString(8,player.getAbout());
		ps.setInt(9, player.getId());
		
		int rowsAffected = ps.executeUpdate();
		if (rowsAffected > 0) {
			System.out.println("Update created");
		}else {
			throw new RuntimeException("Sql issue: Player not created");
		}
		
	}catch(SQLException e) {
		e.printStackTrace();
		System.out.println(e.getMessage());
		throw new RuntimeException(e);
	}finally {
		ConnectionUtil.close(con,ps,rs);
	}
	
}


public void delete(int id) {

	Connection con = null;
	PreparedStatement ps = null;
	ResultSet rs = null;
	try {
		String query = "UPDATE players SET is_active =0 WHERE id=?";
		
		con = ConnectionUtil.getConnection();
		ps = con.prepareStatement(query);
		ps.setInt(1, id);
		
		int rowsAffected = ps.executeUpdate();
		if (rowsAffected > 0) {
			System.out.println("Player deleted");
		}else {
			throw new RuntimeException("Sql issue: Player not created");
		}
		
	}catch(SQLException e) {
		e.printStackTrace();
		System.out.println(e.getMessage());
		throw new RuntimeException(e);
	}finally {
		ConnectionUtil.close(con,ps,rs);
	}
	
}
}
