package in.fssa.sportshub;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

import org.junit.jupiter.api.Test;

import in.fssa.sportshub.service.TeamMemberService;

public class TestListOfTeamMemberRequest {

	@Test
	public void getlistAllTeamMemberRequest() throws Exception {
		TeamMemberService teamMemberService = new TeamMemberService();
		
		assertDoesNotThrow(()->{
			teamMemberService.listAllTeamMemberRequest(2);
		});
	}
}
