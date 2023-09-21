package in.fssa.sportshub;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

import org.junit.jupiter.api.Test;

import in.fssa.sportshub.model.MatchRequest;
import in.fssa.sportshub.model.OpponentType;
import in.fssa.sportshub.service.MatchRequestService;

public class TestDeleteMatchRequest {
	@Test
	public void CreateMatchRequestWithValidDataToTeam() throws Exception {
		MatchRequestService matchRequestServ = new MatchRequestService();

		assertDoesNotThrow(()->{
			matchRequestServ.delete(10, 1);
		});
	}
}
