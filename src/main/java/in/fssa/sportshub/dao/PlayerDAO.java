package in.fssa.sportshub.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import in.fssa.sportshub.exception.PersistanceException;
import in.fssa.sportshub.exception.ValidationException;
import in.fssa.sportshub.model.Gender;
import in.fssa.sportshub.model.Player;
import in.fssa.sportshub.util.ConnectionUtil;

public class PlayerDAO {
	
	/**
     * Checks if a player with the given ID exists and is active in the database.
     * 
     * @param id The player ID to check
     * @return True if a player with the given ID exists and is active, false otherwise
     * @throws PersistanceException If there's an issue with database operations
     */
public boolean checkIfExistById(int id) throws PersistanceException{
	
	boolean value;
	Connection con = null;
	PreparedStatement ps = null;
	ResultSet rs = null;
	try {
		String query = "SELECT id FROM players WHERE is_active=1 && id = ?";
		
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
		
		throw new PersistanceException(e.getMessage());
	}finally {
		ConnectionUtil.close(con,ps,rs);
	}
	
	return value;
}
	/**
	 * Creates a new player record in the database.
	 * 
	 * @param player The player object to be created
	 * @throws PersistanceException If there's an issue with database operations
	 */
public void create(Player player) throws PersistanceException{

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
		
		throw new PersistanceException(e.getMessage());
	}finally {
		ConnectionUtil.close(con,ps,rs);
	}
	
}

/**
 * Checks if a player with the given phone number already exists in the database.
 * 
 * @param phoneNumber The phone number to check
 * @return True if a player with the given phone number exists, false otherwise
 * @throws PersistanceException If there's an issue with database operations
 */
public boolean phoneNumberAlreadyExist(long phoneNumber) throws PersistanceException{
	boolean value;
	Connection con = null;
	PreparedStatement ps = null;
	ResultSet rs = null;
	try {
		String query = "SELECT id FROM players WHERE phone_number = ?";
		
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
		
		throw new PersistanceException(e.getMessage());
	}finally {
		ConnectionUtil.close(con,ps,rs);
	}
	
	return value;
}

/**
 * Updates an existing player's information in the database.
 * 
 * @param player The player object with updated information
 * @throws PersistanceException If there's an issue with database operations
 */

public void update(Player player) throws PersistanceException{
	System.out.println("dao in");
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
		
		throw new PersistanceException(e.getMessage());
	}finally {
		ConnectionUtil.close(con,ps,rs);
		
	}
	System.out.println("dao out");
}

/**
 * Deletes a player by setting the is_active field to 0 in the database.
 * 
 * @param id The ID of the player to be deleted
 * @throws PersistanceException If there's an issue with database operations
 */

public void delete(int id) throws PersistanceException{

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
			throw new RuntimeException("Sql issue: Player not deleted");
		}
		
	}catch(SQLException e) {
		e.printStackTrace();
		
		throw new PersistanceException(e.getMessage());
	}finally {
		ConnectionUtil.close(con,ps,rs);
	}
	
}







/**
 * Changes the is_active status of a deleted player back to 1 in the database.
 * 
 * @param id The ID of the player to be reactivated
 * @throws PersistanceException If there's an issue with database operations
 */
public void changeDelete(int id) throws PersistanceException{

	Connection con = null;
	PreparedStatement ps = null;
	ResultSet rs = null;
	try {
		String query = "UPDATE players SET is_active =1 WHERE id=?";
		
		con = ConnectionUtil.getConnection();
		ps = con.prepareStatement(query);
		ps.setInt(1, id);
		
		int rowsAffected = ps.executeUpdate();
		if (rowsAffected > 0) {
			System.out.println("Player deleted changed");
		}else {
			throw new RuntimeException("Sql issue: Player Change not done correctly");
		}
		
	}catch(SQLException e) {
		e.printStackTrace();
		
		throw new PersistanceException(e.getMessage());
	}finally {
		ConnectionUtil.close(con,ps,rs);
	}
	
}


