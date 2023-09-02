package in.fssa.sportshub.validator;

import in.fssa.sportshub.dao.AddressDAO;
import in.fssa.sportshub.exception.PersistanceException;
import in.fssa.sportshub.exception.ServiceException;
import in.fssa.sportshub.exception.ValidationException;
import in.fssa.sportshub.model.Address;
import in.fssa.sportshub.util.StringUtil;

public class AddressValidator {
	
	public static void validate(Address address) throws ValidationException {
		
		if(address == null) {
			throw new ValidationException("Invalid Address input");
		}
		StringUtil.rejectIfInvalidString(address.getArea(), "Area");
		StringUtil.rejectIfInvalidString(address.getDistrict(), "District");
		StringUtil.rejectIfPatternDoesNotMatch(address.getArea(), "Area");
		StringUtil.rejectIfPatternDoesNotMatch(address.getDistrict(), "District");
		if(address.getArea().length() < 4 || address.getArea().length() > 25) {
			throw new ValidationException("Area length does not match pattern");
		}
		if(address.getDistrict().length() < 4 || address.getDistrict().length() > 25) {
			throw new ValidationException("District length does not match pattern");
		}
	}
	
	public static void validateId(int id) throws ValidationException {
			
			if(id <= 0) {
				throw new ValidationException("Invalid adderess id");
			}
		}
	
	 /**
	  * 
	  * @param id
	  * @return
	  * @throws ValidationException
	  * @throws ServiceException
	  */
	 public static boolean checkAddressExist(int id) throws ValidationException, ServiceException{
		 boolean result;
		 try {
		 	AddressValidator.validateId(id);
	 	
			AddressDAO addressDAO = new AddressDAO();
			 result = addressDAO.checkIfExist(id);
		 }catch(ValidationException e) {
		 		e.printStackTrace();
				throw new ValidationException(e.getMessage());
		 	}catch(PersistanceException e) {
		 		e.printStackTrace();
				throw new ServiceException(e.getMessage());
		 	}
		 return result;
	 }
	 
	
}
