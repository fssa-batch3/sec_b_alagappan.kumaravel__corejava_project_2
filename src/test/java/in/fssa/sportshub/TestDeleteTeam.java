package in.fssa.sportshub;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import in.fssa.sportshub.model.Team;
import in.fssa.sportshub.service.TeamService;

public class TestDeleteTeam {
	
	@Test
	public void deleteTeamWithValidData() throws Exception {
		TeamService teamService = new TeamService();
		
		assertDoesNotThrow(()->{
			teamService.delete(7,3);
		});
	}
	
	@Test
	public void deleteTeamWithInvalidPlayerId() throws Exception {
		TeamService teamService = new TeamService();
		
		Exception exception = assertThrows(Exception.class, () -> {
			teamService.delete(7,-1);
        });
        
        String exceptedMessage = "Invalid player id";
		String actualMessage = exception.getMessage();
		assertTrue(exceptedMessage.equals(actualMessage));
	}
	
	@Test
	public void deleteTeamWithNotExistingPlayer() throws Exception {
		TeamService teamService = new TeamService();
		
		Exception exception = assertThrows(Exception.class, () -> {
			teamService.delete(7,100);
        });
        
        String exceptedMessage = "Player not exist";
		String actualMessage = exception.getMessage();
		assertTrue(exceptedMessage.equals(actualMessage));
	}
	
	@Test
	public void deleteTeamWithInvalidTeamId() throws Exception {
		TeamService teamService = new TeamService();
		
		Exception exception = assertThrows(Exception.class, () -> {
			teamService.delete(-1,3);
        });
        
        String exceptedMessage = "Invalid team id";
		String actualMessage = exception.getMessage();
		assertTrue(exceptedMessage.equals(actualMessage));
	}
	
	@Test
	public void deleteTeamWithNotExistingTeam() throws Exception {
		TeamService teamService = new TeamService();
		

		Exception exception = assertThrows(Exception.class, () -> {
			teamService.delete(100,3);
        });
        
        String exceptedMessage = "Team not exist";
		String actualMessage = exception.getMessage();
		assertTrue(exceptedMessage.equals(actualMessage));
	}
	
	@Test
	public void deleteTeamWithNotCaptainOfTeam() throws Exception {
		TeamService teamService = new TeamService();
		
		Exception exception = assertThrows(Exception.class, () -> {
			teamService.delete(7,2);
        });
        
        String exceptedMessage = "player not a captian of this team";
		String actualMessage = exception.getMessage();
		assertTrue(exceptedMessage.equals(actualMessage));
	}
}
