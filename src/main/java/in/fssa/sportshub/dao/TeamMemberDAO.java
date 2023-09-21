package in.fssa.sportshub.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.HashSet;
import java.util.Set;
import java.sql.Timestamp;
import java.util.Date;

import in.fssa.sportshub.exception.PersistanceException;
import in.fssa.sportshub.model.Gender;
import in.fssa.sportshub.model.Player;
import in.fssa.sportshub.model.PlayerRequestDTO;
import in.fssa.sportshub.model.TeamMember;
import in.fssa.sportshub.model.TeamRequestDTO;
import in.fssa.sportshub.util.ConnectionUtil;

public class TeamMemberDAO {
	public void create(TeamMember teamMember)throws PersistanceException {

		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			String query = "Insert into team_members (team_id, user_id, is_captain, request_status, start_date) Values (?,?,1,1,?)";
			
			con = ConnectionUtil.getConnection();
			ps = con.prepareStatement(query);
			ps.setInt(1, teamMember.getTeamId());
			ps.setInt(2, teamMember.getUserId());
			Timestamp currentTimestamp = new Timestamp(new Date().getTime());
			ps.setTimestamp(3, currentTimestamp);
			int rowsAffected = ps.executeUpdate();
			if (rowsAffected > 0) {
				System.out.println("team member created");
			}else {
				throw new PersistanceException("Sql issue: team member not created");
			}
			
		}catch(SQLException e) {
			e.printStackTrace();
			System.out.println(e.getMessage());
			throw new PersistanceException(e.getMessage());
		}finally {
			ConnectionUtil.close(con,ps,rs);
		}
		
	}
	
	public void createJoinRequest(TeamMember teamMember)throws PersistanceException {

		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			String query = "Insert into team_members (team_id, user_id, is_captain, request_status) Values (?,?,0,2)";
			
			con = ConnectionUtil.getConnection();
			ps = con.prepareStatement(query);
			ps.setInt(1, teamMember.getTeamId());	
			ps.setInt(2, teamMember.getUserId());
			int rowsAffected = ps.executeUpdate();
			if (rowsAffected > 0) {
				System.out.println("team member request created");
			}else {
				throw new PersistanceException("Sql issue: team member request not created");
			}
			
		}catch(SQLException e) {
			e.printStackTrace();
			System.out.println(e.getMessage());
			throw new PersistanceException(e.getMessage());
		}finally {
			ConnectionUtil.close(con,ps,rs);
		}
		
	}
	
	public boolean isPlayerCaptain(int id) throws PersistanceException{
		
		boolean value;
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			String query = "SELECT * FROM team_members WHERE is_active=1 && is_captain=1 && user_id = ?";
			
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
			throw new PersistanceException(e.getMessage());
		}finally {
			ConnectionUtil.close(con,ps,rs);
		}
		
		return value;
	}
	
public TeamMember findById(int id) throws PersistanceException{
	TeamMember teamMemberData = null;
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			String query = "SELECT * FROM team_members WHERE is_active=1 && id=?";
			
			con = ConnectionUtil.getConnection();
			ps = con.prepareStatement(query);
			ps.setInt(1, id);
			rs = ps.executeQuery();
		    if (rs.next()) {
		    	teamMemberData = new TeamMember();
		    	teamMemberData.setId(rs.getInt("id"));
		    	teamMemberData.setIsActive(rs.getInt("is_active"));
		    	teamMemberData.setIsCaptain(rs.getInt("is_captain"));
		    	teamMemberData.setTeamId(rs.getInt("team_id"));
		    	teamMemberData.setUserId(rs.getInt("user_id"));
		    }else {
		    	System.out.println("team member data not avaliable from dao");
		    }
			
		}catch(SQLException e) {
			e.printStackTrace();
			System.out.println(e.getMessage());
			throw new PersistanceException(e.getMessage());
		}finally {
			ConnectionUtil.close(con,ps,rs);
		}
		
		return teamMemberData;
	}

public TeamMember findByCaptainId(int id) throws PersistanceException{
	TeamMember teamMemberData = null;
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			String query = "SELECT * FROM team_members WHERE is_active=1 && is_captain=1 && user_id=?";
			
			con = ConnectionUtil.getConnection();
			ps = con.prepareStatement(query);
			ps.setInt(1, id);
			rs = ps.executeQuery();
		    if (rs.next()) {
		    	teamMemberData = new TeamMember();
		    	teamMemberData.setId(rs.getInt("id"));
		    	teamMemberData.setIsActive(rs.getInt("is_active"));
		    	teamMemberData.setIsCaptain(rs.getInt("is_captain"));
		    	teamMemberData.setTeamId(rs.getInt("team_id"));
		    	teamMemberData.setUserId(rs.getInt("user_id"));
		    }else {
		    	throw new PersistanceException("Player not captain of any team");
		    }
			
		}catch(SQLException e) {
			e.printStackTrace();
			System.out.println(e.getMessage());
			throw new PersistanceException(e.getMessage());
		}finally {
			ConnectionUtil.close(con,ps,rs);
		}
		
		return teamMemberData;
	}
	
