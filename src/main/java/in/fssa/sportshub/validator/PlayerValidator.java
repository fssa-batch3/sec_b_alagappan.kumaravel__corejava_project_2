package in.fssa.sportshub.validator;

import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.Year;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import in.fssa.sportshub.dao.PlayerDAO;
import in.fssa.sportshub.exception.PersistanceException;
import in.fssa.sportshub.exception.ServiceException;
import in.fssa.sportshub.exception.ValidationException;
import in.fssa.sportshub.model.Player;
import in.fssa.sportshub.service.PlayerService;
import in.fssa.sportshub.service.TeamMemberService;
import in.fssa.sportshub.util.StringUtil;

public class PlayerValidator {
	
	/**
	 * 
	 * @param player
	 * @throws ValidationException
	 */
	public static void validateCreate(Player player) throws ValidationException {
		PlayerValidator.validateAll(player);
	
		boolean checkPhoneNumberExist;
		try {
			checkPhoneNumberExist = PlayerValidator.phoneNumberAlreadyExist(player.getPhoneNumber());
			if(checkPhoneNumberExist){
				throw new ValidationException("Phone number already exist");
			}
		} catch (ServiceException e) {
			e.printStackTrace();
			throw new ValidationException(e.getMessage());
		}
	}
	
	public static void validateUpdate(Player player) throws ValidationException {
		PlayerValidator.validatePartial(player);
		AddressValidator.validate(player.getAddress());
		try {
		boolean checkPlayerExist = PlayerValidator.playerExist(player.getId());
		if(!checkPlayerExist){
			throw new ValidationException("Player not exist");
		}
		}catch (ServiceException e) {
		e.printStackTrace();
		throw new ValidationException(e.getMessage());
		}
	}
	
	public static void validateDelete(int id) throws ValidationException {
		
		PlayerValidator.validateId(id, "Player");
		try {
		boolean checkPlayerExist = PlayerValidator.playerExist(id);
		if(!checkPlayerExist){
			throw new ValidationException("Player not exist");
		}
		boolean isCaptain = TeamMemberValidator.isPlayerCaptain(id);
		
		if(isCaptain){
			throw new ValidationException("Player is captain of a team");
		}
		}catch (ServiceException e) {
			e.printStackTrace();
			throw new ValidationException(e.getMessage());
		}
	}
	
	public static void validateDeleteChange(int id) throws ValidationException {
		
		PlayerValidator.validateId(id, "Player");
	}
	
	public static void validateAll(Player player) throws ValidationException {
		PlayerValidator.validatePartial(player);
		PlayerValidator.validatePhoneNumber(player.getPhoneNumber());
		AddressValidator.validate(player.getAddress());
	}
	public static void validatePartial(Player player) throws ValidationException {
		
		if(player == null) {
			throw new ValidationException("Invalid player input");
		}
		
		StringUtil.rejectIfInvalidString(player.getUserName(), "User name");
		StringUtil.rejectIfPatternDoesNotMatch(player.getUserName(), "User name");
		StringUtil.rejectIfInvalidString(player.getFirstName(), "First name");
		StringUtil.rejectIfPatternDoesNotMatch(player.getFirstName(), "First name");
		if(player.getUserName().length() < 5 || player.getUserName().length() > 20) {
			throw new ValidationException("User name length does not match pattern");
		}
		if(player.getFirstName().length() < 3 || player.getFirstName().length() > 20) {
			throw new ValidationException("First name length does not match pattern");
		}
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
		 if(dateOfBirth ==  null) {
			 throw new ValidationException("Date of birth can not be null");
		 }
		 LocalDate minimumValidDate = currentDate.minusYears(10);
		 if(dateOfBirth.isAfter(currentDate) || !dateOfBirth.isBefore(minimumValidDate)) {
				throw new ValidationException("Age should be more than 10 years");
		}
		 if (PlayerValidator.isValidLeapYearDateOfBirth(dateOfBirth)) {
			 throw new ValidationException("Date of birth is a leap year");
	      }
		 if(player.getLastName() != null) {
			 StringUtil.rejectIfPatternDoesNotMatch(player.getLastName(), "Last name");
			 if(player.getLastName().length() < 1 || player.getLastName().length() > 20) {
					throw new ValidationException("Last name length does not match pattern");
				}
		 }
		 
		 if(player.getAbout() != null) {
			 if(player.getAbout().length() > 50) {
					throw new ValidationException("About player data length does not match pattern");
				}
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
	
	public static void validatePassword(String password) throws ValidationException {
		
		StringUtil.rejectIfInvalidString(password, "Password");	
		//password match pattern here
        String regex = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=!])(?=\\S+$).{8,}$";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(password);
        
        if(!matcher.matches()) {
        	throw new ValidationException("Invalid password");
        }
	}
	
	 public static boolean isValidLeapYearDateOfBirth(LocalDate dateOfBirth) {
	        try {
	            // Check if the year is a leap year
	            boolean isLeapYear = Year.of(dateOfBirth.getYear()).isLeap();

	            // Check if the day and month are valid for a leap year
	            return isLeapYear && dateOfBirth.getMonthValue() == 2 && dateOfBirth.getDayOfMonth() <= 29;
	        } catch (DateTimeException e) {
	            return false; // Invalid date format
	        }
	  }
	 
	 
	 
	 
	 
		/**
		 * 
		 * @param phoneNumber
		 * @return
		 * @throws ValidationException, ServiceException
		 */
		public static boolean phoneNumberAlreadyExist(long phoneNumber) throws ValidationException, ServiceException{
			boolean result;
			try {
			PlayerValidator.validatePhoneNumber(phoneNumber);
			
			PlayerDAO dao = new PlayerDAO();
			 result = dao.phoneNumberAlreadyExist(phoneNumber);
	 	}catch(ValidationException e) {
	 		e.printStackTrace();
			throw new ValidationException(e.getMessage());
	 	}catch(PersistanceException e) {
	 		e.printStackTrace();
			throw new ServiceException(e.getMessage());
	 	}
			return result;
		}
		
		/**
		 * 
		 * @param id
		 * @return
		 * @throws ValidationException, ServiceException
		 */
		public static boolean playerExist(int id) throws ValidationException, ServiceException{
			boolean result;
			try {
			PlayerValidator.validateId(id, "Player");
			
			PlayerDAO dao = new PlayerDAO();
			result = dao.checkIfExistById(id);
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
