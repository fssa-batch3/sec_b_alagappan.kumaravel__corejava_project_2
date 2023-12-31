package in.fssa.sportshub.service;

import java.util.List;
import java.util.Set;

import in.fssa.sportshub.dao.AddressDAO;
import in.fssa.sportshub.dao.TeamDAO;
import in.fssa.sportshub.exception.PersistanceException;
import in.fssa.sportshub.exception.ServiceException;
import in.fssa.sportshub.exception.ValidationException;
import in.fssa.sportshub.model.Address;
import in.fssa.sportshub.model.Team;
import in.fssa.sportshub.model.TeamDetailDTO;
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

// validation yet to be done
public Team findByCaptainId(int captainId) throws ValidationException, ServiceException{
	Team team;
	try {
		TeamValidator.validateId(captainId, "captainId");
		
		TeamMemberService teamMemberService = new TeamMemberService();
		
		TeamMember teamMember = teamMemberService.findByCaptainId(captainId);
		
		 team = this.findById(teamMember.getTeamId());
		 
		 AddressService addressService = new AddressService();
		 
		 Address address = addressService.findById(team.getAddress().getId()) ;
		 
		 team.setAddress(address);
		
	}catch(ValidationException e) {
 		e.printStackTrace();
		throw new ValidationException(e.getMessage());
 	}
	return team;	
}

//validation yet to be done
public TeamDetailDTO findById(int teamId) throws ValidationException, ServiceException{
	TeamDetailDTO team;
	try {
		TeamValidator.validateId(teamId, "team");
		TeamDAO teamDAO = new TeamDAO();
		 team = teamDAO.findById(teamId);
		
	}catch(ValidationException e) {
 		e.printStackTrace();
		throw new ValidationException(e.getMessage());
 	}catch(PersistanceException e) {
 		e.printStackTrace();
		throw new ServiceException(e.getMessage());
 	}
	return team;
		
}


public List<TeamDetailDTO> getAllTeam(int pageSize, int lastTeamId) throws ServiceException{
	 try {
		TeamDAO teamDAO = new TeamDAO();
		return teamDAO.getAll(pageSize,lastTeamId);
	}catch(PersistanceException e) {
		e.printStackTrace();
		throw new ServiceException(e.getMessage());
	}
}

public Set<TeamDetailDTO> getRandomTeam(int areaId) throws ServiceException{
	 try {
		TeamDAO teamDAO = new TeamDAO();
		return teamDAO.getRandomTeam(areaId);
	}catch(PersistanceException e) {
		e.printStackTrace();
		throw new ServiceException(e.getMessage());
	}
}

public List<TeamDetailDTO> allTeamByPlayerId(int playerId) throws ServiceException{
	 try {
		TeamDAO teamDAO = new TeamDAO();
		return teamDAO.allTeamByPlayerId(playerId);
	}catch(PersistanceException e) {
		e.printStackTrace();
		throw new ServiceException(e.getMessage());
	}
}

public List<TeamDetailDTO> getOpenForPlayerTeamList(int pageSize, int lastTeamId) throws ServiceException{
	 try {
		TeamDAO teamDAO = new TeamDAO();
		return teamDAO.getOpenForPlayerTeamList(pageSize,lastTeamId);
	}catch(PersistanceException e) {
		e.printStackTrace();
		throw new ServiceException(e.getMessage());
	}
}

public List<TeamDetailDTO> SearchTeamForPlayerTeamListByString(String input, int lastTeamId) throws ServiceException{
	 try {
		TeamDAO teamDAO = new TeamDAO();
		return teamDAO.SearchTeamForPlayerTeamListByString(input,lastTeamId);
	}catch(PersistanceException e) {
		e.printStackTrace();
		throw new ServiceException(e.getMessage());
	}
}

public List<TeamDetailDTO> SearchTeamByString(String input, int lastTeamId) throws ServiceException{
	 try {
		TeamDAO teamDAO = new TeamDAO();
		return teamDAO.SearchTeamByString(input,lastTeamId);
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
		teamDAO.deleteChange(playerId, teamId);
		
		TeamMemberService teamMemServ = new TeamMemberService();
		teamMemServ.deleteChange(teamId, playerId);
		
	}catch(ValidationException e) {
 		e.printStackTrace();
		throw new ValidationException(e.getMessage());
 	}catch(PersistanceException e) {
 		e.printStackTrace();
		throw new ServiceException(e.getMessage());
 	}
		
}

}
