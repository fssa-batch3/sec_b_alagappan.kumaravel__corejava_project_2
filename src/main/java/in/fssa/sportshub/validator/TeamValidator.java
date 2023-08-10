package in.fssa.sportshub.validator;

import in.fssa.sportshub.exception.ValidationException;
import in.fssa.sportshub.model.Team;
import in.fssa.sportshub.util.StringUtil;

public class TeamValidator {
	
	public static void validateAll(Team team) throws ValidationException {
		TeamValidator.validatePartial(team);
		TeamValidator.validateId(team.getCreatedBy(), "Create Player");
		TeamValidator.validateId(team.getModifiedBy(), "Modify Player");
	}
	
	public static void validatePartial(Team team) throws ValidationException {
		
		if(team == null) {
			throw new ValidationException("Invalid team input");
		}
		
		StringUtil.rejectIfInvalidString(team.getTeamName(), "TeamName");
		StringUtil.rejectIfPatternDoesNotMatch(team.getTeamName(), "TeamName");
		
	}
	
	public static void validateId(int id, String name) throws ValidationException {
		
		if(id <= 0) {
			throw new ValidationException("Invalid "+name+" id");
		}
	}
}
