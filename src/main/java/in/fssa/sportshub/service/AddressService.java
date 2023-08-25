package in.fssa.sportshub.service;

import java.util.Set;


import in.fssa.sportshub.dao.AddressDAO;
import in.fssa.sportshub.exception.PersistanceException;
import in.fssa.sportshub.exception.ServiceException;
import in.fssa.sportshub.exception.ValidationException;
import in.fssa.sportshub.model.Address;
import in.fssa.sportshub.validator.AddressValidator;

public class AddressService {
	/**
	 * This method initiate the validation and pass the AddressService object to create address dao.
	 * @param address
	 * @return Address id
	 * @throws ValidationException
	 * @throws ServiceException
	 */
	 public int create(Address address) throws ValidationException, ServiceException {
		 	try {
		 		AddressValidator.validate(address);
		 	int addressIdIf = this.findByAreaAndDistrict(address.getArea(), address.getDistrict());
		 	if(addressIdIf > 0) {
		 		return addressIdIf;
		 	}else {
		 		AddressDAO addressDAO = new AddressDAO();
		 		int newAddressId = addressDAO.create(address);
		 		return newAddressId;
		 	}
		 	}catch(ValidationException e) {
		 		e.printStackTrace();
				throw new ValidationException(e.getMessage());
		 	}catch(PersistanceException e) {
		 		e.printStackTrace();
				throw new ServiceException(e.getMessage());
		 	}
			
	 }
	 /**
	  * This method initiate the validation and pass the data to findByAreaAndDistrict dao.
	  * @param area
	  * @param district
	  * @return Address id
	  * @throws ValidationException
	  * @throws ServiceException
	  */
	 public int findByAreaAndDistrict(String area, String district) throws ValidationException, ServiceException{
		 try {
		 	Address add = new Address();
		 	add.setArea(area);
		 	add.setDistrict(district);
		 	AddressValidator.validate(add);
	 	
			AddressDAO addressDAO = new AddressDAO();
			return addressDAO.findByDistrictAndArea(area, district);
		 }catch(ValidationException e) {
	 		e.printStackTrace();
			throw new ValidationException(e.getMessage());
	 	}catch(PersistanceException e) {
	 		e.printStackTrace();
			throw new ServiceException(e.getMessage());
	 	}
	 }
	 
	 /**
	  * This method initiate the validation and pass the address id to findById dao.
	  * @param id
	  * @return Address object
	  * @throws ValidationException
	  * @throws ServiceException
	  */
	 public Address findById(int id) throws ValidationException, ServiceException{
		 try {
		 	AddressValidator.validateId(id);
	 	
			AddressDAO addressDAO = new AddressDAO();
			return addressDAO.findById(id);
			
		 }catch(ValidationException e) {
	 		e.printStackTrace();
			throw new ValidationException(e.getMessage());
	 	}catch(PersistanceException e) {
	 		e.printStackTrace();
			throw new ServiceException(e.getMessage());
	 	}
	 }
	 
	 /**
	  * 
	  * @param id
	  * @return
	  * @throws ValidationException
	  * @throws ServiceException
	  */
	 public boolean checkAddressExist(int id) throws ValidationException, ServiceException{
		 try {
		 	AddressValidator.validateId(id);
	 	
			AddressDAO addressDAO = new AddressDAO();
			return addressDAO.checkIfExist(id);
		 }catch(ValidationException e) {
		 		e.printStackTrace();
				throw new ValidationException(e.getMessage());
		 	}catch(PersistanceException e) {
		 		e.printStackTrace();
				throw new ServiceException(e.getMessage());
		 	}
	 }
	 public Set<Address> getAllAddress() throws ServiceException{
		 try {
			AddressDAO addressDAO = new AddressDAO();
			return addressDAO.getAll();
	 	}catch(PersistanceException e) {
	 		e.printStackTrace();
			throw new ServiceException(e.getMessage());
	 	}
	 }
}
