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
	
	public static void rejectIfInvalidImageUrl(String input, String inputName) throws ValidationException {
	    if (input == null || "".equals(input.trim())) {
	        throw new ValidationException(inputName.concat(" can't be null or empty"));
	    }

	    // Define a regular expression pattern for image URLs
	    String imageUrlPattern = "^https?://.+(\\.jpeg|\\.jpg|\\.png|\\.gif|\\.bmp|\\.webp)$";
	    
	    // Create a Pattern object
	    Pattern pattern = Pattern.compile(imageUrlPattern, Pattern.CASE_INSENSITIVE);

	    // Create a Matcher to match the input against the pattern
	    Matcher matcher = pattern.matcher(input);

	    // Check if the input matches the pattern
	    if (!matcher.matches()) {
	        throw new ValidationException("Invalid " + inputName + " URL");
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
