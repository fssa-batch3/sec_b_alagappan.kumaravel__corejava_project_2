package in.fssa.sportshub.service;

import in.fssa.sportshub.dao.TeamDAO;
import in.fssa.sportshub.exception.PersistanceException;
import in.fssa.sportshub.exception.ServiceException;
import in.fssa.sportshub.exception.ValidationException;
import in.fssa.sportshub.model.Address;
import in.fssa.sportshub.model.Team;
import in.fssa.sportshub.model.TeamMember;
import in.fssa.sportshub.util.StringUtil;
import in.fssa.sportshub.validator.TeamValidator;

public class TeamService {
	
public void create(Team team) throws ValidationException, ServiceException{
	try {		
			TeamValidator.validateCreate(team);	

			Address address = team.getAddress();
			AddressService addressService = new AddressService();
			int addressId = addressService.create(address);
			
			team.getAddress().setId(addressId);
			
			TeamDAO teamDAO = new TeamDAO();
			int teamId = teamDAO.create(team);
		
			TeamMember teamMember = new TeamMember();
			teamMember.setUserId(team.getCreatedBy());
			teamMember.setTeamId(teamId);

			TeamMemberService teamMemService = new TeamMemberService();
			teamMemService.create(teamMember);
	}catch(ValidationException e) {
 		e.printStackTrace();
		throw new ValidationException(e.getMessage());
 	}catch(PersistanceException e) {
 		e.printStackTrace();
		throw new ServiceException(e.getMessage());
 	}
	}
	
public boolean checkTeamNameExist(String teamName) throws ValidationException, ServiceException{
		try {
		StringUtil.rejectIfInvalidString(teamName, "TeamName");
		TeamDAO teamDAO = new TeamDAO();
		return teamDAO.nameAlreadyExist(teamName);
		}catch(ValidationException e) {
	 		e.printStackTrace();
			throw new ValidationException(e.getMessage());
	 	}catch(PersistanceException e) {
	 		e.printStackTrace();
			throw new ServiceException(e.getMessage());
	 	}
	}
	
public boolean checkTeamExist(int id) throws ValidationException, ServiceException{
		try {
		TeamValidator.validateId(id, "Team");
		TeamDAO teamDAO = new TeamDAO();
		return teamDAO.checkExistById(id);
		}catch(ValidationException e) {
	 		e.printStackTrace();
			throw new ValidationException(e.getMessage());
	 	}catch(PersistanceException e) {
	 		e.printStackTrace();
			throw new ServiceException(e.getMessage());
	 	}
	}
	
public void update(Team team) throws ValidationException, ServiceException{
	try {
			TeamValidator.validateUpdate(team);		
			Address address = team.getAddress();
			AddressService addressService = new AddressService();
			int addressId = addressService.create(address);
			
			team.getAddress().setId(addressId);
			
			TeamDAO teamDAO = new TeamDAO();
			teamDAO.update(team);
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
		TeamValidator.validateDelete(teamId, playerId);
		TeamDAO teamDAO = new TeamDAO();
		teamDAO.delete(playerId, teamId);
		
		TeamMemberService teamMemServ = new TeamMemberService();
		teamMemServ.delete(teamId, playerId);
		
	}catch(ValidationException e) {
 		e.printStackTrace();
		throw new ValidationException(e.getMessage());
 	}catch(PersistanceException e) {
 		e.printStackTrace();
		throw new ServiceException(e.getMessage());
 	}
		
}

public void changeDelete(int teamId, int playerId) throws ValidationException, ServiceException{
	try {
		TeamValidator.validateId(teamId, "team");
		TeamValidator.validateId(playerId, "player");
		TeamDAO teamDAO = new TeamDAO();
		teamDAO.delete(playerId, teamId);
		
		TeamMemberService teamMemServ = new TeamMemberService();
		teamMemServ.delete(teamId, playerId);
		
	}catch(ValidationException e) {
 		e.printStackTrace();
		throw new ValidationException(e.getMessage());
 	}catch(PersistanceException e) {
 		e.printStackTrace();
		throw new ServiceException(e.getMessage());
 	}
		
}



}
