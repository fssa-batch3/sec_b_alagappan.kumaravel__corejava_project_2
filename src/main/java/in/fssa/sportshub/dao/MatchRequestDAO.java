package in.fssa.sportshub.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.HashSet;
import java.util.Set;

import in.fssa.sportshub.exception.PersistanceException;
import in.fssa.sportshub.model.Gender;
import in.fssa.sportshub.model.MatchRequest;
import in.fssa.sportshub.model.OpponentType;
import in.fssa.sportshub.model.Player;
import in.fssa.sportshub.util.ConnectionUtil;

public class MatchRequestDAO {
	public void createToTeam(MatchRequest matchRequest) throws PersistanceException{

		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			String query = "Insert into match_requests (created_by, to_team, type_of_match, members, members_age_from, members_age_to, match_time, location, information, type_of_opponent) Values (?,?,?,?,?,?,?,?,?,?)";
			
			con = ConnectionUtil.getConnection();
			ps = con.prepareStatement(query);
			ps.setInt(1, matchRequest.getCreatedBy());
			ps.setInt(2, matchRequest.getToTeam());
			ps.setInt(3, matchRequest.getTypeOfMatch());
			ps.setInt(4, matchRequest.getMembers());
			ps.setInt(5, matchRequest.getMembersAgeFrom());
			ps.setInt(6, matchRequest.getMembersAgeTo());
			Timestamp timestamp = Timestamp.valueOf(matchRequest.getMatchTime());
			ps.setTimestamp(7, timestamp);
			ps.setString(8, matchRequest.getLocation());
			ps.setString(9, matchRequest.getInformation());
			ps.setString(10, matchRequest.getOpponentType().getDisplayName());
			int rowsAffected = ps.executeUpdate();
			if (rowsAffected > 0) {
				System.out.println("Match request created");
			}else {
				throw new PersistanceException("Sql issue: Match request not created");
			}
			
		}catch(SQLException e) {
			e.printStackTrace();
			System.out.println(e.getMessage());
			throw new RuntimeException(e);
		}finally {
			ConnectionUtil.close(con,ps,rs);
		}
		
	}
	
	public void createToArea(MatchRequest matchRequest) throws PersistanceException{

		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			String query = "Insert into match_requests (created_by, address_id, type_of_match, members, members_age_from, members_age_to, match_time, location, information) Values (?,?,?,?,?,?,?,?,?)";
			
			con = ConnectionUtil.getConnection();
			ps = con.prepareStatement(query);
			ps.setInt(1, matchRequest.getCreatedBy());
			ps.setInt(2, matchRequest.getAddressId());
			ps.setInt(3, matchRequest.getTypeOfMatch());
			ps.setInt(4, matchRequest.getMembers());
			ps.setInt(5, matchRequest.getMembersAgeFrom());
			ps.setInt(6, matchRequest.getMembersAgeTo());
			Timestamp timestamp = Timestamp.valueOf(matchRequest.getMatchTime());
			ps.setTimestamp(7, timestamp);
			ps.setString(8, matchRequest.getLocation());
			ps.setString(9, matchRequest.getInformation());
			
			int rowsAffected = ps.executeUpdate();
			if (rowsAffected > 0) {
				System.out.println("Match request created");
			}else {
				throw new PersistanceException("Sql issue: Match request not created");
			}
			
		}catch(SQLException e) {
			e.printStackTrace();
			System.out.println(e.getMessage());
			throw new RuntimeException(e);
		}finally {
			ConnectionUtil.close(con,ps,rs);
		}
		
	}
	
	public Set<MatchRequest> getAllOpenRequest() throws PersistanceException{
		
		Set<MatchRequest> listOfRequest = new HashSet<>();
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			String query = "SELECT * FROM match_requests WHERE status=1";
			
			con = ConnectionUtil.getConnection();
			ps = con.prepareStatement(query);
			rs = ps.executeQuery();
			while(rs.next()) {
				MatchRequest matchRequest = new MatchRequest();
				matchRequest.setId(rs.getInt("id"));
				matchRequest.setCreatedBy(rs.getInt("created_by"));
				matchRequest.setOpponentType((rs.getInt("type_of_opponent") == 1? OpponentType.TO_TEAM : OpponentType.TO_AREA ));
				matchRequest.setToTeam(rs.getInt("to_team"));
				matchRequest.setAddressId(rs.getInt("address_id"));
				matchRequest.setTypeOfMatch(rs.getInt("type_of_match"));
				matchRequest.setMembers(rs.getInt("members"));
				matchRequest.setMembersAgeFrom(rs.getInt("members_age_from"));
				matchRequest.setMembersAgeTo(rs.getInt("members_age_to"));
				matchRequest.setMatchTime(rs.getTimestamp("match_time").toLocalDateTime());
				matchRequest.setLocation(rs.getString("location"));
				matchRequest.setInformation(rs.getString("information"));
				matchRequest.setCreatedAt(rs.getTimestamp("created_at").toLocalDateTime());
				listOfRequest.add(matchRequest);
				System.out.println(matchRequest.toString());
			}
			
		}catch(SQLException e) {
			e.printStackTrace();
			System.out.println(e.getMessage());
			throw new PersistanceException(e.getMessage());
		}finally {
			ConnectionUtil.close(con,ps,rs);
		}
		return listOfRequest;
	}
	
