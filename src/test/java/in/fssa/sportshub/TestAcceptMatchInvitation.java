package in.fssa.sportshub;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;
import in.fssa.sportshub.service.RequestResponseService;

public class TestAcceptMatchInvitation {

//	@Test
//	public void acceptMatchInvitationWithValidData() throws Exception {
//		RequestResponseService responseService = new RequestResponseService();
//
//		assertDoesNotThrow(()->{
//			responseService.accept(7, 1);
//		});
//	}
	
	@Test
	public void acceptMatchInvitationWithNotAvailableCaptainRelData() throws Exception {
		RequestResponseService responseService = new RequestResponseService();

		Exception exception = assertThrows(Exception.class, () -> {
			responseService.accept(1, 2);
        });
        
        String exceptedMessage = "Match request id not exist";
		String actualMessage = exception.getMessage();
		System.out.println(actualMessage);
		assertTrue(exceptedMessage.equals(actualMessage));
	}
	
	@Test
	public void acceptMatchInvitationWithInValidCaptainRelData() throws Exception {
		RequestResponseService responseService = new RequestResponseService();

		Exception exception = assertThrows(Exception.class, () -> {
			responseService.accept(1, -2);
        });
        
        String exceptedMessage = "Invalid matchRequestId id";
		String actualMessage = exception.getMessage();
		System.out.println(actualMessage);
		assertTrue(exceptedMessage.equals(actualMessage));
	}
	
	@Test
	public void acceptMatchInvitationWithNotAvailableRequestData() throws Exception {
		RequestResponseService responseService = new RequestResponseService();

		Exception exception = assertThrows(Exception.class, () -> {
			responseService.accept(100, 25);
        });
        
        String exceptedMessage = "Captain relation id not exist";
		String actualMessage = exception.getMessage();
		System.out.println(actualMessage);
		assertTrue(exceptedMessage.equals(actualMessage));
	}
	
	@Test
	public void acceptMatchInvitationWithInValidRequestData() throws Exception {
		RequestResponseService responseService = new RequestResponseService();

		Exception exception = assertThrows(Exception.class, () -> {
			responseService.accept(-1, 25);
        });
        
        String exceptedMessage = "Invalid toTeamCaptainRelationId id";
		String actualMessage = exception.getMessage();
		System.out.println(actualMessage);
		assertTrue(exceptedMessage.equals(actualMessage));
	}
	
	
	
}
