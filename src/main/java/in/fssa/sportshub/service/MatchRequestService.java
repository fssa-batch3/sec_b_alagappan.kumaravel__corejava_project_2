package in.fssa.sportshub.service;

import in.fssa.sportshub.exception.ValidationException;
import in.fssa.sportshub.model.MatchRequest;

public class MatchRequestService {
	public void create(MatchRequest matchRequest) throws ValidationException{
		
		TeamValidator.validateAll(team);
		PlayerService playerService = new PlayerService();
		boolean checkPlayerExist = playerService.playerExist(team.getCreatedBy());
		if(checkPlayerExist){
			throw new Exception("Player not exist");
		}
		TeamMemberService teamMemService = new TeamMemberService();
		boolean isCaptain = teamMemService.isPlayerCaptain(team.getCreatedBy());
		if(isCaptain){
			throw new Exception("Player already a captain in team");
		}
		boolean checkTeamNameExist = this.checkTeamNameExist(team.getTeamName());
		if(checkTeamNameExist){
			throw new Exception("Team name already exist");
		}
			
			Address address = team.getAddress();//
			AddressService addressService = new AddressService();
			int addressId = addressService.create(address);
			
			team.getAddress().setId(addressId);
			
			TeamDAO teamDao = new TeamDAO();
			int teamId = teamDao.create(team);
		
			TeamMember teamMember = new TeamMember();
			teamMember.setUserId(team.getCreatedBy());
			teamMember.setTeamId(teamId);
			
			teamMemService.create(teamMember);
			
	}
}
