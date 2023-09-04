package in.fssa.sportshub.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import in.fssa.sportshub.exception.PersistanceException;
import in.fssa.sportshub.util.ConnectionUtil;

public class RequestResponseDAO {

	public void accept(int teamid, int matchRequestId) throws PersistanceException{

		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			String query = "Insert into request_responses (from_team_id, request_id, status_of_response)"
					+ " Values (?,?,1)";
			
			con = ConnectionUtil.getConnection();
			ps = con.prepareStatement(query);
			ps.setInt(1, teamid);
			ps.setInt(2, matchRequestId);
			int rowsAffected = ps.executeUpdate();
			if (rowsAffected > 0) {
				System.out.println("request response created");
			}else {
				throw new RuntimeException("Sql issue: request response not created");
			}
			
		}catch(SQLException e) {
			e.printStackTrace();
			
			throw new PersistanceException(e.getMessage());
		}finally {
			ConnectionUtil.close(con,ps,rs);
		}
		
	}
	
	
	public void reject(int teamid, int matchRequestId) throws PersistanceException{

		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			String query = "Insert into request_responses (from_team_id, request_id, status_of_response)"
					+ " Values (?,?,0)";
			
			con = ConnectionUtil.getConnection();
			ps = con.prepareStatement(query);
			ps.setInt(1, teamid);
			ps.setInt(2, matchRequestId);
			int rowsAffected = ps.executeUpdate();
			if (rowsAffected > 0) {
				System.out.println("request response created");
			}else {
				throw new RuntimeException("Sql issue: request response not created");
			}
			
		}catch(SQLException e) {
			e.printStackTrace();
			
			throw new PersistanceException(e.getMessage());
		}finally {
			ConnectionUtil.close(con,ps,rs);
		}
		
	}
	
}
