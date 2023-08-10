package in.fssa.sportshub;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

import java.time.LocalDate;

import org.junit.jupiter.api.Test;

import in.fssa.sportshub.enumm.Gender;
import in.fssa.sportshub.model.Player;
import in.fssa.sportshub.service.PlayerService;

public class TestCreatePlayer {
	@Test
	public void CreatePlayerWithValidData() throws Exception {
		PlayerService playerService = new PlayerService();
		
		Player player = new Player();
		player.setPhoneNumber(9344655211l);
		player.setUserName("Alagu");
		player.setFirstName("Alagappan");
		player.setLastName("Kumaravel");
		player.setUrl("shssssdsfdsd");
		player.setPassword("Aa!1aaaaa");
		Gender personGender = Gender.MALE;
		player.setGender(personGender);
		player.getAddress().setArea("Aminjikarai");
		player.getAddress().setDistrict("Chennai");
		player.setDateOfBirth(LocalDate.of(2001, 10, 26));
		player.setAbout("I am a good boy");
		
		assertDoesNotThrow(()->{
			playerService.create(player);
		});
	}
	
	
	@Test
	public void UpdatePlayerWithValidData() throws Exception {
		PlayerService playerService = new PlayerService();
		
		Player player = new Player();
		player.setId(1);
		player.setUserName("Alagu");
		player.setFirstName("Alagappan");
		player.setLastName("Kumaravel");
		player.setUrl("shssssdsfdsd");
		player.setPassword("Aa!1aaaaa");
		Gender personGender = Gender.MALE;
		player.setGender(personGender);
		player.getAddress().setArea("ShennoiNagar");// here case should i check
		player.getAddress().setDistrict("Chennai");
		player.setDateOfBirth(LocalDate.of(2001, 10, 26));
		player.setAbout("I am a good batsman");
		
		assertDoesNotThrow(()->{
			playerService.update(player);
		});
		
	}
		
		@Test
		public void DeletePlayerWithValidData() throws Exception {
			PlayerService playerService = new PlayerService();
			assertDoesNotThrow(()->{
				playerService.delete(1);
			});
		}
	
}
