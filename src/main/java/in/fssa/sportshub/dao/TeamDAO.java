package in.fssa.sportshub.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashSet;
import java.util.Set;

import in.fssa.sportshub.exception.PersistanceException;
import in.fssa.sportshub.model.Address;
import in.fssa.sportshub.model.Team;
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
			String query = "Insert into teams (team_name, url, address_id ,about ,created_by,modified_by)"
					+ " Values (?,?,?,?,?,?)";
			
			con = ConnectionUtil.getConnection();
			ps = con.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
			ps.setString(1, team.getTeamName());
			ps.setString(2, team.getUrl());
			ps.setInt(3, team.getAddress().getId());
			ps.setString(4, team.getAbout());
			ps.setInt(5, team.getCreatedBy());
			ps.setInt(6, team.getCreatedBy());
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
	
	
	public Team findById(int teamId) throws PersistanceException {
		Team team = new Team();
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;

		try {
			String query = "SELECT * FROM teams WHERE is_active=1 && id=? ";

			con = ConnectionUtil.getConnection();
			ps = con.prepareStatement(query);
			ps.setInt(1, teamId);
			rs = ps.executeQuery();
			
			if (rs.next()) {
			      team.setId(rs.getInt("id"));
			      team.setTeamName(rs.getString("team_name"));
			      team.setUrl(rs.getString("url"));
			      team.setAbout(rs.getString("about"));
			      team.getAddress().setId(rs.getInt("address_id"));
			      team.setCreatedAt(rs.getTimestamp("created_at").toLocalDateTime().toLocalDate());
			      
			}else {
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
	
	public Set<Team> getAll() throws PersistanceException{
		Set<Team> teamList = new HashSet<>();;
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			String query = "SELECT * FROM teams";
			
			con = ConnectionUtil.getConnection();
			
			ps = con.prepareStatement(query);
			rs = ps.executeQuery();

			while (rs.next()) {
				  Team team = new Team();
				  team.setId(rs.getInt("id"));
			      team.setTeamName(rs.getString("team_name"));
			      team.setUrl(rs.getString("url"));
			      team.setAbout(rs.getString("about"));
			      team.getAddress().setId(rs.getInt("address_id"));
			      team.setCreatedAt(rs.getTimestamp("created_at").toLocalDateTime().toLocalDate());
			      teamList.add(team);
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
