package in.fssa.sportshub.validator;

import in.fssa.sportshub.exception.ServiceException;
import in.fssa.sportshub.exception.ValidationException;
import in.fssa.sportshub.model.Team;
import in.fssa.sportshub.service.PlayerService;
import in.fssa.sportshub.service.TeamMemberService;
import in.fssa.sportshub.service.TeamService;
import in.fssa.sportshub.util.StringUtil;

public class TeamValidator {
	
	public static void validateCreate(Team team) throws ValidationException {
		
		TeamValidator.validateAll(team);
		try {
		PlayerService playerService = new PlayerService();
		boolean checkPlayerExist = playerService.playerExist(team.getCreatedBy());
		if(!checkPlayerExist){
			throw new ValidationException("Player not exist");
		}
		TeamMemberService teamMemService = new TeamMemberService();
		boolean isCaptain = teamMemService.isPlayerCaptain(team.getCreatedBy());
		if(isCaptain){
			throw new ValidationException("Player already a captain in team");
		}
		TeamService teamServ = new TeamService();
		boolean checkTeamNameExist = teamServ.checkTeamNameExist(team.getTeamName());
		if(checkTeamNameExist){
			throw new ValidationException("Team name already exist");
		}
		
		} catch (ValidationException | ServiceException e) {
			e.printStackTrace();
			throw new ValidationException(e.getMessage());
		}
	}
	public static void validateUpdate(Team team) throws ValidationException {
		TeamValidator.validatePartial(team);
		TeamValidator.validateId(team.getModifiedBy(), "modify player");
		try {
		PlayerService playerService = new PlayerService();
		boolean checkPlayerExist = playerService.playerExist(team.getModifiedBy());
		if(!checkPlayerExist){
			throw new ValidationException("Player not exist");
		}
		TeamService teamServ = new TeamService();
		boolean checkTeamExist = teamServ.checkTeamExist(team.getId());
		if(!checkTeamExist){
			throw new ValidationException("Team not exist");
		}
		
		TeamMemberService teamMemService = new TeamMemberService();
		boolean isCaptain = teamMemService.isPlayerCaptainOfSpecificTeam(team.getModifiedBy(), team.getId());
		if(!isCaptain){
			throw new ValidationException("player not a captian of this team");
		}
		} catch (ValidationException | ServiceException e) {
			e.printStackTrace();
			throw new ValidationException(e.getMessage());
		}
	}
	public static void validateDelete(int teamId, int playerId) throws ValidationException {
	
		TeamValidator.validateId(teamId, "team");
		TeamValidator.validateId(playerId, "player");
		try {
		PlayerService playerService = new PlayerService();
		boolean checkPlayerExist = playerService.playerExist(playerId);
		if(!checkPlayerExist){
			throw new ValidationException("Player not exist");
		}
		TeamService teamServ = new TeamService();
		boolean checkTeamExist = teamServ.checkTeamExist(teamId);
		if(!checkTeamExist){
			throw new ValidationException("Team not exist");
		}
		
		TeamMemberService teamMemService = new TeamMemberService();
		boolean isCaptain = teamMemService.isPlayerCaptainOfSpecificTeam(playerId, teamId);
		if(!isCaptain){
			throw new ValidationException("player not a captian of this team");
		}
		} catch (ValidationException | ServiceException e) {
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
