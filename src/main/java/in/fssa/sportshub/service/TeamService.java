package in.fssa.sportshub.service;

import in.fssa.sportshub.dao.TeamDAO;
import in.fssa.sportshub.model.Address;
import in.fssa.sportshub.model.Team;
import in.fssa.sportshub.model.TeamMember;
import in.fssa.sportshub.util.StringUtil;
import in.fssa.sportshub.validator.AddressValidator;
import in.fssa.sportshub.validator.PlayerValidator;
import in.fssa.sportshub.validator.TeamValidator;

public class TeamService {
	
public void create(Team team) throws Exception{
		
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
	
	public boolean checkTeamNameExist(String teamName) throws Exception{
		StringUtil.rejectIfInvalidString(teamName, "TeamName");
		TeamDAO dao = new TeamDAO();
		return dao.teamNameAlreadyExist(teamName);
	}
	
	public boolean checkTeamExist(int id) throws Exception{
		TeamValidator.validateId(id, "Team");
		TeamDAO dao = new TeamDAO();
		return dao.teamAlreadyExist(id);
	}
	
public void update(Team team) throws Exception{
		
		TeamValidator.validatePartial(team);
		TeamValidator.validateId(team.getModifiedBy(), "Modify Player");
		
		PlayerService playerService = new PlayerService();
		boolean checkPlayerExist = playerService.playerExist(team.getModifiedBy());
		if(checkPlayerExist){
			throw new Exception("Player not exist");
		}
		
		boolean checkTeamExist = this.checkTeamExist(team.getId());
		if(!checkTeamExist){
			throw new Exception("Team not exist");
		}
		
		TeamMemberService teamMemService = new TeamMemberService();
		boolean isCaptain = teamMemService.isPlayerCaptainOfSpecificTeam(team.getModifiedBy(), team.getId());
		if(!isCaptain){
			throw new Exception("player not a captian of this team");
		}
			
			Address address = team.getAddress();
			AddressService addressService = new AddressService();
			int addressId = addressService.create(address);
			
			team.getAddress().setId(addressId);
			
			TeamDAO teamDao = new TeamDAO();
			teamDao.update(team);
		
			
	}

//	public boolean playerExist(int id) throws Exception{
//		PlayerValidator.validateId(id);
//		PlayerDAO dao = new PlayerDAO();
//		return dao.playerExist(id);
//	}
	
//	public void update(Player player) throws Exception{
//		
//		PlayerValidator.validatePartial(player);
//		AddressValidator.validate(player.getAddress());
//		boolean checkPlayerExist = this.playerExist(player.getId());
//		if(checkPlayerExist){
//			
//			Address address = player.getAddress();
//			AddressService addressService = new AddressService();
//			int addressId = addressService.create(address);
//			
//			player.getAddress().setId(addressId);
//			
//			PlayerDAO playerDao = new PlayerDAO();
//			playerDao.update(player);	
//			
//		}else {
//			throw new Exception("Player not exist");
//		}
//	}
//	
//	public void delete(int id) throws Exception{
//		
//		PlayerValidator.validateId(id);;
//		boolean checkPlayerExist = this.playerExist(id);
//		if(checkPlayerExist){
//			
//			PlayerDAO playerDao = new PlayerDAO();
//			playerDao.delete(id);	
//			
//		}else {
//			throw new Exception("Player not exist");
//		}
//	}
}
