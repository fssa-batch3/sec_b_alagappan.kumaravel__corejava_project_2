package in.fssa.sportshub;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import in.fssa.sportshub.service.PlayerService;

public class TestLogInPlayer {


	@Test
	public void logInPlayerWithValidInput() throws Exception {
		PlayerService playerService = new PlayerService();
		
		assertDoesNotThrow(()->{
			playerService.logIn(9344655211l, "Aa!1aaaa");
		});
	}
	
	@Test
	public void logInPlayerWithInValidPhonenumberInput() throws Exception {
		PlayerService playerService = new PlayerService();
		
		Exception exception = assertThrows(Exception.class, () -> {
			playerService.logIn(5000000000l, "Aa!1aaaa");
        });
        
        String exceptedMessage = "Invalid phone number";
		String actualMessage = exception.getMessage();
		System.out.println(actualMessage);
		assertTrue(exceptedMessage.equals(actualMessage));
	}
	
	@Test
	public void logInPlayerWithInValidPasswordInput() throws Exception {
		PlayerService playerService = new PlayerService();
		
		Exception exception = assertThrows(Exception.class, () -> {
			playerService.logIn(9344655211l, "Aaasaaaa");
        });
        
        String exceptedMessage = "Invalid password";
		String actualMessage = exception.getMessage();
		System.out.println(actualMessage);
		assertTrue(exceptedMessage.equals(actualMessage));
	}
	
	@Test
	public void logInPlayerWithInValidInput() throws Exception {
		PlayerService playerService = new PlayerService();
		
		Exception exception = assertThrows(Exception.class, () -> {
			playerService.logIn(9344655211l, "Aa!1aaaaaaa");
        });
        
        String exceptedMessage = "Player not found";
		String actualMessage = exception.getMessage();
		System.out.println(actualMessage);
		assertTrue(exceptedMessage.equals(actualMessage));
	}
}
