package in.fssa.sportshub.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import in.fssa.sportshub.exception.ValidationException;

public class StringUtil {
	public static void rejectIfInvalidString(String input, String inputName) throws ValidationException {
		if(input == null || "".equals(input.trim())) {
			throw new ValidationException(inputName.concat(" can't be null or empty"));
		}
	}
	
	public static boolean isValidString(String input){
		if(input == null || "".equals(input.trim())) {
			return true;
		}
			return false;
		
	}
	
	public static boolean isInValidString(String input){
		if(input == null || "".equals(input.trim())) {
			return false;
		}
			return true;
		
	}
	
	public static void rejectIfPatternDoesNotMatch(String input, String inputName) throws ValidationException {
		
		Pattern pattern = Pattern.compile("^[A-Za-z ]+$");
		Matcher matcher = pattern.matcher(input);
		
		if(!matcher.matches()) {
			throw new ValidationException(inputName.concat(" does not match pattern"));
		}
	}
	
	
}
