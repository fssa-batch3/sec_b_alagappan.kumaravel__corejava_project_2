package in.fssa.sportshub.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import in.fssa.sportshub.exception.ValidationException;
import in.fssa.sportshub.model.Team;
import in.fssa.sportshub.util.ConnectionUtil;

public class TeamDAO {
	public boolean teamNameAlreadyExist(String teamName){
		boolean value;
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			String query = "SELECT * FROM teams WHERE is_active=1 && team_name=?";
			
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
			throw new RuntimeException(e);
		}finally {
			ConnectionUtil.close(con,ps,rs);
		}
		
		return value;
	}
	
	public boolean teamAlreadyExist(int id){
		boolean value;
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			String query = "SELECT * FROM teams WHERE is_active=1 && id=?";
			
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
	
	public int create(Team team) {
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
			throw new RuntimeException(e);
		}finally {
			ConnectionUtil.close(con,ps,rs);
		}
		return teamId;
	}
	
	public void update(Team team) throws ValidationException {
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
				throw new ValidationException("Sql issue : Team not updated");
			}
			
		}catch(SQLException e) {
			e.printStackTrace();
			System.out.println(e.getMessage());
			throw new RuntimeException(e);
		}finally {
			ConnectionUtil.close(con,ps,rs);
		}
	}
	
	public void delete(int playerId, int teamId) throws ValidationException {
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
				throw new ValidationException("Sql issue : Team not delete");
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
