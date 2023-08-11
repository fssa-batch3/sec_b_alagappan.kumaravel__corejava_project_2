package in.fssa.sportshub.validator;

import in.fssa.sportshub.exception.ValidationException;
import in.fssa.sportshub.model.MatchRequest;
import in.fssa.sportshub.util.StringUtil;

public class MatchRequestValidator {
	
	public static void validateAll(MatchRequest matchRequest) throws ValidationException {
		if(matchRequest == null) {
			throw new ValidationException("Invalid match request input");
		}
		TeamValidator.validateId(matchRequest.getCreatedBy(), "Create Player");
		StringUtil.rejectIfInvalidString(matchRequest.getCreatedBy(), "Created by");
		StringUtil.rejectIfPatternDoesNotMatch(team.getTeamName(), "TeamName");
		

		TeamValidator.validateId(team.getModifiedBy(), "Modify Player");
	}
	
	public static void validateId(int id, String name) throws ValidationException {
		
		if(id <= 0) {
			throw new ValidationException("Invalid "+name+" id");
		}
	}
}
