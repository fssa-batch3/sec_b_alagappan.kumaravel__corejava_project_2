package in.fssa.sportshub.service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import in.fssa.sportshub.dao.TeamMemberDAO;
import in.fssa.sportshub.exception.PersistanceException;
import in.fssa.sportshub.exception.ServiceException;
import in.fssa.sportshub.exception.ValidationException;
import in.fssa.sportshub.model.PlayerRequestDTO;
import in.fssa.sportshub.model.TeamMember;
import in.fssa.sportshub.model.TeamRequestDTO;
import in.fssa.sportshub.validator.TeamMemberValidator;

public class TeamMemberService {
public void create(TeamMember teamMember) throws ValidationException, ServiceException{
	try {
		TeamMemberValidator.validateCreate(teamMember);
		TeamMemberDAO dao = new TeamMemberDAO();
		dao.create(teamMember);
	}catch(ValidationException e) {
 		e.printStackTrace();
		throw new ValidationException(e.getMessage());
 	}catch(PersistanceException e) {
 		e.printStackTrace();
		throw new ServiceException(e.getMessage());
 	}
			
}

public void createJoinRequest(TeamMember teamMember) throws ValidationException, ServiceException{
	try {
		TeamMemberValidator.validateCreate(teamMember);
		TeamMemberDAO dao = new TeamMemberDAO();
		dao.createJoinRequest(teamMember);
	}catch(ValidationException e) {
 		e.printStackTrace();
		throw new ValidationException(e.getMessage());
 	}catch(PersistanceException e) {
 		e.printStackTrace();
		throw new ServiceException(e.getMessage());
 	}
			
}



	public TeamMember findById(int id) throws ValidationException, ServiceException{
		try {
		TeamMemberValidator.validateId(id, "Team member id");
		TeamMemberDAO dao = new TeamMemberDAO();
		return dao.findById(id);
		}catch(ValidationException e) {
	 		e.printStackTrace();
			throw new ValidationException(e.getMessage());
	 	}catch(PersistanceException e) {
	 		e.printStackTrace();
			throw new ServiceException(e.getMessage());
	 	}
	}
	
	public TeamMember findByCaptainId(int id) throws ValidationException, ServiceException{
		try {
		TeamMemberValidator.validateId(id, "Captain id");
		TeamMemberDAO dao = new TeamMemberDAO();
		return dao.findByCaptainId(id);
		}catch(ValidationException e) {
	 		e.printStackTrace();
			throw new ValidationException(e.getMessage());
	 	}catch(PersistanceException e) {
	 		e.printStackTrace();
			throw new ServiceException(e.getMessage());
	 	}
	}
	
	public int findCaptainRelIdbyTeamId(int teamId) throws ValidationException, ServiceException{
		try {
		TeamMemberValidator.validateId(teamId, "team id");
		TeamMemberDAO dao = new TeamMemberDAO();
		return dao.findCaptainRelIdbyTeamId(teamId);
		}catch(ValidationException e) {
	 		e.printStackTrace();
			throw new ValidationException(e.getMessage());
	 	}catch(PersistanceException e) {
	 		e.printStackTrace();
			throw new ServiceException(e.getMessage());
	 	}
	}
	
