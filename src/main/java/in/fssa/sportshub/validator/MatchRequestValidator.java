package in.fssa.sportshub.validator;

import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Year;

import in.fssa.sportshub.exception.ServiceException;
import in.fssa.sportshub.exception.ValidationException;
import in.fssa.sportshub.model.MatchRequest;
import in.fssa.sportshub.model.TeamMember;
import in.fssa.sportshub.service.TeamMemberService;
import in.fssa.sportshub.util.StringUtil;

public class MatchRequestValidator {
	
	public static void validateCreate(MatchRequest matchRequest, int captainId) throws ValidationException, ServiceException{
		MatchRequestValidator.validateAll(matchRequest);
		MatchRequestValidator.validateId(captainId, "player");
		MatchRequestValidator.validateTypeOfMatch(matchRequest);
		
		
		
		boolean checkPlayerExist = PlayerValidator.playerExist(captainId);
		if(!checkPlayerExist){
			throw new ValidationException("Player not exist");
		}
		TeamMemberService teamMemService = new TeamMemberService();
		boolean isCaptain = TeamMemberValidator.isPlayerCaptain(captainId);
		if(!isCaptain){
			throw new ValidationException("Player not captain of any team");
		}
		TeamMember teamMemberData = teamMemService.findById(matchRequest.getCreatedBy());
		
		if(teamMemberData == null) {
			throw new ValidationException("Created by id not exist");
		}
		if(captainId != teamMemberData.getUserId()) {
			throw new ValidationException("Captain id not match with the team member captain id");
		}
		if(matchRequest.getToTeam() == teamMemberData.getTeamId()) {
			throw new ValidationException("Created team and sent team same");
		}
	}
	
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
	
	public static void validateTypeOfMatch(MatchRequest matchRequest) throws ValidationException {
		
		if(matchRequest.getOpponentType().getDisplayName() == "1"  && (matchRequest.getToTeam() <= 0)){
				throw new ValidationException("Invalid to team id");
			
				
		}
		if(matchRequest.getOpponentType().getDisplayName() == "2" && (matchRequest.getAddressId() <= 0)){
				throw new ValidationException("Invalid to address id");
			
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
}
