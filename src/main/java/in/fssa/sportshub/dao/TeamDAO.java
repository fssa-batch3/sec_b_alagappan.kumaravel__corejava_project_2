package in.fssa.sportshub.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.HashSet;
import java.util.Set;

import in.fssa.sportshub.exception.PersistanceException;
import in.fssa.sportshub.model.Address;
import in.fssa.sportshub.model.Player;
import in.fssa.sportshub.model.Team;
import in.fssa.sportshub.model.TeamDetailDTO;
import in.fssa.sportshub.util.ConnectionUtil;

public class TeamDAO {
	public boolean nameAlreadyExist(String teamName) throws PersistanceException{
		boolean value;
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			String query = "SELECT id FROM teams WHERE is_active=1 && team_name=?";
			
			con = ConnectionUtil.getConnection();
			ps = con.prepareStatement(query);
			ps.setString(1, teamName);
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
	
	public boolean checkExistById(int id) throws PersistanceException{
		boolean value;
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			String query = "SELECT id FROM teams WHERE is_active=1 && id=?";
			
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
	
	public int create(Team team) throws PersistanceException{
		int teamId = 0;
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			String query = "Insert into teams (team_name, url, address_id ,about ,open_for_players_description ,created_by,modified_by)"
					+ " Values (?,?,?,?,?,?,?)";
			
			con = ConnectionUtil.getConnection();
			ps = con.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
			ps.setString(1, team.getTeamName());
			ps.setString(2, team.getUrl());
			ps.setInt(3, team.getAddress().getId());
			ps.setString(4, team.getAbout());
			ps.setString(5, team.getOpenForPlayerDescription());
			ps.setInt(6, team.getCreatedBy());
			ps.setInt(7, team.getCreatedBy());
			int rowsAffected = ps.executeUpdate();
			
			if (rowsAffected > 0) {
			    rs = ps.getGeneratedKeys();
			    
			    if (rs.next()) {
			        int generatedId = rs.getInt(1);
			        teamId = generatedId;
			      System.out.println("Team has been successfullly created.");
			    }
			}
			
		}catch(SQLException e) {
			e.printStackTrace();
			System.out.println(e.getMessage());
			throw new PersistanceException(e.getMessage());
		}finally {
			ConnectionUtil.close(con,ps,rs);
		}
		return teamId;
	}
	
	public void update(Team team) throws PersistanceException {
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			String query = "UPDATE teams SET url =? ,address_id =?,about =? ,modified_by =? "
					+ "WHERE id = ?";
			con = ConnectionUtil.getConnection();
			ps = con.prepareStatement(query);
			ps.setString(1, team.getUrl());
			ps.setInt(2, team.getAddress().getId());
			ps.setString(3, team.getAbout());
			ps.setInt(4, team.getModifiedBy());
			ps.setInt(5, team.getId());
			int rowsAffected = ps.executeUpdate();
			
			if (rowsAffected > 0) {
			      System.out.println("Team successfully updated");
			}else {
				throw new PersistanceException("Sql issue : Team not updated");
			}
			
		}catch(SQLException e) {
			e.printStackTrace();
			System.out.println(e.getMessage());
			throw new PersistanceException(e.getMessage());
		}finally {
			ConnectionUtil.close(con,ps,rs);
		}
	}
	
