package in.fssa.sportshub;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import in.fssa.sportshub.model.TeamMember;
import in.fssa.sportshub.service.TeamMemberService;

public class TestCreateTeamMemberRequest {
	
	@Test
	public void createTeamMemberRequestWithValidData() throws Exception {
		TeamMemberService teamMemberService = new TeamMemberService();
		
		TeamMember teamMember = new TeamMember();

		teamMember.setTeamId(2);
		teamMember.setUserId(4);//here should change new player data
		assertDoesNotThrow(()->{
			teamMemberService.createJoinRequest(teamMember);
		});
	}
	
	@Test
	public void createTeamMemberRequestWithInValidUserData() throws Exception {
		TeamMemberService teamMemberService = new TeamMemberService();
		
		TeamMember teamMember = new TeamMember();

		teamMember.setTeamId(2);
		teamMember.setUserId(-4);
		Exception exception = assertThrows(Exception.class, () -> {
			teamMemberService.createJoinRequest(teamMember);
        });
        
        String exceptedMessage = "Invalid Player id";
		String actualMessage = exception.getMessage();
		System.out.println(actualMessage);
		assertTrue(exceptedMessage.equals(actualMessage));
	}
	
	@Test
	public void createTeamMemberRequestWithNotExistUserData() throws Exception {
		TeamMemberService teamMemberService = new TeamMemberService();
		
		TeamMember teamMember = new TeamMember();

		teamMember.setTeamId(2);
		teamMember.setUserId(100);
		Exception exception = assertThrows(Exception.class, () -> {
			teamMemberService.createJoinRequest(teamMember);
        });
        
        String exceptedMessage = "Player not exist";
		String actualMessage = exception.getMessage();
		System.out.println(actualMessage);
		assertTrue(exceptedMessage.equals(actualMessage));
	}
	
	@Test
	public void createTeamMemberRequestWithAlreadyCaptainUserData() throws Exception {
		TeamMemberService teamMemberService = new TeamMemberService();
		
		TeamMember teamMember = new TeamMember();

		teamMember.setTeamId(2);
		teamMember.setUserId(1);
		Exception exception = assertThrows(Exception.class, () -> {
			teamMemberService.createJoinRequest(teamMember);
        });
        
        String exceptedMessage = "Player already a captain in team";
		String actualMessage = exception.getMessage();
		System.out.println(actualMessage);
		assertTrue(exceptedMessage.equals(actualMessage));
	}
	
	
	@Test
	public void createTeamMemberRequestWithInvalidTeamData() throws Exception {
		TeamMemberService teamMemberService = new TeamMemberService();
		
		TeamMember teamMember = new TeamMember();

		teamMember.setTeamId(-2);
		teamMember.setUserId(13);
		Exception exception = assertThrows(Exception.class, () -> {
			teamMemberService.createJoinRequest(teamMember);
        });
        
        String exceptedMessage = "Invalid Team id";
		String actualMessage = exception.getMessage();
		System.out.println(actualMessage);
		assertTrue(exceptedMessage.equals(actualMessage));
	}
	
	@Test
	public void createTeamMemberRequestWithNotExistTeamData() throws Exception {
		TeamMemberService teamMemberService = new TeamMemberService();
		
		TeamMember teamMember = new TeamMember();

		teamMember.setTeamId(100);
		teamMember.setUserId(13);
		Exception exception = assertThrows(Exception.class, () -> {
			teamMemberService.createJoinRequest(teamMember);
        });
        
        String exceptedMessage = "Team not exist";
		String actualMessage = exception.getMessage();
		System.out.println(actualMessage);
		assertTrue(exceptedMessage.equals(actualMessage));
	}

	
	
	
	
}
