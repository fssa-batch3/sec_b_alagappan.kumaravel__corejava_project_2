package in.fssa.sportshub.validator;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

import in.fssa.sportshub.dao.TeamMemberDAO;
import in.fssa.sportshub.exception.PersistanceException;
import in.fssa.sportshub.exception.ServiceException;
import in.fssa.sportshub.exception.ValidationException;
import in.fssa.sportshub.model.MatchRequestDTO;
import in.fssa.sportshub.model.TeamMember;
import in.fssa.sportshub.service.MatchRequestService;
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
		boolean checkPlayerExist = PlayerValidator.playerExist(teamMember.getUserId());
		if(!checkPlayerExist){
			throw new ValidationException("Player not exist");
		}
		boolean isCaptain = TeamMemberValidator.isPlayerCaptain(teamMember.getUserId());
		if(isCaptain){
			throw new ValidationException("Player already a captain in team");
		}
		
		boolean checkTeamNameExist = TeamValidator.checkTeamExist(teamMember.getTeamId());
		if(!checkTeamNameExist) {
			throw new ValidationException("Team not exist");
		}
		} catch (ServiceException e) {
			e.printStackTrace();
			throw new ValidationException(e.getMessage());
		}
	}
	
	
	public static void validateDelete(int teamId, int playerId) throws ValidationException {
		TeamMemberValidator.validateId(teamId, "Team");
		TeamMemberValidator.validateId(playerId, "Player");
		try {
		boolean checkPlayerExist = PlayerValidator.playerExist(playerId);
		if(!checkPlayerExist){
			throw new ValidationException("Player not exist");
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
	
	public static boolean isPlayerCaptain(int id) throws ValidationException, ServiceException{
		boolean result;
		try {
		TeamMemberValidator.validateId(id, "Player");
		TeamMemberDAO dao = new TeamMemberDAO();
		 result = dao.isPlayerCaptain(id);
		}catch(ValidationException e) {
	 		e.printStackTrace();
			throw new ValidationException(e.getMessage());
	 	}catch(PersistanceException e) {
	 		e.printStackTrace();
			throw new ServiceException(e.getMessage());
	 	}
		return result;
	}
	
	public static boolean isPlayerCaptainOfSpecificTeam(int player_id, int team_id) throws ValidationException, ServiceException{
		boolean result;
		try {
		TeamMemberValidator.validateId(player_id, "Player");
		TeamMemberValidator.validateId(team_id, "Team");
		TeamMemberDAO dao = new TeamMemberDAO();
		result =  dao.isPlayerCaptainOfSpecificTeam(player_id, team_id);
		}catch(ValidationException e) {
	 		e.printStackTrace();
			throw new ValidationException(e.getMessage());
	 	}catch(PersistanceException e) {
	 		e.printStackTrace();
			throw new ServiceException(e.getMessage());
	 	}
		return result;
	}
	
	public static void validateListAllTeamMemberRequest(int teamId) throws ValidationException {
		boolean checkTeamNameExist;
		try {
			checkTeamNameExist = TeamValidator.checkTeamExist(teamId);
			if(!checkTeamNameExist) {
				throw new ValidationException("Team not exist");
			}
		} catch (ValidationException | ServiceException e) {
			e.printStackTrace();
		}
		
	}
	
	public static void validateListAllPlayerRequestByPlayerId(int playerId) throws ValidationException {
		try {
			boolean checkPlayerExist = PlayerValidator.playerExist(playerId);
			if(!checkPlayerExist){
				throw new ValidationException("Player not exist");
			}
		} catch (ValidationException | ServiceException e) {
			e.printStackTrace();
		}
		
	}
	
	
	public static void validateDeleteRequest(int requestId, int playerId) throws ValidationException {
		try {
			boolean checkPlayerExist = PlayerValidator.playerExist(playerId);
			if(!checkPlayerExist){
				throw new ValidationException("Player not exist");
			}
			
			TeamMemberService teamMemberService = new TeamMemberService();
			TeamMember teamMembervalidate = teamMemberService.findById(requestId);
			if(teamMembervalidate == null) {
				throw new ValidationException("Request id not found");
			}
			
			
		} catch (ValidationException | ServiceException e) {
			e.printStackTrace();
		}
		
	}
	
	public static void validateAcceptRequest(int requestId, int captainId) throws ValidationException {
		try {
			TeamMemberService teamMemberService = new TeamMemberService();
			boolean checkPlayerExist = PlayerValidator.playerExist(captainId);
			if(!checkPlayerExist){
				throw new ValidationException("Player not exist");
			}
			boolean isCaptain = TeamMemberValidator.isPlayerCaptain(captainId);
			if(!isCaptain){
				throw new ValidationException("Player not a captain of team");
			}
			
			TeamMember teamMembervalidate = teamMemberService.findById(requestId);
			if(teamMembervalidate == null) {
				throw new ValidationException("Request id not found");
			}
			
			boolean isCaptain1 = TeamMemberValidator.isPlayerCaptainOfSpecificTeam(captainId, teamMembervalidate.getTeamId());
			if(!isCaptain1){
				throw new ValidationException("player not a captian of this team");
			}
			
			
		} catch (ValidationException | ServiceException e) {
			e.printStackTrace();
		}
		
	}
	
	public static void validateRejectRequest(int requestId, int captainId) throws ValidationException {
		TeamMemberService teamMemberService = new TeamMemberService();
		try {
			boolean checkPlayerExist = PlayerValidator.playerExist(captainId);
			if(!checkPlayerExist){
				throw new ValidationException("Player not exist");
			}
			boolean isCaptain = TeamMemberValidator.isPlayerCaptain(captainId);
			if(!isCaptain){
				throw new ValidationException("Player not a captain of team");
			}
			
			TeamMember teamMembervalidate = teamMemberService.findById(requestId);
			if(teamMembervalidate == null) {
				throw new ValidationException("Request id not found");
			}
			
			boolean isCaptain1 = TeamMemberValidator.isPlayerCaptainOfSpecificTeam(captainId, teamMembervalidate.getTeamId());
			if(!isCaptain1){
				throw new ValidationException("player not a captian of this team");
			}
			
			
		} catch (ValidationException | ServiceException e) {
			e.printStackTrace();
		}
		
	}
	
	
	public static void validateExitTeam(int teamId, int playerId) throws ValidationException {
		try {
			boolean checkPlayerExist = PlayerValidator.playerExist(playerId);
			if(!checkPlayerExist){
				throw new ValidationException("Player not exist");
			}
			
			boolean checkTeamNameExist = TeamValidator.checkTeamExist(teamId);
			if(!checkTeamNameExist) {
				throw new ValidationException("Team not exist");
			}
			
			MatchRequestService matchRequestServ = new MatchRequestService();
			Set<MatchRequestDTO> listOfMatchInvitation = matchRequestServ.listOfMyMatchByPlayerId(playerId);
			LocalDateTime currentTime = LocalDateTime.now();
			Set<MatchRequestDTO> filteredList = listOfMatchInvitation.stream()
			    .filter(matchRequest -> matchRequest.getMatchTime().isAfter(currentTime))
			    .collect(Collectors.toSet());
			Set<MatchRequestDTO> uniqueFilteredList = new HashSet<>(filteredList);
			if(uniqueFilteredList.size() > 0) {
				throw new ValidationException("You can exit this team after completed your upcomming match");
			}
			
			
		} catch (ValidationException | ServiceException e) {
			e.printStackTrace();
		}
		
	}
	
}