public boolean isPlayerCaptainOfSpecificTeam(int player_id, int team_id) throws PersistanceException{
		
		boolean value;
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			String query = "SELECT * FROM team_members WHERE is_active=1 && is_captain=1 && user_id = ? && team_id = ?";
			
			con = ConnectionUtil.getConnection();
			ps = con.prepareStatement(query);
			ps.setInt(1, player_id);
			ps.setInt(2, team_id);
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
			throw new PersistanceException(e.getMessage());
		}finally {
			ConnectionUtil.close(con,ps,rs);
		}
		
		return value;
	}

public void delete(int teamId, int playerId) throws PersistanceException{
	
	Connection con = null;
	PreparedStatement ps = null;
	ResultSet rs = null;
	try {

		String query = "UPDATE team_members SET is_captain =0, is_active=0 "
				+ "WHERE team_id=? && user_id = ? && is_captain=1";
		con = ConnectionUtil.getConnection();
		ps = con.prepareStatement(query);
		ps.setInt(1, teamId);
		ps.setInt(2, playerId);
		int rowsAffected = ps.executeUpdate();
		if (rowsAffected > 0) {
			System.out.println("team member deleted");
		}else {
			throw new PersistanceException("Sql issue: team member not delete");
		}
		
	}catch(SQLException e) {
		e.printStackTrace();
		System.out.println(e.getMessage());
		throw new PersistanceException(e.getMessage());
	}finally {
		ConnectionUtil.close(con,ps,rs);
	}
	
}

public void deleteRequest(int requestId, int playerId) throws PersistanceException{
	
	Connection con = null;
	PreparedStatement ps = null;
	ResultSet rs = null;
	try {

		String query = "UPDATE team_members SET is_active=0 "
				+ "WHERE id =? && user_id = ? && is_active=1 && request_status = 2";
		con = ConnectionUtil.getConnection();
		ps = con.prepareStatement(query);
		ps.setInt(1, requestId);
		ps.setInt(2, playerId);
		int rowsAffected = ps.executeUpdate();
		if (rowsAffected > 0) {
			System.out.println("team member deleted");
		}else {
			throw new PersistanceException("Sql issue: team member not delete");
		}
		
	}catch(SQLException e) {
		e.printStackTrace();
		System.out.println(e.getMessage());
		throw new PersistanceException(e.getMessage());
	}finally {
		ConnectionUtil.close(con,ps,rs);
	}
	
}

public void acceptRequest(int requestId) throws PersistanceException{
	
	Connection con = null;
	PreparedStatement ps = null;
	ResultSet rs = null;
	try {

		String query = "UPDATE team_members SET request_status = 1 , start_date = ? "
				+ "WHERE id =? && is_active=1 && request_status = 2";
		con = ConnectionUtil.getConnection();
		ps = con.prepareStatement(query);
		 Timestamp currentTimestamp = new Timestamp(new Date().getTime());
		 
		ps.setTimestamp(1, currentTimestamp);
		ps.setInt(2, requestId);
		int rowsAffected = ps.executeUpdate();
		if (rowsAffected > 0) {
			System.out.println("team member acceptRequest");
		}else {
			throw new PersistanceException("Sql issue: team member not delete");
		}
		
	}catch(SQLException e) {
		e.printStackTrace();
		System.out.println(e.getMessage());
		throw new PersistanceException(e.getMessage());
	}finally {
		ConnectionUtil.close(con,ps,rs);
	}
	
}

public void rejectRequest(int requestId) throws PersistanceException{
	
	Connection con = null;
	PreparedStatement ps = null;
	ResultSet rs = null;
	try {

		String query = "UPDATE team_members SET request_status = 0 "
				+ "WHERE id =? && is_active=1 && request_status = 2";
		con = ConnectionUtil.getConnection();
		ps = con.prepareStatement(query);
		ps.setInt(1, requestId);
		int rowsAffected = ps.executeUpdate();
		if (rowsAffected > 0) {
			System.out.println("team member rejectRequest");
		}else {
			throw new PersistanceException("Sql issue: team member not rejectRequest");
		}
		
	}catch(SQLException e) {
		e.printStackTrace();
		System.out.println(e.getMessage());
		throw new PersistanceException(e.getMessage());
	}finally {
		ConnectionUtil.close(con,ps,rs);
	}
	
}


