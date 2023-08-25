package in.fssa.sportshub.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ConnectionUtil {
	// this method use when ever we connect with database. 
	public static Connection getConnection() {
		 String url;
	        String userName;
	        String passWord;

	            url = System.getenv("DATABASE_HOSTNAME");
	            userName = System.getenv("DATABASE_USERNAME");
	            passWord = System.getenv("DATABASE_PASSWORD");
	        	
//	            cloud 
//	        	url = "jdbc:mysql://164.52.216.41:3306/alagappan_kumaravel_corejava_project";
//	        	userName = "nZfrzgnz4jmj";
//	        	passWord = "139fda3c-13fc-456b-b120-20017daa728e";
	            
	            
		Connection connection = null;
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
				connection = DriverManager.getConnection(url,userName,passWord);
		}catch(Exception e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
		
		return connection;
	}
	public static void close(Connection connection, PreparedStatement ps) {
		try {
			if(ps != null) {
				ps.close();
			}
			if(connection != null) {
				connection.close();
				}
		}catch(SQLException e) {
			e.printStackTrace();
		}
	}
	public static void close(Connection connection, PreparedStatement ps, ResultSet rs) {
		try {
			// the order of close is important.
			if(rs != null) {
				rs.close();
			}
			if(ps != null) {
				ps.close();
			}
			if(connection != null) {
				connection.close();
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}
	}
}
