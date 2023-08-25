package in.fssa.sportshub.service;

import in.fssa.sportshub.dao.TeamMemberDAO;
import in.fssa.sportshub.exception.PersistanceException;
import in.fssa.sportshub.exception.ServiceException;
import in.fssa.sportshub.exception.ValidationException;
import in.fssa.sportshub.model.TeamMember;
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

	public boolean isPlayerCaptain(int id) throws ValidationException, ServiceException{
		try {
		TeamMemberValidator.validateId(id, "Player");
		TeamMemberDAO dao = new TeamMemberDAO();
		return dao.isPlayerCaptain(id);
		}catch(ValidationException e) {
	 		e.printStackTrace();
			throw new ValidationException(e.getMessage());
	 	}catch(PersistanceException e) {
	 		e.printStackTrace();
			throw new ServiceException(e.getMessage());
	 	}
	}
	
	public boolean isPlayerCaptainOfSpecificTeam(int player_id, int team_id) throws ValidationException, ServiceException{
		try {
		TeamMemberValidator.validateId(player_id, "Player");
		TeamMemberValidator.validateId(team_id, "Team");
		TeamMemberDAO dao = new TeamMemberDAO();
		return dao.isPlayerCaptainOfSpecificTeam(player_id, team_id);
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
}
