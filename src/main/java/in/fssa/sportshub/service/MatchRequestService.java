package in.fssa.sportshub.service;

import java.util.Set;

import in.fssa.sportshub.dao.MatchRequestDAO;
import in.fssa.sportshub.exception.ValidationException;
import in.fssa.sportshub.model.MatchRequest;
import in.fssa.sportshub.model.TeamMember;
import in.fssa.sportshub.validator.MatchRequestValidator;

public class MatchRequestService {
	public void create(MatchRequest matchRequest, int captainId) throws Exception{
		
		MatchRequestValidator.validateAll(matchRequest);
		MatchRequestValidator.validateId(captainId, "player");
		int typeOfMatch = MatchRequestValidator.validateTypeOfMatch(matchRequest);
		
		
		PlayerService playerService = new PlayerService();
		boolean checkPlayerExist = playerService.playerExist(captainId);
		if(!checkPlayerExist){
			throw new Exception("Player not exist");
		}
		TeamMemberService teamMemService = new TeamMemberService();
		boolean isCaptain = teamMemService.isPlayerCaptain(captainId);
		if(!isCaptain){
			throw new Exception("Player not captain of any team");
		}
		TeamMember teamMemberData = teamMemService.findById(matchRequest.getCreatedBy());
		
		if(teamMemberData == null) {
			throw new ValidationException("Created by id not exist");
		}
		if(captainId != teamMemberData.getUserId()) {
			throw new ValidationException("Captain id not match with the team member captain id");
		}
		if(matchRequest.getToTeam() == teamMemberData.getTeamId()) {
			throw new ValidationException("Created team and sent team same");
		}
		
		MatchRequestDAO matchReqDao = new MatchRequestDAO();
		if(typeOfMatch == 1) {
			TeamService teamService = new TeamService();
			boolean checkTeamExist = teamService.checkTeamExist(matchRequest.getToTeam());
			if(!checkTeamExist) {
				throw new ValidationException("To team not exist");
			}
			matchReqDao.createToTeam(matchRequest);
			System.out.println("match created to team");
		}
		else if(typeOfMatch == 2) {
			AddressService addressService = new AddressService();
			boolean checkAddressExist = addressService.checkAddressExist(matchRequest.getAddressId());
			if(!checkAddressExist) {
				throw new ValidationException("Address not exist");
			}
			matchReqDao.createToArea(matchRequest);
			System.out.println("match created to area");
		}	
	}
	
	public Set<MatchRequest> listAllOpenRequest(){
		MatchRequestDAO matchReqDao = new MatchRequestDAO();
		return matchReqDao.listAllOpenRequest();
	}
}
