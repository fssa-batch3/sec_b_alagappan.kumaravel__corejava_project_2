package in.fssa.sportshub.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import in.fssa.sportshub.model.TeamMember;
import in.fssa.sportshub.util.ConnectionUtil;

public class TeamMemberDAO {
	public void create(TeamMember teamMember) {

		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			String query = "Insert into team_member (team_id, user_id) Values (?,?)";
			
			con = ConnectionUtil.getConnection();
			ps = con.prepareStatement(query);
			ps.setInt(1, teamMember.getTeamId());
			ps.setInt(2, teamMember.getUserId());
			int rowsAffected = ps.executeUpdate();
			if (rowsAffected > 0) {
				System.out.println("team member created");
			}else {
				throw new RuntimeException("Sql issue: team member not created");
			}
			
		}catch(SQLException e) {
			e.printStackTrace();
			System.out.println(e.getMessage());
			throw new RuntimeException(e);
		}finally {
			ConnectionUtil.close(con,ps,rs);
		}
		
	}
	
	public boolean isPlayerCaptain(int id) {
		
		boolean value;
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			String query = "SELECT * FROM team_member WHERE is_active=1 && is_captain=1 && user_id = ?";
			
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
	
public boolean isPlayerCaptainOfSpecificTeam(int player_id, int team_id) {
		
		boolean value;
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			String query = "SELECT * FROM team_member WHERE is_active=1 && is_captain=1 && user_id = ? && team_id = ?";
			
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
			throw new RuntimeException(e);
		}finally {
			ConnectionUtil.close(con,ps,rs);
		}
		
		return value;
	}

public void delete(int teamId, int playerId) {
	
	Connection con = null;
	PreparedStatement ps = null;
	ResultSet rs = null;
	try {

		String query = "UPDATE team_member SET is_captain =0, is_active=0 "
				+ "WHERE team_id=? && user_id = ? && is_captain=1";
		con = ConnectionUtil.getConnection();
		ps = con.prepareStatement(query);
		ps.setInt(1, teamId);
		ps.setInt(2, playerId);
		int rowsAffected = ps.executeUpdate();
		if (rowsAffected > 0) {
			System.out.println("team member deleted");
		}else {
			throw new RuntimeException("Sql issue: team member not delete");
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
