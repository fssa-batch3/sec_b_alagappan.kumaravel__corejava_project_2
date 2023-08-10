package in.fssa.sportshub.validator;

import in.fssa.sportshub.exception.ValidationException;
import in.fssa.sportshub.model.Address;
import in.fssa.sportshub.util.StringUtil;

public class AddressValidator {
	public static void validate(Address address) throws ValidationException {
		
		if(address == null) {
			throw new ValidationException("Invalid task input");
		}
		StringUtil.rejectIfInvalidString(address.getArea(), "Area");
		StringUtil.rejectIfInvalidString(address.getDistrict(), "District");
		StringUtil.rejectIfPatternDoesNotMatch(address.getArea(), "Area");
		StringUtil.rejectIfPatternDoesNotMatch(address.getDistrict(), "District");
		if(address.getArea().length() < 4 || address.getArea().length() > 40) {
			throw new ValidationException("Area length does not match pattern");
		}
		if(address.getDistrict().length() < 4 || address.getDistrict().length() > 40) {
			throw new ValidationException("District length does not match pattern");
		}
	}
	
	public static void validateId(int id) throws ValidationException {
			
			if(id <= 0) {
				throw new ValidationException("Invalid adderess id");
			}
		}
	
}
