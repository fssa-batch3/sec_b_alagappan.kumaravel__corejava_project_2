package in.fssa.sportshub.validator;

import java.time.LocalDate;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import in.fssa.sportshub.exception.ValidationException;
import in.fssa.sportshub.model.Player;
import in.fssa.sportshub.util.StringUtil;

public class PlayerValidator {
	public static void validateAll(Player player) throws ValidationException {
		PlayerValidator.validatePartial(player);
		PlayerValidator.validatePhoneNumber(player.getPhoneNumber());
		AddressValidator.validate(player.getAddress());
	}
	public static void validatePartial(Player player) throws ValidationException {
		
		if(player == null) {
			throw new ValidationException("Invalid player input");
		}
		
		StringUtil.rejectIfInvalidString(player.getUserName(), "UserName");
		StringUtil.rejectIfPatternDoesNotMatch(player.getUserName(), "UserName");
		StringUtil.rejectIfInvalidString(player.getFirstName(), "FirstName");
		StringUtil.rejectIfPatternDoesNotMatch(player.getFirstName(), "FirstName");
		StringUtil.rejectIfInvalidString(player.getPassword(), "Password");	
		//password match pattern here
        String regex = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=!])(?=\\S+$).{8,}$";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(player.getPassword());
        
        if(!matcher.matches()) {
        	throw new ValidationException("Invalid password");
        }
		// dateofbirth validate here
		 LocalDate currentDate = LocalDate.now();  
		 LocalDate dateOfBirth = player.getDateOfBirth();
		 LocalDate minimumValidDate = currentDate.minusYears(10);
		 if(!dateOfBirth.isAfter(currentDate) && !dateOfBirth.isBefore(minimumValidDate)) {
				throw new ValidationException("Age should be more than 10 years");
		}
	}
	
	public static void validatePhoneNumber(long phoneNumber) throws ValidationException {
			
			if(phoneNumber < 6000000000l || phoneNumber > 9999999999l) {
				throw new ValidationException("Invalid phone number");
			}
	}
	public static void validateId(int id, String name) throws ValidationException {
		
		if(id <= 0) {
			throw new ValidationException("Invalid "+name+" id");
		}
	}
}