public int findByPhoneNumber(long phoneNumber) throws PersistanceException{
	int value;
	Connection con = null;
	PreparedStatement ps = null;
	ResultSet rs = null;
	try {
		String query = "SELECT id FROM players WHERE is_active= 1 && phone_number = ? ";
		
		con = ConnectionUtil.getConnection();
		ps = con.prepareStatement(query);
		ps.setLong(1, phoneNumber);
		rs = ps.executeQuery();
	    if (rs.next()) {
	    	value = rs.getInt("id");
	    	System.out.println(value + "<===");
	    	System.out.println(phoneNumber + "<===");
	    }else {
	    	throw new PersistanceException("Player not found");
	    }
		
	}catch(SQLException e) {
		e.printStackTrace();
		
		throw new PersistanceException(e.getMessage());
	}finally {
		ConnectionUtil.close(con,ps,rs);
	}
	
	return value;
}

public String getPasswordByPhonenumber(long phoneNumber) throws PersistanceException{
	String value;
	Connection con = null;
	PreparedStatement ps = null;
	ResultSet rs = null;
	try {
		String query = "SELECT password FROM players WHERE is_active= 1 && phone_number = ? ";
		
		con = ConnectionUtil.getConnection();
		ps = con.prepareStatement(query);
		ps.setLong(1, phoneNumber);
		rs = ps.executeQuery();
	    if (rs.next()) {
	    	value = rs.getString("password");
	    }else {
	    	throw new PersistanceException("Player not found");
	    }
		
	}catch(SQLException e) {
		e.printStackTrace();
		
		throw new PersistanceException(e.getMessage());
	}finally {
		ConnectionUtil.close(con,ps,rs);
	}
	
	return value;
}

public int logIn(long phoneNumber, String password) throws PersistanceException{
	int value;
	Connection con = null;
	PreparedStatement ps = null;
	ResultSet rs = null;
	try {
		String query = "SELECT id FROM players WHERE phone_number = ? && password = ? && is_active=1";
		con = ConnectionUtil.getConnection();
		ps = con.prepareStatement(query);
		ps.setLong(1, phoneNumber);
		ps.setString(2, password);
		rs = ps.executeQuery();
	    if (rs.next()) {
	    	value = rs.getInt("id");
	    	System.out.println(value);
	    }else {
	    	throw new PersistanceException("Player not found");
	    }
		
	}catch(SQLException e) {
		e.printStackTrace();
		
		throw new PersistanceException(e.getMessage());
	}finally {
		ConnectionUtil.close(con,ps,rs);
	}
	
	return value;
}

public Player findById(int id) throws PersistanceException{
	Player player = new Player();
	Connection con = null;
	PreparedStatement ps = null;
	ResultSet rs = null;
	try {
		String query = "SELECT * FROM players AS p INNER JOIN address AS a ON p.address_id = a.id WHERE p.id = ? ";
		
		con = ConnectionUtil.getConnection();
		ps = con.prepareStatement(query);
		ps.setLong(1, id);
		rs = ps.executeQuery();
	    if (rs.next()) {
	    	player.setId(rs.getInt("id"));
			player.setPhoneNumber(rs.getLong("phone_number"));
			player.setUserName(rs.getString("user_name"));
			player.setFirstName(rs.getString("first_name"));
			player.setLastName(rs.getString("last_name"));
			player.setUrl(rs.getString("image"));
			player.setPassword(rs.getString("password"));
			player.setGender(Gender.valueOf(rs.getString("gender").toUpperCase()));
			player.getAddress().setId(rs.getInt("address_id"));
			player.getAddress().setArea(rs.getString("area"));
			player.getAddress().setDistrict(rs.getString("district"));
			String dateString = rs.getDate("date_of_birth") + "";
			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
			LocalDate localDate = LocalDate.parse(dateString, formatter);
			
			player.setDateOfBirth(localDate);
			player.setAbout(rs.getString("about"));
			player.setCreatedAt(rs.getTimestamp("created_at").toLocalDateTime());
			player.setModifiedAt(rs.getTimestamp("modified_at").toLocalDateTime());
	    }else {
	    	throw new PersistanceException("Player not found");
	    }
		
	}catch(SQLException e) {
		e.printStackTrace();
		
		throw new PersistanceException(e.getMessage());
	}finally {
		ConnectionUtil.close(con,ps,rs);
	}
	
	return player;
}

}
