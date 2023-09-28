package in.fssa.sportshub.validator;

import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Year;

import in.fssa.sportshub.exception.PersistanceException;
import in.fssa.sportshub.exception.ServiceException;
import in.fssa.sportshub.exception.ValidationException;
import in.fssa.sportshub.model.MatchRequest;
import in.fssa.sportshub.model.TeamMember;
import in.fssa.sportshub.service.MatchRequestService;
import in.fssa.sportshub.service.TeamMemberService;
import in.fssa.sportshub.util.StringUtil;

public class RequestResponseValidator {
	
	public static void validateAccept(int toTeamCaptainRelationId, int matchRequestId) throws ValidationException, ServiceException{
		RequestResponseValidator.validateId(toTeamCaptainRelationId, "toTeamCaptainRelationId");
		RequestResponseValidator.validateId(matchRequestId, "matchRequestId");
		

		boolean checkMatchRequestExist = MatchRequestValidator.idExist(matchRequestId);
		if(!checkMatchRequestExist){
			throw new ValidationException("Match request id not exist");
		}
		
		TeamMemberService teamMemberService = new TeamMemberService();
		
		TeamMember validateIsIdAvailable = teamMemberService.findById(toTeamCaptainRelationId);
		if(validateIsIdAvailable == null){
			throw new ValidationException("Captain relation id not exist");
		}
		
		TeamMemberService teamMemberServ = new TeamMemberService();
		try {
			teamMemberServ.findById(toTeamCaptainRelationId);
			
		}catch(ValidationException | ServiceException e) {
			e.printStackTrace();
			throw new ValidationException(e.getMessage());
		}
		
	}
	
	
	public static void validateId(int id, String name) throws ValidationException {
		
		if(id <= 0) {
			throw new ValidationException("Invalid "+name+" id");
		}
	}
	
	public static void validateTypeOfMatch(MatchRequest matchRequest) throws ValidationException {
		
		if("1".equals(matchRequest.getOpponentType().getDisplayName())  && (matchRequest.getToTeam() <= 0)){
				throw new ValidationException("Invalid to team id");
			
				
		}
		if("2".equals(matchRequest.getOpponentType().getDisplayName()) && (matchRequest.getAddressId() <= 0)){
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
