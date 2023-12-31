package in.fssa.sportshub;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import in.fssa.sportshub.service.RequestResponseService;

public class TestRejectMatchInvitation {

	@Test
	public void rejectMatchInvitationWithValidData() throws Exception {
		RequestResponseService responseService = new RequestResponseService();

		assertDoesNotThrow(()->{
			responseService.reject(7, 2);// we need correct data
		});
	}
	
	@Test
	public void rejectMatchInvitationWithNotAvailableCaptainRelData() throws Exception {
		RequestResponseService responseService = new RequestResponseService();

		Exception exception = assertThrows(Exception.class, () -> {
			responseService.reject(1, 2);
        });
        
        String exceptedMessage = "Match request id not exist";
		String actualMessage = exception.getMessage();
		System.out.println(actualMessage);
		assertTrue(exceptedMessage.equals(actualMessage));
	}
	
	@Test
	public void rejectMatchInvitationWithInValidCaptainRelData() throws Exception {
		RequestResponseService responseService = new RequestResponseService();

		Exception exception = assertThrows(Exception.class, () -> {
			responseService.reject(1, -2);
        });
        
        String exceptedMessage = "Invalid matchRequestId id";
		String actualMessage = exception.getMessage();
		System.out.println(actualMessage);
		assertTrue(exceptedMessage.equals(actualMessage));
	}
	
	@Test
	public void rejectMatchInvitationWithNotAvailableRequestData() throws Exception {
		RequestResponseService responseService = new RequestResponseService();

		Exception exception = assertThrows(Exception.class, () -> {
			responseService.reject(100, 25);
        });
        
        String exceptedMessage = "Captain relation id not exist";
		String actualMessage = exception.getMessage();
		System.out.println(actualMessage);
		assertTrue(exceptedMessage.equals(actualMessage));
	}
	
	@Test
	public void rejectMatchInvitationWithInValidRequestData() throws Exception {
		RequestResponseService responseService = new RequestResponseService();

		Exception exception = assertThrows(Exception.class, () -> {
			responseService.reject(-1, 25);
        });
        
        String exceptedMessage = "Invalid toTeamCaptainRelationId id";
		String actualMessage = exception.getMessage();
		System.out.println(actualMessage);
		assertTrue(exceptedMessage.equals(actualMessage));
	}
	
	
}
