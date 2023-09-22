package in.fssa.sportshub.service;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

import in.fssa.sportshub.dao.TeamMemberDAO;
import in.fssa.sportshub.exception.PersistanceException;
import in.fssa.sportshub.exception.ServiceException;
import in.fssa.sportshub.exception.ValidationException;
import in.fssa.sportshub.model.MatchRequestDTO;
import in.fssa.sportshub.model.Player;
import in.fssa.sportshub.model.PlayerRequestDTO;
import in.fssa.sportshub.model.TeamMember;
import in.fssa.sportshub.model.TeamRequestDTO;
import in.fssa.sportshub.validator.PlayerValidator;
import in.fssa.sportshub.validator.TeamMemberValidator;
import in.fssa.sportshub.validator.TeamValidator;

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
	boolean checkTeamNameExist = TeamValidator.checkTeamExist(teamId);
	if(!checkTeamNameExist) {
		throw new ValidationException("Team not exist");
	}
	
	Set<PlayerRequestDTO> listOfplayers = new HashSet<>();
	try {	
		TeamMemberDAO dao = new TeamMemberDAO();
		listOfplayers = dao.listAllTeamMemberRequest(teamId);
	}catch(PersistanceException e) {
 		e.printStackTrace();
		throw new ServiceException(e.getMessage());
 	}
	return listOfplayers;
				
	}

public Set<TeamRequestDTO> listAllPlayerRequestByPlayerId(int playerId) throws ValidationException, ServiceException{
	boolean checkPlayerExist = PlayerValidator.playerExist(playerId);
	if(!checkPlayerExist){
		throw new ValidationException("Player not exist");
	}
	
	Set<TeamRequestDTO> listOfTeams = new HashSet<>();
	try {	
		TeamMemberDAO dao = new TeamMemberDAO();
		listOfTeams = dao.listAllPlayerRequestByPlayerId(playerId);
	}catch(PersistanceException e) {
 		e.printStackTrace();
		throw new ServiceException(e.getMessage());
 	}
	return listOfTeams;
				
	}

public void deleteRequest(int requestId, int playerId) throws ValidationException, ServiceException{
	boolean checkPlayerExist = PlayerValidator.playerExist(playerId);
	if(!checkPlayerExist){
		throw new ValidationException("Player not exist");
	}
	
	TeamMember teamMembervalidate = this.findById(requestId);
	if(teamMembervalidate == null) {
		throw new ValidationException("Request id not found");
	}
	
	
	try {	
		TeamMemberDAO dao = new TeamMemberDAO();
		/* dao.deleteRequest(requestId, playerId); */ // validation should done
		
		dao.deleteRequest(requestId,playerId);
	}catch(PersistanceException e) {
 		e.printStackTrace();
		throw new ServiceException(e.getMessage());
 	}
				
}

public void acceptRequest(int requestId, int captainId) throws ValidationException, ServiceException{
	boolean checkPlayerExist = PlayerValidator.playerExist(captainId);
	if(!checkPlayerExist){
		throw new ValidationException("Player not exist");
	}
	boolean isCaptain = TeamMemberValidator.isPlayerCaptain(captainId);
	if(!isCaptain){
		throw new ValidationException("Player not a captain of team");
	}
	
	TeamMember teamMembervalidate = this.findById(requestId);
	if(teamMembervalidate == null) {
		throw new ValidationException("Request id not found");
	}
	
	boolean isCaptain1 = TeamMemberValidator.isPlayerCaptainOfSpecificTeam(captainId, teamMembervalidate.getTeamId());
	if(!isCaptain1){
		throw new ValidationException("player not a captian of this team");
	}
	
	try {	
		TeamMemberDAO dao = new TeamMemberDAO();
		
		TeamMember teamMember = this.findById(requestId);
		int playerId = teamMember.getUserId();
		
		Set<TeamRequestDTO> listOfRequest = this.listAllPlayerRequestByPlayerId(playerId);
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

public void rejectRequest(int requestId, int captainId) throws ValidationException, ServiceException{
	boolean checkPlayerExist = PlayerValidator.playerExist(captainId);
	if(!checkPlayerExist){
		throw new ValidationException("Player not exist");
	}
	boolean isCaptain = TeamMemberValidator.isPlayerCaptain(captainId);
	if(!isCaptain){
		throw new ValidationException("Player not a captain of team");
	}
	
	TeamMember teamMembervalidate = this.findById(requestId);
	if(teamMembervalidate == null) {
		throw new ValidationException("Request id not found");
	}
	
	boolean isCaptain1 = TeamMemberValidator.isPlayerCaptainOfSpecificTeam(captainId, teamMembervalidate.getTeamId());
	if(!isCaptain1){
		throw new ValidationException("player not a captian of this team");
	}
	
	try {	
		TeamMemberDAO dao = new TeamMemberDAO();
		/* dao.deleteRequest(requestId, playerId); */ // validation should done
		
		dao.rejectRequest(requestId);
	}catch(PersistanceException e) {
 		e.printStackTrace();
		throw new ServiceException(e.getMessage());
 	}
				
}

public void exitTeam(int teamId, int playerId) throws ValidationException, ServiceException{
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
	
	try {	
		TeamMemberDAO dao = new TeamMemberDAO();
		/* dao.deleteRequest(requestId, playerId); */ // validation should done
		
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
