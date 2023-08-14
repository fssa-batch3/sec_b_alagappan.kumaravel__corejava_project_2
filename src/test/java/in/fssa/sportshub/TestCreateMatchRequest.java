package in.fssa.sportshub;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

import org.junit.jupiter.api.Test;

import in.fssa.sportshub.model.MatchRequest;
import in.fssa.sportshub.service.MatchRequestService;

public class TestCreateMatchRequest {
	@Test
	public void CreateMatchRequestWithValidDataToTeam() throws Exception {
		MatchRequestService matchRequestServ = new MatchRequestService();
		MatchRequest matchRequest = new MatchRequest();
		
		matchRequest.setCreatedBy(1);
		matchRequest.setToTeam(6);
		matchRequest.setTypeOfMatch(1);
		matchRequest.setMembers(10);
		matchRequest.setMembersAgeFrom(19);
		matchRequest.setMembersAgeTo(22);
        LocalDateTime currentDateTime = LocalDateTime.now();
        long amountToAdd = 3; 
        ChronoUnit unit = ChronoUnit.DAYS;
        LocalDateTime futureDateTime = currentDateTime.plus(amountToAdd, unit);
		matchRequest.setMatchTime(futureDateTime);
		matchRequest.setLocation("Shennoi nagar ground");
		matchRequest.setInformation("100 rs betting");
		assertDoesNotThrow(()->{
			matchRequestServ.create(matchRequest, 1);
		});
	}
	
	
	@Test
	public void CreateMatchRequestWithValidDataToArea() throws Exception {
		MatchRequestService matchRequestServ = new MatchRequestService();
		MatchRequest matchRequest = new MatchRequest();
		
		matchRequest.setCreatedBy(1);
		matchRequest.setAddressId(4);
		matchRequest.setTypeOfMatch(1);
		matchRequest.setMembers(10);
		matchRequest.setMembersAgeFrom(19);
		matchRequest.setMembersAgeTo(22);
        LocalDateTime currentDateTime = LocalDateTime.now();
        long amountToAdd = 3; // Change this to the desired amount
        ChronoUnit unit = ChronoUnit.DAYS; // Change this to the desired unit

        LocalDateTime futureDateTime = currentDateTime.plus(amountToAdd, unit);

		matchRequest.setMatchTime(futureDateTime);
		matchRequest.setLocation("Shennoi nagar ground");
		matchRequest.setInformation("100 rs betting");
		assertDoesNotThrow(()->{
			matchRequestServ.create(matchRequest, 1);
		});
	}
	@Test
	public void GetAllOpenMAtchRequest() throws Exception {
		MatchRequestService matchRequestServ = new MatchRequestService();
		matchRequestServ.listAllOpenRequest();
	}
}
