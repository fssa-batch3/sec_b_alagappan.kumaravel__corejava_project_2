package in.fssa.sportshub.validator;

import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Year;

import in.fssa.sportshub.exception.ValidationException;
import in.fssa.sportshub.model.MatchRequest;
import in.fssa.sportshub.util.StringUtil;

public class MatchRequestValidator {
	
	public static void validateAll(MatchRequest matchRequest) throws ValidationException {
		if(matchRequest == null) {
			throw new ValidationException("Invalid match request input");
		}
		
		TeamValidator.validateId(matchRequest.getCreatedBy(), "create player");
		if(matchRequest.getTypeOfMatch() < 1 || matchRequest.getTypeOfMatch() > 2) {
			throw new ValidationException("Invalid type of match input");
		}
		if(matchRequest.getMembers() < 4 || matchRequest.getMembers() > 15) {
			throw new ValidationException("Invalid members count");
		}
		if(matchRequest.getMembersAgeFrom() < 10 || matchRequest.getMembersAgeFrom() > 50) {
			throw new ValidationException("Invalid members from age");
		}
		if(matchRequest.getMembersAgeTo() < 10 || matchRequest.getMembersAgeTo() > 50) {
			throw new ValidationException("Invalid members to age");
		}
		
		// match time validation here
		 LocalDateTime currentDateTime = LocalDateTime.now();  
		 LocalDateTime matchDateAndTime = matchRequest.getMatchTime(); 
//		 if (MatchRequestValidator.isValidLeapYearDateOfBirth(matchDateAndTime)) {
//			 throw new ValidationException("Date of birth is a leap year");
//	      }
		 if (matchDateAndTime.isBefore(currentDateTime)) {
			 throw new ValidationException("Match time should be in future");
	        }
		
		StringUtil.rejectIfInvalidString(matchRequest.getLocation(), "Match location");
		if(matchRequest.getLocation().length() < 5 || matchRequest.getLocation().length() > 50) {
			throw new ValidationException("Location char length does not match pattern");
		}
		
		if(matchRequest.getInformation() != null) {
			if(matchRequest.getInformation().length() > 50) {
				throw new ValidationException("Information char length does not match pattern");
			}
		}
		

	}
	
	public static void validateId(int id, String name) throws ValidationException {
		
		if(id <= 0) {
			throw new ValidationException("Invalid "+name+" id");
		}
	}
	
	public static int validateTypeOfMatch(MatchRequest matchRequest) throws ValidationException {
		int value = 0;
		if(matchRequest.getToTeam() <= 0) {
			if(matchRequest.getAddressId() <= 0){
				throw new ValidationException("Both to team and area not valid");
			}else {
				value = 2;
			}
		}else {
			if(matchRequest.getAddressId() > 0){
				throw new ValidationException("Any one type should only update");
			}else {
				value = 1;
			}
		}
		return value;
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
}
