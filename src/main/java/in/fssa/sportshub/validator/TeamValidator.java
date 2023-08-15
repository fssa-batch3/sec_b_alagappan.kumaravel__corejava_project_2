package in.fssa.sportshub.validator;

import in.fssa.sportshub.exception.ValidationException;
import in.fssa.sportshub.model.Team;
import in.fssa.sportshub.util.StringUtil;

public class TeamValidator {
	
	public static void validateAll(Team team) throws ValidationException {
		TeamValidator.validatePartial(team);
		AddressValidator.validate(team.getAddress());
		TeamValidator.validateId(team.getCreatedBy(), "create player");
		TeamValidator.validateId(team.getModifiedBy(), "modify player");
	}
	
	public static void validatePartial(Team team) throws ValidationException {
		
		if(team == null) {
			throw new ValidationException("Invalid team input");
		}
		
		StringUtil.rejectIfInvalidString(team.getTeamName(), "Team name");
		StringUtil.rejectIfPatternDoesNotMatch(team.getTeamName(), "Team name");
		if(team.getTeamName().length() < 5 || team.getTeamName().length() > 20) {
			throw new ValidationException("Team name length does not match pattern");
		}
		if(team.getAbout() != null) {
			 if(team.getAbout().length() > 50) {
					throw new ValidationException("About team data length does not match pattern");
				}
		 }
	}
	
	public static void validateId(int id, String name) throws ValidationException {
		
		if(id <= 0) {
			throw new ValidationException("Invalid "+name+" id");
		}
	}
}
