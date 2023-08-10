package in.fssa.sportshub.service;

import in.fssa.sportshub.dao.TeamMemberDAO;
import in.fssa.sportshub.model.TeamMember;
import in.fssa.sportshub.validator.TeamMemberValidator;

public class TeamMemberService {
public void create(TeamMember teamMember) throws Exception{
		
		TeamMemberValidator.validateId(teamMember.getTeamId(), "Team");
		TeamMemberValidator.validateId(teamMember.getUserId(), "Player");
		PlayerService playerService = new PlayerService();
		boolean checkPlayerExist = playerService.playerExist(teamMember.getUserId());
		if(checkPlayerExist){
			throw new Exception("Player not exist");
		}
		
		boolean isCaptain = this.isPlayerCaptain(teamMember.getUserId());
		if(isCaptain){
			throw new Exception("Player already a captain in team");
		}
		
		TeamService teamService = new TeamService();
		boolean checkTeamNameExist = teamService.checkTeamExist(teamMember.getTeamId());
		if(!checkTeamNameExist) {
			throw new Exception("Team not exist");
		}
		
		TeamMemberDAO dao = new TeamMemberDAO();
		dao.create(teamMember);
			
	}


	public boolean isPlayerCaptain(int id) throws Exception{
		TeamMemberValidator.validateId(id, "Player");
		TeamMemberDAO dao = new TeamMemberDAO();
		return dao.isPlayerCaptain(id);
	}
	
	public boolean isPlayerCaptainOfSpecificTeam(int player_id, int team_id) throws Exception{
		TeamMemberValidator.validateId(player_id, "Player");
		TeamMemberValidator.validateId(team_id, "Team");
		TeamMemberDAO dao = new TeamMemberDAO();
		return dao.isPlayerCaptainOfSpecificTeam(player_id, team_id);
	}

}