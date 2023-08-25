package in.fssa.sportshub.validator;

import in.fssa.sportshub.exception.ServiceException;
import in.fssa.sportshub.exception.ValidationException;
import in.fssa.sportshub.model.TeamMember;
import in.fssa.sportshub.service.PlayerService;
import in.fssa.sportshub.service.TeamMemberService;
import in.fssa.sportshub.service.TeamService;

public class TeamMemberValidator {
	public static void validateId(int id, String name) throws ValidationException {
		
		if(id <= 0) {
			throw new ValidationException("Invalid "+name+" id");
		}
	}
	public static void validateCreate(TeamMember teamMember) throws ValidationException {
		TeamMemberValidator.validateId(teamMember.getTeamId(), "Team");
		TeamMemberValidator.validateId(teamMember.getUserId(), "Player");
		try {
		PlayerService playerService = new PlayerService();
		boolean checkPlayerExist = playerService.playerExist(teamMember.getUserId());
		if(!checkPlayerExist){
			throw new ValidationException("Player not exist");
		}
		TeamMemberService serv = new TeamMemberService();
		boolean isCaptain = serv.isPlayerCaptain(teamMember.getUserId());
		if(isCaptain){
			throw new ValidationException("Player already a captain in team");
		}
		
		TeamService teamService = new TeamService();
		boolean checkTeamNameExist = teamService.checkTeamExist(teamMember.getTeamId());
		if(!checkTeamNameExist) {
			throw new ValidationException("Team not exist");
		}
		} catch (ValidationException | ServiceException e) {
			e.printStackTrace();
			throw new ValidationException(e.getMessage());
		}
	}
	public static void validateDelete(int teamId, int playerId) throws ValidationException {
		TeamMemberValidator.validateId(teamId, "Team");
		TeamMemberValidator.validateId(playerId, "Player");
		try {
		PlayerService playerService = new PlayerService();
		boolean checkPlayerExist = playerService.playerExist(playerId);
		if(!checkPlayerExist){
			throw new ValidationException("Player not exist");
		}
		TeamMemberService serv = new TeamMemberService();
		boolean isCaptain = serv.isPlayerCaptainOfSpecificTeam(playerId, teamId);
		if(!isCaptain){
			throw new ValidationException("player not a captian of this team");
		}
		} catch (ValidationException | ServiceException e) {
			e.printStackTrace();
			throw new ValidationException(e.getMessage());
		}
	}
}
