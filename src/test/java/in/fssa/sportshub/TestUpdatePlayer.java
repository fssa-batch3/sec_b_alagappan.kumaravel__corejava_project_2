package in.fssa.sportshub;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalDate;

import org.junit.jupiter.api.Test;

import in.fssa.sportshub.enumm.Gender;
import in.fssa.sportshub.model.Player;
import in.fssa.sportshub.service.PlayerService;

public class TestUpdatePlayer {
	
	@Test
	public void updatePlayerWithValidData() throws Exception {
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
    public void updatePlayerWithNullValues() {
        PlayerService playerService = new PlayerService();

        Exception exception = assertThrows(Exception.class, () -> {
            playerService.update(null);
        });
        
        String exceptedMessage = "Invalid player input";
		String actualMessage = exception.getMessage();
		assertTrue(exceptedMessage.equals(actualMessage));
    }
	
	@Test
	public void updatePlayerWithInvalidPlayerId() throws Exception {
		PlayerService playerService = new PlayerService();
		
		Player player = new Player();
		player.setId(-1);
		player.setUserName("Pranven");
		player.setFirstName("Praveen");
		player.setLastName("kumar");
		player.setUrl("shssssdsfdsdfvdfgvdfd");
		player.setPassword("Aa!1aaaaa");
		Gender personGender = Gender.MALE;
		player.setGender(personGender);
		player.getAddress().setArea("Aminjikarai");
		player.getAddress().setDistrict("Chennai");
		player.setDateOfBirth(LocalDate.of(2002, 11, 26));
		player.setAbout("I am a good boy");
		
		Exception exception = assertThrows(Exception.class, () -> {
            playerService.update(player);
        });
        
        String exceptedMessage = "Invalid Player id";
		String actualMessage = exception.getMessage();
		assertTrue(exceptedMessage.equals(actualMessage));
	}
	
	@Test
	public void updatePlayerWithNotExistPlayerId() throws Exception {
		PlayerService playerService = new PlayerService();
		
		Player player = new Player();
		player.setId(100);
		player.setUserName("Pranven");
		player.setFirstName("Praveen");
		player.setLastName("kumar");
		player.setUrl("shssssdsfdsdfvdfgvdfd");
		player.setPassword("Aa!1aaaaa");
		Gender personGender = Gender.MALE;
		player.setGender(personGender);
		player.getAddress().setArea("Aminjikarai");
		player.getAddress().setDistrict("Chennai");
		player.setDateOfBirth(LocalDate.of(2002, 11, 26));
		player.setAbout("I am a good boy");
		
		Exception exception = assertThrows(Exception.class, () -> {
            playerService.update(player);
        });
        
        String exceptedMessage = "Player not exist";
		String actualMessage = exception.getMessage();
		assertTrue(exceptedMessage.equals(actualMessage));
	}
	
	@Test
	public void updatePlayerWithUserNameValueNull() throws Exception {
		PlayerService playerService = new PlayerService();
		
		Player player = new Player();
		player.setId(2);
		player.setUserName(null);
		player.setFirstName("Praveen");
		player.setLastName("kumar");
		player.setUrl("shssssdsfdsdfvdfgvdfd");
		player.setPassword("Aa!1aaaaa");
		Gender personGender = Gender.MALE;
		player.setGender(personGender);
		player.getAddress().setArea("Aminjikarai");
		player.getAddress().setDistrict("Chennai");
		player.setDateOfBirth(LocalDate.of(2002, 11, 26));
		player.setAbout("I am a good boy");
		
		Exception exception = assertThrows(Exception.class, () -> {
            playerService.update(player);
        });
        
        String exceptedMessage = "User name can't be null or empty";
		String actualMessage = exception.getMessage();
		assertTrue(exceptedMessage.equals(actualMessage));
	}
	
	@Test
	public void updatePlayerWithUserNameValueEmpty() throws Exception {
		PlayerService playerService = new PlayerService();
		
		Player player = new Player();
		player.setId(2);
		player.setUserName("  ");
		player.setFirstName("Praveen");
		player.setLastName("kumar");
		player.setUrl("shssssdsfdsdfvdfgvdfd");
		player.setPassword("Aa!1aaaaa");
		Gender personGender = Gender.MALE;
		player.setGender(personGender);
		player.getAddress().setArea("Aminjikarai");
		player.getAddress().setDistrict("Chennai");
		player.setDateOfBirth(LocalDate.of(2002, 11, 26));
		player.setAbout("I am a good boy");
		
		Exception exception = assertThrows(Exception.class, () -> {
            playerService.update(player);
        });
        
        String exceptedMessage = "User name can't be null or empty";
		String actualMessage = exception.getMessage();
		assertTrue(exceptedMessage.equals(actualMessage));
	}
	
	@Test
	public void updatePlayerWithLessUserNameCharacterLength() throws Exception {
		PlayerService playerService = new PlayerService();
		
		Player player = new Player();
		player.setId(2);
		player.setUserName("assd");
		player.setFirstName("Praveen");
		player.setLastName("kumar");
		player.setUrl("shssssdsfdsdfvdfgvdfd");
		player.setPassword("Aa!1aaaaa");
		Gender personGender = Gender.MALE;
		player.setGender(personGender);
		player.getAddress().setArea("Aminjikarai");
		player.getAddress().setDistrict("Chennai");
		player.setDateOfBirth(LocalDate.of(2002, 11, 26));
		player.setAbout("I am a good boy");
		
		Exception exception = assertThrows(Exception.class, () -> {
            playerService.update(player);
        });
        
        String exceptedMessage = "User name length does not match pattern";
		String actualMessage = exception.getMessage();
		assertTrue(exceptedMessage.equals(actualMessage));
	}
	
	@Test
	public void updatePlayerWithMoreUserNameCharacterLength() throws Exception {
		PlayerService playerService = new PlayerService();
		
		Player player = new Player();
		player.setId(2);
		player.setUserName("asddaskbafkjbadkjbfkjdbdkdnb");
		player.setFirstName("Praveen");
		player.setLastName("kumar");
		player.setUrl("shssssdsfdsdfvdfgvdfd");
		player.setPassword("Aa!1aaaaa");
		Gender personGender = Gender.MALE;
		player.setGender(personGender);
		player.getAddress().setArea("Aminjikarai");
		player.getAddress().setDistrict("Chennai");
		player.setDateOfBirth(LocalDate.of(2002, 11, 26));
		player.setAbout("I am a good boy");
		
		Exception exception = assertThrows(Exception.class, () -> {
            playerService.update(player);
        });
        
        String exceptedMessage = "User name length does not match pattern";
		String actualMessage = exception.getMessage();
		assertTrue(exceptedMessage.equals(actualMessage));
	}
	
	@Test
	public void updatePlayerWithNumericUserName() throws Exception {
		PlayerService playerService = new PlayerService();
		
		Player player = new Player();
		player.setId(2);
		player.setUserName("12345");
		player.setFirstName("Praveen");
		player.setLastName("kumar");
		player.setUrl("shssssdsfdsdfvdfgvdfd");
		player.setPassword("Aa!1aaaaa");
		Gender personGender = Gender.MALE;
		player.setGender(personGender);
		player.getAddress().setArea("Aminjikarai");
		player.getAddress().setDistrict("Chennai");
		player.setDateOfBirth(LocalDate.of(2002, 11, 26));
		player.setAbout("I am a good boy");
		
		Exception exception = assertThrows(Exception.class, () -> {
            playerService.update(player);
        });
        
        String exceptedMessage = "User name does not match pattern";
		String actualMessage = exception.getMessage();
		assertTrue(exceptedMessage.equals(actualMessage));
	}
	
	@Test
	public void updatePlayerWithFirstNameValueNull() throws Exception {
		PlayerService playerService = new PlayerService();
		
		Player player = new Player();
		player.setId(2);
		player.setUserName("Praveen");
		player.setFirstName(null);
		player.setLastName("kumar");
		player.setUrl("shssssdsfdsdfvdfgvdfd");
		player.setPassword("Aa!1aaaaa");
		Gender personGender = Gender.MALE;
		player.setGender(personGender);
		player.getAddress().setArea("Aminjikarai");
		player.getAddress().setDistrict("Chennai");
		player.setDateOfBirth(LocalDate.of(2002, 11, 26));
		player.setAbout("I am a good boy");
		
		Exception exception = assertThrows(Exception.class, () -> {
            playerService.update(player);
        });
        
        String exceptedMessage = "First name can't be null or empty";
		String actualMessage = exception.getMessage();
		assertTrue(exceptedMessage.equals(actualMessage));
	}
	
	@Test
	public void updatePlayerWithFirstNameValueEmpty() throws Exception {
		PlayerService playerService = new PlayerService();
		
		Player player = new Player();
		player.setId(2);
		player.setUserName("Praveen");
		player.setFirstName("  ");
		player.setLastName("kumar");
		player.setUrl("shssssdsfdsdfvdfgvdfd");
		player.setPassword("Aa!1aaaaa");
		Gender personGender = Gender.MALE;
		player.setGender(personGender);
		player.getAddress().setArea("Aminjikarai");
		player.getAddress().setDistrict("Chennai");
		player.setDateOfBirth(LocalDate.of(2002, 11, 26));
		player.setAbout("I am a good boy");
		
		Exception exception = assertThrows(Exception.class, () -> {
            playerService.update(player);
        });
        
        String exceptedMessage = "First name can't be null or empty";
		String actualMessage = exception.getMessage();
		assertTrue(exceptedMessage.equals(actualMessage));
	}
	
	@Test
	public void updatePlayerWithNumericFirstName() throws Exception {
		PlayerService playerService = new PlayerService();
		
		Player player = new Player();
		player.setId(2);
		player.setUserName("Praveen");
		player.setFirstName("Pr123");
		player.setLastName("kumar");
		player.setUrl("shssssdsfdsdfvdfgvdfd");
		player.setPassword("Aa!1aaaaa");
		Gender personGender = Gender.MALE;
		player.setGender(personGender);
		player.getAddress().setArea("Aminjikarai");
		player.getAddress().setDistrict("Chennai");
		player.setDateOfBirth(LocalDate.of(2002, 11, 26));
		player.setAbout("I am a good boy");
		
		Exception exception = assertThrows(Exception.class, () -> {
            playerService.update(player);
        });
        
        String exceptedMessage = "First name does not match pattern";
		String actualMessage = exception.getMessage();
		assertTrue(exceptedMessage.equals(actualMessage));
	}
	
	@Test
	public void updatePlayerWithLessFirstNameCharacterLength() throws Exception {
		PlayerService playerService = new PlayerService();
		
		Player player = new Player();
		player.setId(2);
		player.setUserName("praveen");
		player.setFirstName("Pr");
		player.setLastName("kumar");
		player.setUrl("shssssdsfdsdfvdfgvdfd");
		player.setPassword("Aa!1aaaaa");
		Gender personGender = Gender.MALE;
		player.setGender(personGender);
		player.getAddress().setArea("Aminjikarai");
		player.getAddress().setDistrict("Chennai");
		player.setDateOfBirth(LocalDate.of(2002, 11, 26));
		player.setAbout("I am a good boy");
		
		Exception exception = assertThrows(Exception.class, () -> {
            playerService.update(player);
        });
        
        String exceptedMessage = "First name length does not match pattern";
		String actualMessage = exception.getMessage();
		assertTrue(exceptedMessage.equals(actualMessage));
	}
	
	@Test
	public void updatePlayerWithMoreFirstNameCharacterLength() throws Exception {
		PlayerService playerService = new PlayerService();
		
		Player player = new Player();
		player.setId(2);
		player.setUserName("asdddd");
		player.setFirstName("Praveencsdfcbnsdkjvnsdkjfnsdkjfvnsdkjfvn");
		player.setLastName("kumar");
		player.setUrl("shssssdsfdsdfvdfgvdfd");
		player.setPassword("Aa!1aaaaa");
		Gender personGender = Gender.MALE;
		player.setGender(personGender);
		player.getAddress().setArea("Aminjikarai");
		player.getAddress().setDistrict("Chennai");
		player.setDateOfBirth(LocalDate.of(2002, 11, 26));
		player.setAbout("I am a good boy");
		
		Exception exception = assertThrows(Exception.class, () -> {
            playerService.update(player);
        });
        
        String exceptedMessage = "First name length does not match pattern";
		String actualMessage = exception.getMessage();
		assertTrue(exceptedMessage.equals(actualMessage));
	}
	
	@Test
	public void updatePlayerWithPasswordValueNull() throws Exception {
		PlayerService playerService = new PlayerService();
		
		Player player = new Player();
		player.setId(2);
		player.setUserName("Praveen");
		player.setFirstName("Praveen");
		player.setLastName("kumar");
		player.setUrl("shssssdsfdsdfvdfgvdfd");
		player.setPassword(null);
		Gender personGender = Gender.MALE;
		player.setGender(personGender);
		player.getAddress().setArea("Aminjikarai");
		player.getAddress().setDistrict("Chennai");
		player.setDateOfBirth(LocalDate.of(2002, 11, 26));
		player.setAbout("I am a good boy");
		
		Exception exception = assertThrows(Exception.class, () -> {
            playerService.update(player);
        });
        
        String exceptedMessage = "Password can't be null or empty";
		String actualMessage = exception.getMessage();
		assertTrue(exceptedMessage.equals(actualMessage));
	}
	
	@Test
	public void updatePlayerWithPasswordValueEmpty() throws Exception {
		PlayerService playerService = new PlayerService();
		
		Player player = new Player();
		player.setId(2);
		player.setUserName("Praveen");
		player.setFirstName("praveen");
		player.setLastName("kumar");
		player.setUrl("shssssdsfdsdfvdfgvdfd");
		player.setPassword(" ");
		Gender personGender = Gender.MALE;
		player.setGender(personGender);
		player.getAddress().setArea("Aminjikarai");
		player.getAddress().setDistrict("Chennai");
		player.setDateOfBirth(LocalDate.of(2002, 11, 26));
		player.setAbout("I am a good boy");
		
		Exception exception = assertThrows(Exception.class, () -> {
            playerService.update(player);
        });
        
        String exceptedMessage = "Password can't be null or empty";
		String actualMessage = exception.getMessage();
		assertTrue(exceptedMessage.equals(actualMessage));
	}
	
	@Test
	public void updatePlayerWithWeakPassword() throws Exception {
		PlayerService playerService = new PlayerService();
		
		Player player = new Player();
		player.setId(2);
		player.setUserName("Praveen");
		player.setFirstName("Praveen");
		player.setLastName("kumar");
		player.setUrl("shssssdsfdsdfvdfgvdfd");
		player.setPassword("Aa11aaa");
		Gender personGender = Gender.MALE;
		player.setGender(personGender);
		player.getAddress().setArea("Aminjikarai");
		player.getAddress().setDistrict("Chennai");
		player.setDateOfBirth(LocalDate.of(2002, 11, 26));
		player.setAbout("I am a good boy");
		
		Exception exception = assertThrows(Exception.class, () -> {
            playerService.update(player);
        });
        
        String exceptedMessage = "Invalid password";
		String actualMessage = exception.getMessage();
		assertTrue(exceptedMessage.equals(actualMessage));
	}
	
	@Test
	public void updatePlayerWithDOBValueNull() throws Exception {
		PlayerService playerService = new PlayerService();
		
		Player player = new Player();
		player.setId(2);
		player.setUserName("Praveen");
		player.setFirstName("Praveen");
		player.setLastName("kumar");
		player.setUrl("shssssdsfdsdfvdfgvdfd");
		player.setPassword("Aa11!aaa");
		Gender personGender = Gender.MALE;
		player.setGender(personGender);
		player.getAddress().setArea("Aminjikarai");
		player.getAddress().setDistrict("Chennai");
		player.setDateOfBirth(null);
		player.setAbout("I am a good boy");
		
		Exception exception = assertThrows(Exception.class, () -> {
            playerService.update(player);
        });
        
        String exceptedMessage = "Date of birth can not be null";
		String actualMessage = exception.getMessage();
		assertTrue(exceptedMessage.equals(actualMessage));
	}
	
	@Test
	public void updatePlayerWithDOBLessThanTenYear() throws Exception {
		PlayerService playerService = new PlayerService();
		
		Player player = new Player();
		player.setId(2);
		player.setUserName("Praveen");
		player.setFirstName("Praveen");
		player.setLastName("kumar");
		player.setUrl("shssssdsfdsdfvdfgvdfd");
		player.setPassword("Aa11!aaa");
		Gender personGender = Gender.MALE;
		player.setGender(personGender);
		player.getAddress().setArea("Aminjikarai");
		player.getAddress().setDistrict("Chennai");
		player.setDateOfBirth(LocalDate.of(2015, 11, 26));
		player.setAbout("I am a good boy");
		
		Exception exception = assertThrows(Exception.class, () -> {
            playerService.update(player);
        });
        
        String exceptedMessage = "Age should be more than 10 years";
		String actualMessage = exception.getMessage();
		assertTrue(exceptedMessage.equals(actualMessage));
	}
	
	@Test
	public void updatePlayerWithDOBFutureDate() throws Exception {
		PlayerService playerService = new PlayerService();
		
		Player player = new Player();
		player.setId(2);
		player.setUserName("Praveen");
		player.setFirstName("Praveen");
		player.setLastName("kumar");
		player.setUrl("shssssdsfdsdfvdfgvdfd");
		player.setPassword("Aa11!aaa");
		Gender personGender = Gender.MALE;
		player.setGender(personGender);
		player.getAddress().setArea("Aminjikarai");
		player.getAddress().setDistrict("Chennai");
		player.setDateOfBirth(LocalDate.of(2023, 11, 26));
		player.setAbout("I am a good boy");
		
		Exception exception = assertThrows(Exception.class, () -> {
            playerService.update(player);
        });
        
        String exceptedMessage = "Age should be more than 10 years";
		String actualMessage = exception.getMessage();
		assertTrue(exceptedMessage.equals(actualMessage));
	}
	
	@Test
	public void updatePlayerWithDOBLeapYearDate() throws Exception {
		PlayerService playerService = new PlayerService();
		
		Player player = new Player();
		player.setId(2);
		player.setUserName("Praveen");
		player.setFirstName("Praveen");
		player.setLastName("kumar");
		player.setUrl("shssssdsfdsdfvdfgvdfd");
		player.setPassword("Aa11!aaa");
		Gender personGender = Gender.MALE;
		player.setGender(personGender);
		player.getAddress().setArea("Aminjikarai");
		player.getAddress().setDistrict("Chennai");
		player.setDateOfBirth(LocalDate.of(2000, 2, 29));
		player.setAbout("I am a good boy");
		
		Exception exception = assertThrows(Exception.class, () -> {
            playerService.update(player);
        });
        
        String exceptedMessage = "Date of birth is a leap year";
		String actualMessage = exception.getMessage();
		System.out.println(actualMessage);
		assertTrue(exceptedMessage.equals(actualMessage));
	}
	
	@Test
	public void updatePlayerWithAddressValueNull() throws Exception {
		PlayerService playerService = new PlayerService();
		
		Player player = new Player();
		player.setId(2);
		player.setUserName("Praveen");
		player.setFirstName("Praveen");
		player.setLastName("kumar");
		player.setUrl("shssssdsfdsdfvdfgvdfd");
		player.setPassword("Aa11!aaa");
		Gender personGender = Gender.MALE;
		player.setGender(personGender);
		player.setAddress(null);
		player.setDateOfBirth(LocalDate.of(2000, 3, 29));
		player.setAbout("I am a good boy");
		
		Exception exception = assertThrows(Exception.class, () -> {
            playerService.update(player);
        });
        
        String exceptedMessage = "Invalid Address input";
		String actualMessage = exception.getMessage();
		assertTrue(exceptedMessage.equals(actualMessage));
	}
	
	@Test
	public void updatePlayerWithAreaValueNull() throws Exception {
		PlayerService playerService = new PlayerService();
		
		Player player = new Player();
		player.setId(2);
		player.setUserName("Praveen");
		player.setFirstName("Praveen");
		player.setLastName("kumar");
		player.setUrl("shssssdsfdsdfvdfgvdfd");
		player.setPassword("Aa11!aaa");
		Gender personGender = Gender.MALE;
		player.setGender(personGender);
		player.getAddress().setArea(null);
		player.getAddress().setDistrict("Chennai");
		player.setDateOfBirth(LocalDate.of(2000, 3, 29));
		player.setAbout("I am a good boy");
		
		Exception exception = assertThrows(Exception.class, () -> {
            playerService.update(player);
        });
        
        String exceptedMessage = "Area can't be null or empty";
		String actualMessage = exception.getMessage();
		assertTrue(exceptedMessage.equals(actualMessage));
	}
	
	@Test
	public void updatePlayerWithAreaValueEmpty() throws Exception {
		PlayerService playerService = new PlayerService();
		
		Player player = new Player();
		player.setId(2);
		player.setUserName("Praveen");
		player.setFirstName("Praveen");
		player.setLastName("kumar");
		player.setUrl("shssssdsfdsdfvdfgvdfd");
		player.setPassword("Aa11!aaa");
		Gender personGender = Gender.MALE;
		player.setGender(personGender);
		player.getAddress().setArea("  ");
		player.getAddress().setDistrict("Chennai");
		player.setDateOfBirth(LocalDate.of(2000, 3, 29));
		player.setAbout("I am a good boy");
		
		Exception exception = assertThrows(Exception.class, () -> {
            playerService.update(player);
        });
        
        String exceptedMessage = "Area can't be null or empty";
		String actualMessage = exception.getMessage();
		assertTrue(exceptedMessage.equals(actualMessage));
	}
	
	@Test
	public void updatePlayerWithNumericalAreaValue() throws Exception {
		PlayerService playerService = new PlayerService();
		
		Player player = new Player();
		player.setId(2);
		player.setUserName("Praveen");
		player.setFirstName("Praveen");
		player.setLastName("kumar");
		player.setUrl("shssssdsfdsdfvdfgvdfd");
		player.setPassword("Aa11!aaa");
		Gender personGender = Gender.MALE;
		player.setGender(personGender);
		player.getAddress().setArea("12sas");
		player.getAddress().setDistrict("Chennai");
		player.setDateOfBirth(LocalDate.of(2000, 3, 29));
		player.setAbout("I am a good boy");
		
		Exception exception = assertThrows(Exception.class, () -> {
            playerService.update(player);
        });
        
        String exceptedMessage = "Area does not match pattern";
		String actualMessage = exception.getMessage();
		assertTrue(exceptedMessage.equals(actualMessage));
	}
	
	@Test
	public void updatePlayerWithDistrictValueNull() throws Exception {
		PlayerService playerService = new PlayerService();
		
		Player player = new Player();
		player.setId(2);
		player.setUserName("Praveen");
		player.setFirstName("Praveen");
		player.setLastName("kumar");
		player.setUrl("shssssdsfdsdfvdfgvdfd");
		player.setPassword("Aa11!aaa");
		Gender personGender = Gender.MALE;
		player.setGender(personGender);
		player.getAddress().setArea("Aminjikarai");
		player.getAddress().setDistrict(null);
		player.setDateOfBirth(LocalDate.of(2000, 3, 29));
		player.setAbout("I am a good boy");
		
		Exception exception = assertThrows(Exception.class, () -> {
            playerService.update(player);
        });
        
        String exceptedMessage = "District can't be null or empty";
		String actualMessage = exception.getMessage();
		System.out.println(actualMessage);
		assertTrue(exceptedMessage.equals(actualMessage));
	}
	
	@Test
	public void updatePlayerWithDistrictValueEmpty() throws Exception {
		PlayerService playerService = new PlayerService();
		
		Player player = new Player();
		player.setId(2);
		player.setUserName("Praveen");
		player.setFirstName("Praveen");
		player.setLastName("kumar");
		player.setUrl("shssssdsfdsdfvdfgvdfd");
		player.setPassword("Aa11!aaa");
		Gender personGender = Gender.MALE;
		player.setGender(personGender);
		player.getAddress().setArea("Aminjikarai");
		player.getAddress().setDistrict("  ");
		player.setDateOfBirth(LocalDate.of(2000, 3, 29));
		player.setAbout("I am a good boy");
		
		Exception exception = assertThrows(Exception.class, () -> {
            playerService.update(player);
        });
        
        String exceptedMessage = "District can't be null or empty";
		String actualMessage = exception.getMessage();
		assertTrue(exceptedMessage.equals(actualMessage));
	}
	
	@Test
	public void updatePlayerWithNumericalDistrictValue() throws Exception {
		PlayerService playerService = new PlayerService();
		
		Player player = new Player();
		player.setId(2);
		player.setUserName("Praveen");
		player.setFirstName("Praveen");
		player.setLastName("kumar");
		player.setUrl("shssssdsfdsdfvdfgvdfd");
		player.setPassword("Aa11!aaa");
		Gender personGender = Gender.MALE;
		player.setGender(personGender);
		player.getAddress().setArea("Aminjikarai");
		player.getAddress().setDistrict("12sdd");
		player.setDateOfBirth(LocalDate.of(2000, 3, 29));
		player.setAbout("I am a good boy");
		
		Exception exception = assertThrows(Exception.class, () -> {
            playerService.update(player);
        });
        
        String exceptedMessage = "District does not match pattern";
		String actualMessage = exception.getMessage();
		assertTrue(exceptedMessage.equals(actualMessage));
	}
	
	@Test
	public void updatePlayerWithLessAreaCharacterLength() throws Exception {
		PlayerService playerService = new PlayerService();
		
		Player player = new Player();
		player.setId(2);
		player.setUserName("Praveen");
		player.setFirstName("Praveen");
		player.setLastName("kumar");
		player.setUrl("shssssdsfdsdfvdfgvdfd");
		player.setPassword("Aa11!aaa");
		Gender personGender = Gender.MALE;
		player.setGender(personGender);
		player.getAddress().setArea("Am");
		player.getAddress().setDistrict("Chennai");
		player.setDateOfBirth(LocalDate.of(2000, 3, 29));
		player.setAbout("I am a good boy");
		
		Exception exception = assertThrows(Exception.class, () -> {
            playerService.update(player);
        });
        
        String exceptedMessage = "Area length does not match pattern";
		String actualMessage = exception.getMessage();
		assertTrue(exceptedMessage.equals(actualMessage));
	}
	@Test
	public void updatePlayerWithMoreAreaCharacterLength() throws Exception {
		PlayerService playerService = new PlayerService();
		
		Player player = new Player();
		player.setId(2);
		player.setUserName("Praveen");
		player.setFirstName("Praveen");
		player.setLastName("kumar");
		player.setUrl("shssssdsfdsdfvdfgvdfd");
		player.setPassword("Aa11!aaa");
		Gender personGender = Gender.MALE;
		player.setGender(personGender);
		player.getAddress().setArea("Amhbdajhbsdjfhbsdfsjdhbfsdjhjhsbfsdjfhbsdfjhbsdjfhbsdf");
		player.getAddress().setDistrict("Chennai");
		player.setDateOfBirth(LocalDate.of(2000, 3, 29));
		player.setAbout("I am a good boy");
		
		Exception exception = assertThrows(Exception.class, () -> {
            playerService.update(player);
        });
        
        String exceptedMessage = "Area length does not match pattern";
		String actualMessage = exception.getMessage();
		assertTrue(exceptedMessage.equals(actualMessage));
	}
	
	@Test
	public void updatePlayerWithLessDistrictCharacterLength() throws Exception {
		PlayerService playerService = new PlayerService();
		
		Player player = new Player();
		player.setId(2);
		player.setUserName("Praveen");
		player.setFirstName("Praveen");
		player.setLastName("kumar");
		player.setUrl("shssssdsfdsdfvdfgvdfd");
		player.setPassword("Aa11!aaa");
		Gender personGender = Gender.MALE;
		player.setGender(personGender);
		player.getAddress().setArea("Aminjikarai");
		player.getAddress().setDistrict("Ch");
		player.setDateOfBirth(LocalDate.of(2000, 3, 29));
		player.setAbout("I am a good boy");
		
		Exception exception = assertThrows(Exception.class, () -> {
            playerService.update(player);
        });
        
        String exceptedMessage = "District length does not match pattern";
		String actualMessage = exception.getMessage();
		assertTrue(exceptedMessage.equals(actualMessage));
	}
	@Test
	public void updatePlayerWithMoreDistrictCharacterLength() throws Exception {
		PlayerService playerService = new PlayerService();
		
		Player player = new Player();
		player.setId(2);
		player.setUserName("Praveen");
		player.setFirstName("Praveen");
		player.setLastName("kumar");
		player.setUrl("shssssdsfdsdfvdfgvdfd");
		player.setPassword("Aa11!aaa");
		Gender personGender = Gender.MALE;
		player.setGender(personGender);
		player.getAddress().setArea("Aminjikarai");
		player.getAddress().setDistrict("Amhbdajhbsdjfhbsdfsjdhbfsdjhjhsbfsdjfhbsdfjhbsdjfhbsdf");
		player.setDateOfBirth(LocalDate.of(2000, 3, 29));
		player.setAbout("I am a good boy");
		
		Exception exception = assertThrows(Exception.class, () -> {
            playerService.update(player);
        });
        
        String exceptedMessage = "District length does not match pattern";
		String actualMessage = exception.getMessage();
		assertTrue(exceptedMessage.equals(actualMessage));
	}
	
	@Test
	public void updatePlayerWithLessLastNameCharacterLength() throws Exception {
		PlayerService playerService = new PlayerService();
		
		Player player = new Player();
		player.setId(2);
		player.setUserName("assdsdsdf");
		player.setFirstName("Praveen");
		player.setLastName("");
		player.setUrl("shssssdsfdsdfvdfgvdfd");
		player.setPassword("Aa!1aaaaa");
		Gender personGender = Gender.MALE;
		player.setGender(personGender);
		player.getAddress().setArea("Aminjikarai");
		player.getAddress().setDistrict("Chennai");
		player.setDateOfBirth(LocalDate.of(2002, 11, 26));
		player.setAbout("I am a good boy");
		
		Exception exception = assertThrows(Exception.class, () -> {
            playerService.update(player);
        });
        
        String exceptedMessage = "Last name does not match pattern";
		String actualMessage = exception.getMessage();
		assertTrue(exceptedMessage.equals(actualMessage));
	}
	
	@Test
	public void updatePlayerWithMoreLastNameCharacterLength() throws Exception {
		PlayerService playerService = new PlayerService();
		
		Player player = new Player();
		player.setId(2);
		player.setUserName("asddask");
		player.setFirstName("Praveen");
		player.setLastName("kumardsvsfdvnvdfsdfsdfvsd");
		player.setUrl("shssssdsfdsdfvdfgvdfd");
		player.setPassword("Aa!1aaaaa");
		Gender personGender = Gender.MALE;
		player.setGender(personGender);
		player.getAddress().setArea("Aminjikarai");
		player.getAddress().setDistrict("Chennai");
		player.setDateOfBirth(LocalDate.of(2002, 11, 26));
		player.setAbout("I am a good boy");
		
		Exception exception = assertThrows(Exception.class, () -> {
            playerService.update(player);
        });
        
        String exceptedMessage = "Last name length does not match pattern";
		String actualMessage = exception.getMessage();
		assertTrue(exceptedMessage.equals(actualMessage));
	}
	
	@Test
	public void updatePlayerWithNumericLastName() throws Exception {
		PlayerService playerService = new PlayerService();
		
		Player player = new Player();
		player.setId(2);
		player.setUserName("pksasddd");
		player.setFirstName("Praveen");
		player.setLastName("1");
		player.setUrl("shssssdsfdsdfvdfgvdfd");
		player.setPassword("Aa!1aaaaa");
		Gender personGender = Gender.MALE;
		player.setGender(personGender);
		player.getAddress().setArea("Aminjikarai");
		player.getAddress().setDistrict("Chennai");
		player.setDateOfBirth(LocalDate.of(2002, 11, 26));
		player.setAbout("I am a good boy");
		
		Exception exception = assertThrows(Exception.class, () -> {
            playerService.update(player);
        });
        
        String exceptedMessage = "Last name does not match pattern";
		String actualMessage = exception.getMessage();
		assertTrue(exceptedMessage.equals(actualMessage));
	}
	
	@Test
	public void updatePlayerWithMoreAboutStringLength() throws Exception {
		PlayerService playerService = new PlayerService();
		
		Player player = new Player();
		player.setId(2);
		player.setUserName("pksasddd");
		player.setFirstName("Praveen");
		player.setLastName("kumar");
		player.setUrl("shssssdsfdsdfvdfgvdfd");
		player.setPassword("Aa!1aaaaa");
		Gender personGender = Gender.MALE;
		player.setGender(personGender);
		player.getAddress().setArea("Aminjikarai");
		player.getAddress().setDistrict("Chennai");
		player.setDateOfBirth(LocalDate.of(2002, 11, 26));
		player.setAbout("I am a good boy jhgvjhgvjhvjhvjhbvjhgbjhgjhgjhvkhgjvjhgkghvjhvugghgvjvkjkbncsdkjfnsdfvkjsdnfsdkjnsdfkjbnfskdjfnsdkjfnskdjfnskdjfnsdjfnsdljfnjhgrijriuqejnweiufhwoeiyulefbgdyfghbsdgfiyerbidfbisudfhihjbfiufhijhbnfishfidfbnsijdfisdjbnfisdjnfisjdnfisjdfnsidjfudgfdsfgdfhdfgndfgnfghnghngfdfsdgtuuoiptdgfgjmghfj");
		
		Exception exception = assertThrows(Exception.class, () -> {
            playerService.update(player);
        });
        
        String exceptedMessage = "About player data length does not match pattern";
		String actualMessage = exception.getMessage();
		assertTrue(exceptedMessage.equals(actualMessage));
	}
	
	
	
}
