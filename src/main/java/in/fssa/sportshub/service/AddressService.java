package in.fssa.sportshub.service;

import java.util.Set;

import in.fssa.sportshub.dao.AddressDAO;
import in.fssa.sportshub.model.Address;
import in.fssa.sportshub.validator.AddressValidator;

public class AddressService {
	 public int create(Address address) throws Exception{
		 	AddressValidator.validate(address);
		 	int addressIdIf = this.findByAreaAndDistrict(address.getArea(), address.getDistrict());
		 	System.out.println(addressIdIf);
		 	if(addressIdIf > 0) {
		 		return addressIdIf;
		 	}else {
		 		AddressDAO addressDao = new AddressDAO();
		 		int newAddressId = addressDao.create(address);
		 		return newAddressId;
		 	}
			
	 }
	 
	 public int findByAreaAndDistrict(String area, String district) throws Exception{
		 	Address add = new Address();
		 	add.setArea(area);
		 	add.setDistrict(district);
		 	AddressValidator.validate(add);
	 	
			AddressDAO addressDao = new AddressDAO();
			return addressDao.findByAreaAndDistrict(area, district);
	 }
	 
	 public Address findById(int id) throws Exception{
		 	AddressValidator.validateId(id);
	 	
			AddressDAO addressDao = new AddressDAO();
			return addressDao.findById(id);
	 }
	 public boolean checkAddressExist(int id) throws Exception{
		 	AddressValidator.validateId(id);
	 	
			AddressDAO addressDao = new AddressDAO();
			return addressDao.checkAddressExist(id);
	 }
	 public Set<Address> getAllAddress() throws Exception{

			AddressDAO addressDao = new AddressDAO();
			return addressDao.getAllAddress();
	 }
}
