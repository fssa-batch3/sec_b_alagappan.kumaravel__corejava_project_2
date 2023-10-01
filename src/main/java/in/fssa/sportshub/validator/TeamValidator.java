package in.fssa.sportshub.validator;

import in.fssa.sportshub.dao.TeamDAO;
import in.fssa.sportshub.exception.PersistanceException;
import in.fssa.sportshub.exception.ServiceException;
import in.fssa.sportshub.exception.ValidationException;
import in.fssa.sportshub.model.Team;
import in.fssa.sportshub.service.PlayerService;
import in.fssa.sportshub.service.TeamMemberService;
import in.fssa.sportshub.service.TeamService;
import in.fssa.sportshub.util.StringUtil;

public class TeamValidator {
	
	public static void validateCreate(Team team) throws ValidationException {

		try {
		TeamValidator.validateAll(team);
		
		StringUtil.rejectIfInvalidString(team.getOpenForPlayerDescription(), "Open for player description");
		StringUtil.rejectIfPatternDoesNotMatch(team.getOpenForPlayerDescription(), "Open for player description");
		if(team.getOpenForPlayerDescription().length() < 5 || team.getOpenForPlayerDescription().length() > 50) {
			throw new ValidationException("Open for player description length does not match pattern");
		}
		
		boolean checkPlayerExist = PlayerValidator.playerExist(team.getCreatedBy());
		if(!checkPlayerExist){
			throw new ValidationException("Player not exist");
		}
		boolean isCaptain = TeamMemberValidator.isPlayerCaptain(team.getCreatedBy());
		if(isCaptain){
			throw new ValidationException("Player already a captain in team");
		}
		boolean checkTeamNameExist = TeamValidator.checkTeamNameExist(team.getTeamName());
		if(checkTeamNameExist){
			throw new ValidationException("Team name already exist");
		}
		
		} catch (ServiceException e) {
			e.printStackTrace();
			throw new ValidationException(e.getMessage());
		}
	}
	public static void validateUpdate(Team team) throws ValidationException {
		try {
			TeamValidator.validatePartial(team);
			AddressValidator.validate(team.getAddress());
			TeamValidator.validateId(team.getModifiedBy(), "modify player");
		boolean checkPlayerExist = PlayerValidator.playerExist(team.getModifiedBy());
		if(!checkPlayerExist){
			throw new ValidationException("Player not exist");
		}

		boolean checkTeamExist = TeamValidator.checkTeamExist(team.getId());
		if(!checkTeamExist){
			throw new ValidationException("Team not exist");
		}
		boolean checkTeamNameExist = TeamValidator.nameAlreadyExistWithTeamId(team.getId(),team.getTeamName());
		if(checkTeamNameExist){
			throw new ValidationException("Team name already exist");
		}
		
		boolean isCaptain = TeamMemberValidator.isPlayerCaptainOfSpecificTeam(team.getModifiedBy(), team.getId());
		if(!isCaptain){
			throw new ValidationException("player not a captian of this team");
		}
		} catch (ServiceException e) {
			e.printStackTrace();
			throw new ValidationException(e.getMessage());
		}
	}
	public static void validateDelete(int teamId, int playerId) throws ValidationException {
	
		
		try {
		TeamValidator.validateId(teamId, "team");
		TeamValidator.validateId(playerId, "player");
		boolean checkPlayerExist = PlayerValidator.playerExist(playerId);
		if(!checkPlayerExist){
			throw new ValidationException("Player not exist");
		}

		boolean checkTeamExist = TeamValidator.checkTeamExist(teamId);
		if(!checkTeamExist){
			throw new ValidationException("Team not exist");
		}
		
		boolean isCaptain = TeamMemberValidator.isPlayerCaptainOfSpecificTeam(playerId, teamId);
		if(!isCaptain){
			throw new ValidationException("player not a captian of this team");
		}
		} catch (ServiceException e) {
			e.printStackTrace();
			throw new ValidationException(e.getMessage());
		}
	}
	
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
		
		StringUtil.rejectIfInvalidImageUrl(team.getUrl(), "Team image url");
		StringUtil.rejectIfInvalidString(team.getTeamName(), "Team name");
		StringUtil.rejectIfPatternDoesNotMatch(team.getTeamName(), "Team name");
		if(team.getTeamName().length() < 5 || team.getTeamName().length() > 50) {
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
	
	
	public static boolean checkTeamNameExist(String teamName) throws ValidationException, ServiceException{
		boolean result;
		try {
		StringUtil.rejectIfInvalidString(teamName, "TeamName");
		TeamDAO teamDAO = new TeamDAO();
		result = teamDAO.nameAlreadyExist(teamName);
		}catch(ValidationException e) {
	 		e.printStackTrace();
			throw new ValidationException(e.getMessage());
	 	}catch(PersistanceException e) {
	 		e.printStackTrace();
			throw new ServiceException(e.getMessage());
	 	}
		return result;
	}
	
	public static boolean nameAlreadyExistWithTeamId(int teamId, String teamName) throws ValidationException, ServiceException{
		boolean result;
		try {
		StringUtil.rejectIfInvalidString(teamName, "TeamName");
		TeamDAO teamDAO = new TeamDAO();
		result = teamDAO.nameAlreadyExistWithTeamId(teamId,teamName);
		}catch(ValidationException e) {
	 		e.printStackTrace();
			throw new ValidationException(e.getMessage());
	 	}catch(PersistanceException e) {
	 		e.printStackTrace();
			throw new ServiceException(e.getMessage());
	 	}
		return result;
	}
	
public static boolean checkTeamExist(int id) throws ValidationException, ServiceException{
	boolean result;	
	try {
		TeamValidator.validateId(id, "Team");
		TeamDAO teamDAO = new TeamDAO();
		result = teamDAO.checkExistById(id);
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
