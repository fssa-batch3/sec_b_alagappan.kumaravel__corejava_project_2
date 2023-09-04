package in.fssa.sportshub;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;


import org.junit.jupiter.api.Test;

import in.fssa.sportshub.service.MatchRequestService;

public class TestGetAllMyMatchRequest {
	@Test
	public void GetAllMyMatchRequestWithValidData() throws Exception {
		MatchRequestService matchRequestServ = new MatchRequestService();
		assertDoesNotThrow(()->{
			matchRequestServ.getAllMyMatchRequest(4,4, 1);
		});
	}
	
	@Test
	public void listOfMyMatchInvitationWithValidData() throws Exception {
		MatchRequestService matchRequestServ = new MatchRequestService();
		assertDoesNotThrow(()->{
			matchRequestServ.listOfMyMatchInvitation(1);
		});
	}
}