public void exitTeam(int teamId, int playerId) throws PersistanceException{
	
	Connection con = null;
	PreparedStatement ps = null;
	ResultSet rs = null;
	try {

		String query = "UPDATE team_members SET is_active = 0 "
				+ "WHERE team_id =? && user_id = ? && is_active=1 && request_status = 1";
		con = ConnectionUtil.getConnection();
		ps = con.prepareStatement(query);
		ps.setInt(1, teamId);
		ps.setInt(1, playerId);
		int rowsAffected = ps.executeUpdate();
		if (rowsAffected > 0) {
			System.out.println("team member rejectRequest");
		}else {
			throw new PersistanceException("Sql issue: team member not rejectRequest");
		}
		
	}catch(SQLException e) {
		e.printStackTrace();
		System.out.println(e.getMessage());
		throw new PersistanceException(e.getMessage());
	}finally {
		ConnectionUtil.close(con,ps,rs);
	}
	
}

public Set<PlayerRequestDTO> listAllTeamMemberRequest(int teamId) throws PersistanceException{
	Set<PlayerRequestDTO> listOfplayers = new HashSet<>();
	
	Connection con = null;
	PreparedStatement ps = null;
	ResultSet rs = null;
	try {

		String query = "SELECT p.*, a.*, tm.id AS request_id "
				+ "FROM players AS p "
				+ "JOIN address AS a ON p.address_id = a.id "
				+ "JOIN team_members AS tm ON tm.user_id = p.id "
				+ "WHERE tm.request_status = 2 AND tm.is_active = 1 AND tm.team_id = ? ";
		con = ConnectionUtil.getConnection();
		ps = con.prepareStatement(query);
		ps.setInt(1, teamId);
		rs = ps.executeQuery();
		
		while (rs.next()) {
			PlayerRequestDTO player = new PlayerRequestDTO();
			
	    	player.setId(rs.getInt("id"));
			player.setPhoneNumber(rs.getLong("phone_number"));
			player.setUserName(rs.getString("user_name"));
			player.setFirstName(rs.getString("first_name"));
			player.setLastName(rs.getString("last_name"));
			player.setUrl(rs.getString("image"));
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
			
			player.setRequestId(rs.getInt("request_id"));
			listOfplayers.add(player);
			System.out.println(player);
	    }
		
	}catch(SQLException e) {
		e.printStackTrace();
		System.out.println(e.getMessage());
		throw new PersistanceException(e.getMessage());
	}finally {
		ConnectionUtil.close(con,ps,rs);
	}
	
	return listOfplayers;
}


public Set<TeamRequestDTO> listAllPlayerRequestByPlayerId(int playerId) throws PersistanceException{
	Set<TeamRequestDTO> listOfTeam = new HashSet<>();
	
	Connection con = null;
	PreparedStatement ps = null;
	ResultSet rs = null;
	try {

		String query = "SELECT t.*,a.*,tm.request_status,tm.id AS request_id FROM teams AS t "
				+ "JOIN address AS a ON t.address_id = a.id "
				+ "JOIN team_members AS tm ON tm.team_id = t.id "
				+ "WHERE tm.is_active = 1 AND tm.is_captain != 1 AND tm.user_id = ? ";
		con = ConnectionUtil.getConnection();
		ps = con.prepareStatement(query);
		ps.setInt(1, playerId);
		rs = ps.executeQuery();
		
		while (rs.next()) {
			TeamRequestDTO team = new TeamRequestDTO();
			
			team.setId(rs.getInt("id"));
		      team.setTeamName(rs.getString("team_name"));
		      team.setUrl(rs.getString("url"));
		      team.setAbout(rs.getString("about"));
		      team.setOpenForPlayerDescription(rs.getString("open_for_players_description"));
		      team.getAddress().setId(rs.getInt("address_id"));
		      team.getAddress().setArea(rs.getString("area"));
		      team.getAddress().setDistrict(rs.getString("district"));
		      team.setCreatedAt(rs.getTimestamp("created_at").toLocalDateTime());
		      team.setRequestStatus(rs.getInt("request_status"));
		      team.setRequestId(rs.getInt("request_id"));
		      
			listOfTeam.add(team);
			System.out.println(team);
	    }
		
	}catch(SQLException e) {
		e.printStackTrace();
		System.out.println(e.getMessage());
		throw new PersistanceException(e.getMessage());
	}finally {
		ConnectionUtil.close(con,ps,rs);
	}
	
	return listOfTeam;
}

public void deleteChange(int teamId, int playerId) throws PersistanceException{
	
	Connection con = null;
	PreparedStatement ps = null;
	ResultSet rs = null;
	try {

		String query = "UPDATE team_members SET is_captain =1, is_active=1 "
				+ "WHERE team_id=? && user_id = ?";
		con = ConnectionUtil.getConnection();
		ps = con.prepareStatement(query);
		ps.setInt(1, teamId);
		ps.setInt(2, playerId);
		int rowsAffected = ps.executeUpdate();
		if (rowsAffected > 0) {
			System.out.println("team member deleted");
		}else {
			throw new PersistanceException("Sql issue: team member not delete");
		}
		
	}catch(SQLException e) {
		e.printStackTrace();
		System.out.println(e.getMessage());
		throw new PersistanceException(e.getMessage());
	}finally {
		ConnectionUtil.close(con,ps,rs);
	}
	
}
}
