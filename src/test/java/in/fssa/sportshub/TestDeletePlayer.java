package in.fssa.sportshub;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Order;
import in.fssa.sportshub.service.PlayerService;
import in.fssa.sportshub.service.TeamService;

public class TestDeletePlayer {
	
	@Test
	@Order(1)
	public void changeIsActiveFalseTotrue() throws Exception {
		
		PlayerService playerService = new PlayerService();
		assertDoesNotThrow(()->{
			playerService.changeDelete(4);
		});
	}
	
	@Test
	@Order(2)
	public void deletePlayerWithValidData() throws Exception {
		PlayerService playerService = new PlayerService();
		assertDoesNotThrow(()->{
			playerService.delete(4);
		});
	}
	
	@Test
	public void deletePlayerWithInvalidPlayerId() throws Exception {
		PlayerService playerService = new PlayerService();
		
		Exception exception = assertThrows(Exception.class, () -> {
            playerService.delete(-1);
        });
        
        String exceptedMessage = "Invalid Player id";
		String actualMessage = exception.getMessage();
		System.out.println(actualMessage);
		assertTrue(exceptedMessage.equals(actualMessage));
	}
	
	@Test
	public void deletePlayerWithNotExistPlayerId() throws Exception {
		PlayerService playerService = new PlayerService();
		
		Exception exception = assertThrows(Exception.class, () -> {
            playerService.delete(100);
        });
        
        String exceptedMessage = "Player not exist";
		String actualMessage = exception.getMessage();
		assertTrue(exceptedMessage.equals(actualMessage));
	}
	
	@Test
	public void deleteTeamCaptainPlayer() throws Exception {
		PlayerService playerService = new PlayerService();
		Exception exception = assertThrows(Exception.class, () -> {
            playerService.delete(1);
        });
        
        String exceptedMessage = "Player is captain of a team";
		String actualMessage = exception.getMessage();
		assertTrue(exceptedMessage.equals(actualMessage));
	}
}