	public void delete(int playerId, int teamId) throws PersistanceException {
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;

		try {
			String query = "UPDATE teams SET modified_by =? ,is_active=0 "
					+ "WHERE id = ?";
			con = ConnectionUtil.getConnection();
			ps = con.prepareStatement(query);
			ps.setInt(1, playerId);
			ps.setInt(2, teamId);
			int rowsAffected = ps.executeUpdate();
			
			if (rowsAffected > 0) {
			      System.out.println("Team successfully deleted");
			}else {
				throw new PersistanceException("Sql issue : Team not delete");
			}
			
		}catch(SQLException e) {
			e.printStackTrace();
			System.out.println(e.getMessage());
			throw new PersistanceException(e.getMessage());
		}finally {
			ConnectionUtil.close(con,ps,rs);
		}
	}
	
	
	public TeamDetailDTO findById(int teamId) throws PersistanceException {
		TeamDetailDTO team = new TeamDetailDTO();
		Set<Player> teamPlayer = new HashSet<Player>();
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		try {
			String query = "SELECT t.*, p.id AS player_id, p.user_name, p.image AS player_image, p.phone_number, p.date_of_birth, tm.is_captain, a.area , a.district "
					+ " FROM teams AS t "
					+ "JOIN team_members AS tm ON tm.team_id = t.id "
					+ "JOIN players AS p ON tm.user_id = p.id "
					+ "JOIN address AS a ON t.address_id = a.id "
					+ " WHERE t.is_active=1 AND t.id= ? "
					+ " AND tm.is_active = 1 AND tm.request_status = 1 ";
			
			con = ConnectionUtil.getConnection();
			ps = con.prepareStatement(query);
			ps.setInt(1, teamId);
			rs = ps.executeQuery();
			
			int i =0;
			while (rs.next()) {
				if(i == 0) {
					i++;
			      team.setId(rs.getInt("id"));
			      team.setTeamName(rs.getString("team_name"));
			      team.setUrl(rs.getString("url"));
			      team.setAbout(rs.getString("about"));
			      team.setOpenForPlayerStatus(rs.getBoolean("open_for_players_status"));
			      team.setOpenForPlayerDescription(rs.getString("open_for_players_description"));
			      team.setCreatedAt(rs.getTimestamp("created_at").toLocalDateTime());
			      
			      Address address = new Address();
			      address.setId(rs.getInt("address_id"));
			      address.setArea(rs.getString("area"));
			      address.setDistrict(rs.getString("district"));
			      team.setAddress(address);
				}
				
				Player player = new Player();
				int id = rs.getInt("id");
				player.setId(id);
				if(rs.getInt("is_captain") == 1) {
					team.setTeamCaptainId(id);
				}
				player.setUserName(rs.getString("user_name"));
				player.setPhoneNumber(rs.getLong("phone_number"));
				player.setUrl(rs.getString("player_image"));
				String dateString = rs.getDate("date_of_birth") + "";
				DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
				LocalDate localDate = LocalDate.parse(dateString, formatter);
				
				player.setDateOfBirth(localDate);
				teamPlayer.add(player);
			}
			team.setTeamMembers(teamPlayer);
			if(i == 0){
				throw new PersistanceException("Sql issue : Team not find");
		    }
			
			
		}catch(SQLException e) {
			e.printStackTrace();
			System.out.println(e.getMessage());
			throw new PersistanceException(e.getMessage());
		}finally {
			ConnectionUtil.close(con,ps,rs);
		}
		return team;
	}
	
	
	public void deleteChange(int playerId, int teamId) throws PersistanceException {
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;

		try {
			String query = "UPDATE teams SET modified_by =? ,is_active=1 "
					+ "WHERE id = ?";
			con = ConnectionUtil.getConnection();
			ps = con.prepareStatement(query);
			ps.setInt(1, playerId);
			ps.setInt(2, teamId);
			int rowsAffected = ps.executeUpdate();
			
			if (rowsAffected > 0) {
			      System.out.println("Team successfully deleted");
			}else {
				throw new PersistanceException("Sql issue : Team not delete");
			}
			
		}catch(SQLException e) {
			e.printStackTrace();
			System.out.println(e.getMessage());
			throw new PersistanceException(e.getMessage());
		}finally {
			ConnectionUtil.close(con,ps,rs);
		}
	}
	
	public Set<TeamDetailDTO> getAll() throws PersistanceException{
		Set<TeamDetailDTO> teamList = new HashSet<>();;
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			String query = "SELECT t.*, p.id, p.user_name, tm.id FROM teams AS t "
					+ "JOIN team_members AS tm ON tm.team_id = t.id "
					+ "JOIN players AS p ON tm.user_id = p.id ";
			
			con = ConnectionUtil.getConnection();
			
			ps = con.prepareStatement(query);
			rs = ps.executeQuery();

			while (rs.next()) {
				TeamDetailDTO team = new TeamDetailDTO();
				  team.setId(rs.getInt("t.id"));
			      team.setTeamName(rs.getString("team_name"));
			      team.setUrl(rs.getString("url"));
			      team.setAbout(rs.getString("about"));
			      team.setOpenForPlayerDescription(rs.getString("open_for_players_description"));
			      team.getAddress().setId(rs.getInt("address_id"));
			      team.setCreatedAt(rs.getTimestamp("created_at").toLocalDateTime());
			      team.setCaptainName(rs.getString("user_name"));
			      team.setTeamCaptainRelId(rs.getInt("tm.id"));
			      teamList.add(team);
			      System.out.println(team);
			}
			
		}catch(SQLException e) {
			e.printStackTrace();
			throw new PersistanceException(e.getMessage());
		}finally {
			ConnectionUtil.close(con,ps,rs);
		}
		return teamList;
	}
	
	public Set<TeamDetailDTO> getOpenForPlayerTeamList() throws PersistanceException{
		Set<TeamDetailDTO> teamList = new HashSet<>();;
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			String query = "SELECT t.*, p.id AS player_id, p.user_name FROM teams AS t "
					+ "JOIN team_members AS tm ON tm.team_id = t.id "
					+ "JOIN players AS p ON tm.user_id = p.id "
					+ "WHERE t.open_for_players_status = 1";
			
			con = ConnectionUtil.getConnection();
			
			ps = con.prepareStatement(query);
			rs = ps.executeQuery();

			while (rs.next()) {
				TeamDetailDTO team = new TeamDetailDTO();
				  team.setId(rs.getInt("id"));
			      team.setTeamName(rs.getString("team_name"));
			      team.setUrl(rs.getString("url"));
			      team.setAbout(rs.getString("about"));
			      team.setOpenForPlayerDescription(rs.getString("open_for_players_description"));
			      team.getAddress().setId(rs.getInt("address_id"));
			      team.setCreatedAt(rs.getTimestamp("created_at").toLocalDateTime());
			      team.setCaptainName(rs.getString("user_name"));
			      team.setTeamCaptainId(rs.getInt("player_id"));
			      teamList.add(team);
			      System.out.println(team);
			}
			
		}catch(SQLException e) {
			e.printStackTrace();
			throw new PersistanceException(e.getMessage());
		}finally {
			ConnectionUtil.close(con,ps,rs);
		}
		return teamList;
	}
}
