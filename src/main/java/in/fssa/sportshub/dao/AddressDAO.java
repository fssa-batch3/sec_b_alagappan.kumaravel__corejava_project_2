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
import in.fssa.sportshub.util.ConnectionUtil;

public class AddressDAO {
	/**
	 * This method create address in database.
	 * @param newAddress
	 * @return address id
	 * @throws PersistanceException
	 */
public int create(Address newAddress) throws PersistanceException{ 
		int id = 0;
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			String query = "Insert into address (area, district) Values (?,?)";
			
			con = ConnectionUtil.getConnection();
			
			ps = con.prepareStatement(query , Statement.RETURN_GENERATED_KEYS);
			ps.setString(1, newAddress.getArea());
			ps.setString(2, newAddress.getDistrict());
			
			int rowsAffected = ps.executeUpdate();
			
			if (rowsAffected > 0) {
			    rs = ps.getGeneratedKeys();
			    
			    if (rs.next()) {
			        int generatedId = rs.getInt(1);
			      id = generatedId;
			      System.out.println("Address has been successfullly created.");
			    }
			}else {
				throw new PersistanceException("Address not created in data base.");
			}
			
		}catch(SQLException e) {
			e.printStackTrace();
			throw new PersistanceException(e.getMessage());
		}finally {
			ConnectionUtil.close(con,ps,rs);
		}
		System.out.println(id);
		return id;
	}

/**
 * This method return address id if exist otherwise it return -1.
 * @param area
 * @param district
 * @return address id
 * @throws PersistanceException
 */
public int findByDistrictAndArea(String area, String district)throws PersistanceException {
	int id = 0;
	Connection con = null;
	PreparedStatement ps = null;
	ResultSet rs = null;
	try {
		String query = "SELECT * FROM address WHERE area=? && district=?";
		
		con = ConnectionUtil.getConnection();
		
		ps = con.prepareStatement(query);
		ps.setString(1, area);
		ps.setString(2, district);
		System.out.println(district);
		rs = ps.executeQuery();

		if (rs.next()) {
		      id = rs.getInt("id");
		      System.out.println("Address id found");
		}else {
			id = -1;
			System.out.println("Address id not found");
		}
		
	}catch(SQLException e) {
		e.printStackTrace();
		throw new PersistanceException(e.getMessage());
	}finally {
		ConnectionUtil.close(con,ps,rs);
	}
	return id;
}

/**
 * This method return address id if exist otherwise throw exception.
 * @param id
 * @return Address object
 * @throws PersistanceException
 */
public Address findById(int id)throws PersistanceException {
	Address address;
	Connection con = null;
	PreparedStatement ps = null;
	ResultSet rs = null;
	try {
		String query = "SELECT * FROM address WHERE id=?";
		
		con = ConnectionUtil.getConnection();
		
		ps = con.prepareStatement(query);
		ps.setInt(1, id);
		
		rs = ps.executeQuery();

		if (rs.next()) {
			  address = new Address();
		      address.setId(rs.getInt("id"));
		      address.setArea(rs.getString("area"));
		      address.setDistrict(rs.getString("district"));
		      System.out.println("Address found");
		}else {
			throw new PersistanceException("Address not found in data base");
		}
		
	}catch(SQLException e) {
		e.printStackTrace();
		System.out.println(e.getMessage());
		throw new RuntimeException(e);
	}finally {
		ConnectionUtil.close(con,ps,rs);
	}
	return address;
}

/**
 * This method return true if address exist othwerwise return false.
 * @param id
 * @return boolean data
 * @throws PersistanceException
 */
public boolean checkIfExist(int id) throws PersistanceException{
	boolean value;
	Connection con = null;
	PreparedStatement ps = null;
	ResultSet rs = null;
	try {
		String query = "SELECT id FROM address WHERE id=?";
		
		con = ConnectionUtil.getConnection();
		
		ps = con.prepareStatement(query);
		ps.setInt(1, id);
		
		rs = ps.executeQuery();

		if (rs.next()) {
			  value = true;
		      System.out.println("Address found");
		}else {
			value = false;
			System.out.println("Address not found");
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
 * It returns list of address object.
 * @return
 * @throws PersistanceException
 */
public Set<Address> getAll() throws PersistanceException{
	Set<Address> addressList = new HashSet<>();;
	Connection con = null;
	PreparedStatement ps = null;
	ResultSet rs = null;
	try {
		String query = "SELECT * FROM address";
		
		con = ConnectionUtil.getConnection();
		
		ps = con.prepareStatement(query);
		rs = ps.executeQuery();

		while (rs.next()) {
			  Address address = new Address();
		      address.setId(rs.getInt("id"));
		      address.setArea(rs.getString("area"));
		      address.setDistrict(rs.getString("district"));
		      addressList.add(address);
		}
		
	}catch(SQLException e) {
		e.printStackTrace();
		throw new PersistanceException(e.getMessage());
	}finally {
		ConnectionUtil.close(con,ps,rs);
	}
	return addressList;
}

}
