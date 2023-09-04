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