public Set<MatchRequest> getAllMyMatchRequest(int CreatedId, int toTeamId, int addressId) throws PersistanceException{
		
		Set<MatchRequest> listOfRequest = new HashSet<>();
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			String query = "SELECT mr.* FROM match_requests mr "
		             + "LEFT JOIN request_responses rr ON mr.id = rr.request_id AND rr.from_team_id = ? "
		             + "WHERE (mr.status = 1 AND mr.created_by != ?) "
		             + "AND (mr.to_team = ? OR mr.address_id = ?) "
		             + "AND rr.request_id IS NULL";
			
			con = ConnectionUtil.getConnection();
			ps = con.prepareStatement(query);
			ps.setInt(1, toTeamId);
			ps.setInt(2, CreatedId);
			ps.setInt(3, toTeamId);
			ps.setInt(4, addressId);
			rs = ps.executeQuery();
			while(rs.next()) {
				MatchRequest matchRequest = new MatchRequest();
				matchRequest.setId(rs.getInt("id"));
				matchRequest.setCreatedBy(rs.getInt("created_by"));
				matchRequest.setOpponentType((rs.getInt("type_of_opponent") == 1? OpponentType.TO_TEAM : OpponentType.TO_AREA ));
				matchRequest.setToTeam(rs.getInt("to_team"));
				matchRequest.setAddressId(rs.getInt("address_id"));
				matchRequest.setTypeOfMatch(rs.getInt("type_of_match"));
				matchRequest.setMembers(rs.getInt("members"));
				matchRequest.setMembersAgeFrom(rs.getInt("members_age_from"));
				matchRequest.setMembersAgeTo(rs.getInt("members_age_to"));
				matchRequest.setMatchTime(rs.getTimestamp("match_time").toLocalDateTime());
				matchRequest.setLocation(rs.getString("location"));
				matchRequest.setInformation(rs.getString("information"));
				matchRequest.setCreatedAt(rs.getTimestamp("created_at").toLocalDateTime());
				listOfRequest.add(matchRequest);
				System.out.println(matchRequest.toString());
			}
			
		}catch(SQLException e) {
			e.printStackTrace();
			System.out.println(e.getMessage());
			throw new PersistanceException(e.getMessage());
		}finally {
			ConnectionUtil.close(con,ps,rs);
		}
		return listOfRequest;
	}

public Set<MatchRequest> listOfMyMatchInvitation(int CreatedId) throws PersistanceException{
	
	Set<MatchRequest> listOfRequest = new HashSet<>();
	Connection con = null;
	PreparedStatement ps = null;
	ResultSet rs = null;
	try {
		String query = "SELECT * FROM match_requests WHERE status=1 && created_by = ?";
		
		con = ConnectionUtil.getConnection();
		ps = con.prepareStatement(query);
		ps.setInt(1, CreatedId);
		rs = ps.executeQuery();
		while(rs.next()) {
			MatchRequest matchRequest = new MatchRequest();
			matchRequest.setId(rs.getInt("id"));
			matchRequest.setCreatedBy(rs.getInt("created_by"));
			matchRequest.setOpponentType((rs.getInt("type_of_opponent") == 1? OpponentType.TO_TEAM : OpponentType.TO_AREA ));
			matchRequest.setToTeam(rs.getInt("to_team"));
			matchRequest.setAddressId(rs.getInt("address_id"));
			matchRequest.setTypeOfMatch(rs.getInt("type_of_match"));
			matchRequest.setMembers(rs.getInt("members"));
			matchRequest.setMembersAgeFrom(rs.getInt("members_age_from"));
			matchRequest.setMembersAgeTo(rs.getInt("members_age_to"));
			matchRequest.setMatchTime(rs.getTimestamp("match_time").toLocalDateTime());
			matchRequest.setLocation(rs.getString("location"));
			matchRequest.setInformation(rs.getString("information"));
			matchRequest.setCreatedAt(rs.getTimestamp("created_at").toLocalDateTime());
			listOfRequest.add(matchRequest);
			System.out.println(matchRequest.toString());
		}
		
	}catch(SQLException e) {
		e.printStackTrace();
		System.out.println(e.getMessage());
		throw new PersistanceException(e.getMessage());
	}finally {
		ConnectionUtil.close(con,ps,rs);
	}
	return listOfRequest;
}

public void updateAccept(int toTeamCaptainRelationId, int matchRequestId) throws PersistanceException{

	Connection con = null;
	PreparedStatement ps = null;
	ResultSet rs = null;
	try {
		String query = "UPDATE match_requests SET status=0, to_team = ? "
				+ "WHERE id = ?";
		
		con = ConnectionUtil.getConnection();
		ps = con.prepareStatement(query);
		ps.setInt(1, toTeamCaptainRelationId);
		ps.setInt(2, matchRequestId);		
		int rowsAffected = ps.executeUpdate();
		if (rowsAffected > 0) {
			System.out.println("Update match request");
		}else {
			throw new RuntimeException("Sql issue: match request not updated");
		}
		
	}catch(SQLException e) {
		e.printStackTrace();
		
		throw new PersistanceException(e.getMessage());
	}finally {
		ConnectionUtil.close(con,ps,rs);
	}
	
}


public boolean checkIfExistById(int id) throws PersistanceException{
	
	boolean value;
	Connection con = null;
	PreparedStatement ps = null;
	ResultSet rs = null;
	try {
		String query = "SELECT id FROM match_requests WHERE status=1 && id = ?";
		
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
}
