package in.fssa.sportshub;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

import org.junit.jupiter.api.Test;

import in.fssa.sportshub.model.TeamMember;
import in.fssa.sportshub.service.TeamMemberService;

public class TestCreateTeamMemberRequest {
	
	@Test
	public void createTeamMemberRequestWithValidData() throws Exception {
		TeamMemberService teamMemberService = new TeamMemberService();
		
		TeamMember teamMember = new TeamMember();

		teamMember.setTeamId(2);
		teamMember.setUserId(4);
		assertDoesNotThrow(()->{
			teamMemberService.createJoinRequest(teamMember);
		});
	}
	
	@Test
	public void getlistAllTeamMemberRequest() throws Exception {
		TeamMemberService teamMemberService = new TeamMemberService();
		
		assertDoesNotThrow(()->{
			teamMemberService.listAllTeamMemberRequest(2);
		});
	}
	
	
	
	
}
