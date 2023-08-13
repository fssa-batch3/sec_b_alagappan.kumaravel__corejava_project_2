package in.fssa.sportshub;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

import org.junit.jupiter.api.Test;

import in.fssa.sportshub.model.Team;
import in.fssa.sportshub.service.TeamService;

public class TestCreateTeam {
	@Test
	public void CreateTeamWithValidData() throws Exception {
		TeamService teamService = new TeamService();
		
		Team team = new Team();

		team.setTeamName("Indians");
		team.setUrl("shssssdsfdsd");
		team.getAddress().setArea("Aminjikarai");
		team.getAddress().setDistrict("Chennai");
		team.setAbout("Playing cricket");
		team.setCreatedBy(2);
		team.setModifiedBy(2);
		assertDoesNotThrow(()->{
			teamService.create(team);
		});
	}
	
	@Test
	public void UpdateTeamWithValidData() throws Exception {
		TeamService teamService = new TeamService();
		
		Team team = new Team();
		team.setId(3);
		team.setTeamName("Rockers");
		team.setUrl("shssssdsfdsd");
		team.getAddress().setArea("Aminjikarai");
		team.getAddress().setDistrict("Chennai");
		team.setAbout("Playing Street cricket");
		team.setModifiedBy(1);
		assertDoesNotThrow(()->{
			teamService.update(team);
		});
	}
	
	@Test
	public void DeleteTeamWithValidData() throws Exception {
		TeamService teamService = new TeamService();
		
		assertDoesNotThrow(()->{
			teamService.delete(3,1);
		});
	}
}