	public TeamMember findByPlayerId(int playerId) throws ValidationException, ServiceException{
		try {
		TeamMemberValidator.validateId(playerId, "Player id");
		TeamMemberDAO dao = new TeamMemberDAO();
		return dao.findByPlayerId(playerId);
		}catch(ValidationException e) {
	 		e.printStackTrace();
			throw new ValidationException(e.getMessage());
	 	}catch(PersistanceException e) {
	 		e.printStackTrace();
			throw new ServiceException(e.getMessage());
	 	}
	}
	


public void delete(int teamId, int playerId) throws ValidationException, ServiceException{
	try {	
		TeamMemberValidator.validateDelete(teamId, playerId);
		TeamMemberDAO dao = new TeamMemberDAO();
		dao.delete(teamId, playerId);
	}catch(ValidationException e) {
 		e.printStackTrace();
		throw new ValidationException(e.getMessage());
 	}catch(PersistanceException e) {
 		e.printStackTrace();
		throw new ServiceException(e.getMessage());
 	}
			
	}

public Set<PlayerRequestDTO> listAllTeamMemberRequest(int teamId) throws ValidationException, ServiceException{

	Set<PlayerRequestDTO> listOfplayers = new HashSet<>();
	try {
		TeamMemberValidator.validateListAllTeamMemberRequest(teamId);
		TeamMemberDAO dao = new TeamMemberDAO();
		listOfplayers = dao.listAllTeamMemberRequest(teamId);
	}catch(PersistanceException e) {
 		e.printStackTrace();
		throw new ServiceException(e.getMessage());
 	}
	return listOfplayers;
				
	}

public List<TeamRequestDTO> listAllPlayerRequestByPlayerId(int playerId) throws ValidationException, ServiceException{
	
	List<TeamRequestDTO> listOfTeams = new ArrayList<>();
	try {	
		TeamMemberValidator.validateListAllPlayerRequestByPlayerId(playerId);
		TeamMemberDAO dao = new TeamMemberDAO();
		listOfTeams = dao.listAllPlayerRequestByPlayerId(playerId);
	}catch(PersistanceException e) {
 		e.printStackTrace();
		throw new ServiceException(e.getMessage());
 	}
	return listOfTeams;
				
	}

public void deleteRequest(int requestId, int playerId) throws ValidationException, ServiceException{

	try {
		TeamMemberValidator.validateDeleteRequest(requestId,playerId);
		TeamMemberDAO dao = new TeamMemberDAO();
		dao.deleteRequest(requestId,playerId);
	}catch(PersistanceException e) {
 		e.printStackTrace();
		throw new ServiceException(e.getMessage());
 	}
				
}

public void acceptRequest(int requestId, int captainId) throws ValidationException, ServiceException{
	
	
	try {	
		TeamMemberValidator.validateAcceptRequest(requestId,captainId);
		TeamMemberDAO dao = new TeamMemberDAO();
		
		TeamMember teamMember = this.findById(requestId);
		int playerId = teamMember.getUserId();
		
		List<TeamRequestDTO> listOfRequest = this.listAllPlayerRequestByPlayerId(playerId);
		for (TeamRequestDTO request : listOfRequest) {
		    if(request.getRequestId() != requestId && request.getRequestStatus() != 0) {
		    	System.out.println(request.getRequestId() + " "+  playerId);
		    	this.deleteRequest(request.getRequestId(), playerId);
		    }
		}
		dao.acceptRequest(requestId);
		
	}catch(PersistanceException e) {
 		e.printStackTrace();
		throw new ServiceException(e.getMessage());
 	}
				
}


public void switchCaptain(int teamId, int captainId, int newCaptainId) throws ValidationException, ServiceException{
	
	
	try {	
		TeamMemberValidator.validateSwitchCaptain(teamId, captainId, newCaptainId);
		TeamMemberDAO dao = new TeamMemberDAO();
		
		dao.switchCaptain(teamId, captainId, newCaptainId);
		
	}catch(PersistanceException e) {
 		e.printStackTrace();
		throw new ServiceException(e.getMessage());
 	}
				
}

public void rejectRequest(int requestId, int captainId) throws ValidationException, ServiceException{
	
	
	try {	
		TeamMemberValidator.validateRejectRequest(requestId,captainId);
		TeamMemberDAO dao = new TeamMemberDAO();
		
		dao.rejectRequest(requestId);
	}catch(PersistanceException e) {
 		e.printStackTrace();
		throw new ServiceException(e.getMessage());
 	}
				
}

public void exitTeam(int teamId, int playerId) throws ValidationException, ServiceException{
	
	try {
		TeamMemberValidator.validateExitTeam(teamId, playerId);
		TeamMemberDAO dao = new TeamMemberDAO();
		
		dao.exitTeam(teamId, playerId);
	}catch(PersistanceException e) {
 		e.printStackTrace();
		throw new ServiceException(e.getMessage());
 	}
				
}

public void deleteChange(int teamId, int playerId) throws ValidationException, ServiceException{
	try {	
		TeamMemberDAO dao = new TeamMemberDAO();
		dao.deleteChange(teamId, playerId);
	}catch(PersistanceException e) {
 		e.printStackTrace();
		throw new ServiceException(e.getMessage());
 	}
				
	}



}
