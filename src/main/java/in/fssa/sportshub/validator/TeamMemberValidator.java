package in.fssa.sportshub.validator;

import in.fssa.sportshub.exception.ValidationException;

public class TeamMemberValidator {
	public static void validateId(int id, String name) throws ValidationException {
		
		if(id <= 0) {
			throw new ValidationException("Invalid "+name+" id");
		}
	}
}
