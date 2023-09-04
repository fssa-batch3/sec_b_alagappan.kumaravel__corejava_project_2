package in.fssa.sportshub;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

import org.junit.jupiter.api.Test;
import in.fssa.sportshub.service.RequestResponseService;

public class TestAcceptInvitation {

	@Test
	public void acceptMatchInvitationWithValidData() throws Exception {
		RequestResponseService responseService = new RequestResponseService();

		assertDoesNotThrow(()->{
			responseService.accept(7, 1);
		});
	}
}
