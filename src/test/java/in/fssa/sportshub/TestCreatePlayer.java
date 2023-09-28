package in.fssa.sportshub;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalDate;

import org.junit.jupiter.api.Test;

import in.fssa.sportshub.model.Gender;
import in.fssa.sportshub.model.Player;
import in.fssa.sportshub.service.PlayerService;

public class TestCreatePlayer {
	
	private long generateRandomPhoneNumber() {
	    // Generate a random long phone number within a specific range
	    long minPhoneNumber = 8000000000L; // 10 digits
	    long maxPhoneNumber = 9999999999L; // 10 digits

	    return minPhoneNumber + (long) (Math.random() * (maxPhoneNumber - minPhoneNumber + 1));
	}
	
	@Test
	public void createPlayerWithValidData() throws Exception {
		PlayerService playerService = new PlayerService();
		
		Player player = new Player();
		player.setPhoneNumber(generateRandomPhoneNumber());// here change new phone number
		player.setUserName("Madhan");
		player.setFirstName("Praveen");
		player.setLastName("kumar");
		player.setUrl("shssssdsfdsdfvdfgvdfd");
		player.setPassword("Aa!1aaaaa");
		Gender personGender = Gender.MALE;
		player.setGender(personGender);
		player.getAddress().setArea("ShennoiNagar");
		player.getAddress().setDistrict("Chennai");
		player.setDateOfBirth(LocalDate.of(2002, 11, 26));
		player.setAbout("I am a good boy");
		
		assertDoesNotThrow(()->{
			playerService.create(player);
		});
	}
	
	@Test
    public void createPlayerWithNullValues() {
        PlayerService playerService = new PlayerService();

        Exception exception = assertThrows(Exception.class, () -> {
            playerService.create(null);
        });
        
        String exceptedMessage = "Invalid player input";
		String actualMessage = exception.getMessage();
		assertTrue(exceptedMessage.equals(actualMessage));
    }
	
	@Test
	public void createPlayerWithInvalidPhoneNumber() throws Exception {
		PlayerService playerService = new PlayerService();
		
		Player player = new Player();
		player.setPhoneNumber(93446552l);
		player.setUserName("Pksaaaa");
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
            playerService.create(player);
        });
        
        String exceptedMessage = "Invalid phone number";
		String actualMessage = exception.getMessage();
		System.out.println(actualMessage);
		assertTrue(exceptedMessage.equals(actualMessage));
	}
	
	@Test
	public void createPlayerWithUserNameValueNull() throws Exception {
		PlayerService playerService = new PlayerService();
		
		Player player = new Player();
		player.setPhoneNumber(9344655211l);
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
            playerService.create(player);
        });
        
        String exceptedMessage = "User name can't be null or empty";
		String actualMessage = exception.getMessage();
		assertTrue(exceptedMessage.equals(actualMessage));
	}
	
	@Test
	public void createPlayerWithUserNameValueEmpty() throws Exception {
		PlayerService playerService = new PlayerService();
		
		Player player = new Player();
		player.setPhoneNumber(9344655211l);
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
            playerService.create(player);
        });
        
        String exceptedMessage = "User name can't be null or empty";
		String actualMessage = exception.getMessage();
		assertTrue(exceptedMessage.equals(actualMessage));
	}
	
	@Test
	public void createPlayerWithLessUserNameCharacterLength() throws Exception {
		PlayerService playerService = new PlayerService();
		
		Player player = new Player();
		player.setPhoneNumber(9344655211l);
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
            playerService.create(player);
        });
        
        String exceptedMessage = "User name length does not match pattern";
		String actualMessage = exception.getMessage();
		assertTrue(exceptedMessage.equals(actualMessage));
	}
	
	@Test
	public void createPlayerWithMoreUserNameCharacterLength() throws Exception {
		PlayerService playerService = new PlayerService();
		
		Player player = new Player();
		player.setPhoneNumber(9344655211l);
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
            playerService.create(player);
        });
        
        String exceptedMessage = "User name length does not match pattern";
		String actualMessage = exception.getMessage();
		assertTrue(exceptedMessage.equals(actualMessage));
	}
	
	@Test
	public void createPlayerWithNumericUserName() throws Exception {
		PlayerService playerService = new PlayerService();
		
		Player player = new Player();
		player.setPhoneNumber(9344655211l);
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
            playerService.create(player);
        });
        
        String exceptedMessage = "User name does not match pattern";
		String actualMessage = exception.getMessage();
		assertTrue(exceptedMessage.equals(actualMessage));
	}
	
	@Test
	public void createPlayerWithFirstNameValueNull() throws Exception {
		PlayerService playerService = new PlayerService();
		
		Player player = new Player();
		player.setPhoneNumber(9344655211l);
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
            playerService.create(player);
        });
        
        String exceptedMessage = "First name can't be null or empty";
		String actualMessage = exception.getMessage();
		assertTrue(exceptedMessage.equals(actualMessage));
	}
	
	
	@Test
	public void createPlayerWithNumericFirstName() throws Exception {
		PlayerService playerService = new PlayerService();
		
		Player player = new Player();
		player.setPhoneNumber(9344655211l);
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
            playerService.create(player);
        });
        
        String exceptedMessage = "First name does not match pattern";
		String actualMessage = exception.getMessage();
		assertTrue(exceptedMessage.equals(actualMessage));
	}
	
	@Test
	public void createPlayerWithLessFirstNameCharacterLength() throws Exception {
		PlayerService playerService = new PlayerService();
		
		Player player = new Player();
		player.setPhoneNumber(9344655211l);
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
            playerService.create(player);
        });
        
        String exceptedMessage = "First name length does not match pattern";
		String actualMessage = exception.getMessage();
		assertTrue(exceptedMessage.equals(actualMessage));
	}
	
	@Test
	public void createPlayerWithMoreFirstNameCharacterLength() throws Exception {
		PlayerService playerService = new PlayerService();
		
		Player player = new Player();
		player.setPhoneNumber(9344655211l);
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
            playerService.create(player);
        });
        
        String exceptedMessage = "First name length does not match pattern";
		String actualMessage = exception.getMessage();
		assertTrue(exceptedMessage.equals(actualMessage));
	}
	
	@Test
	public void createPlayerWithPasswordValueNull() throws Exception {
		PlayerService playerService = new PlayerService();
		
		Player player = new Player();
		player.setPhoneNumber(9344655211l);
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
            playerService.create(player);
        });
        
        String exceptedMessage = "Password can't be null or empty";
		String actualMessage = exception.getMessage();
		assertTrue(exceptedMessage.equals(actualMessage));
	}
	
	@Test
	public void createPlayerWithPasswordValueEmpty() throws Exception {
		PlayerService playerService = new PlayerService();
		
		Player player = new Player();
		player.setPhoneNumber(9344655211l);
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
            playerService.create(player);
        });
        
        String exceptedMessage = "Password can't be null or empty";
		String actualMessage = exception.getMessage();
		assertTrue(exceptedMessage.equals(actualMessage));
	}
	
	@Test
	public void createPlayerWithWeakPassword() throws Exception {
		PlayerService playerService = new PlayerService();
		
		Player player = new Player();
		player.setPhoneNumber(9344655211l);
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
            playerService.create(player);
        });
        
        String exceptedMessage = "Invalid password";
		String actualMessage = exception.getMessage();
		assertTrue(exceptedMessage.equals(actualMessage));
	}
	
	@Test
	public void createPlayerWithDOBLessThanTenYear() throws Exception {
		PlayerService playerService = new PlayerService();
		
		Player player = new Player();
		player.setPhoneNumber(9344655211l);
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
            playerService.create(player);
        });
        
        String exceptedMessage = "Age should be more than 10 years";
		String actualMessage = exception.getMessage();
		assertTrue(exceptedMessage.equals(actualMessage));
	}
	
	@Test
	public void createPlayerWithDOBFutureDate() throws Exception {
		PlayerService playerService = new PlayerService();
		
		Player player = new Player();
		player.setPhoneNumber(9344655211l);
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
            playerService.create(player);
        });
        
        String exceptedMessage = "Age should be more than 10 years";
		String actualMessage = exception.getMessage();
		assertTrue(exceptedMessage.equals(actualMessage));
	}
	
	@Test
	public void createPlayerWithDOBLeapYearDate() throws Exception {
		PlayerService playerService = new PlayerService();
		
		Player player = new Player();
		player.setPhoneNumber(9344655211l);
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
            playerService.create(player);
        });
        
        String exceptedMessage = "Date of birth is a leap year";
		String actualMessage = exception.getMessage();
		System.out.println(actualMessage);
		assertTrue(exceptedMessage.equals(actualMessage));
	}
	
	@Test
	public void createPlayerWithAddressValueNull() throws Exception {
		PlayerService playerService = new PlayerService();
		
		Player player = new Player();
		player.setPhoneNumber(9344655211l);
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
            playerService.create(player);
        });
        
        String exceptedMessage = "Invalid Address input";
		String actualMessage = exception.getMessage();
		assertTrue(exceptedMessage.equals(actualMessage));
	}
	
	@Test
	public void createPlayerWithAreaValueNull() throws Exception {
		PlayerService playerService = new PlayerService();
		
		Player player = new Player();
		player.setPhoneNumber(9344655211l);
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
            playerService.create(player);
        });
        
        String exceptedMessage = "Area can't be null or empty";
		String actualMessage = exception.getMessage();
		assertTrue(exceptedMessage.equals(actualMessage));
	}
	
	@Test
	public void createPlayerWithAreaValueEmpty() throws Exception {
		PlayerService playerService = new PlayerService();
		
		Player player = new Player();
		player.setPhoneNumber(9344655211l);
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
            playerService.create(player);
        });
        
        String exceptedMessage = "Area can't be null or empty";
		String actualMessage = exception.getMessage();
		assertTrue(exceptedMessage.equals(actualMessage));
	}
	
	@Test
	public void createPlayerWithNumericalAreaValue() throws Exception {
		PlayerService playerService = new PlayerService();
		
		Player player = new Player();
		player.setPhoneNumber(9344655211l);
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
            playerService.create(player);
        });
        
        String exceptedMessage = "Area does not match pattern";
		String actualMessage = exception.getMessage();
		assertTrue(exceptedMessage.equals(actualMessage));
	}
	
	@Test
	public void createPlayerWithDistrictValueNull() throws Exception {
		PlayerService playerService = new PlayerService();
		
		Player player = new Player();
		player.setPhoneNumber(9344655211l);
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
            playerService.create(player);
        });
        
        String exceptedMessage = "District can't be null or empty";
		String actualMessage = exception.getMessage();
		System.out.println(actualMessage);
		assertTrue(exceptedMessage.equals(actualMessage));
	}
	
	@Test
	public void createPlayerWithDistrictValueEmpty() throws Exception {
		PlayerService playerService = new PlayerService();
		
		Player player = new Player();
		player.setPhoneNumber(9344655211l);
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
            playerService.create(player);
        });
        
        String exceptedMessage = "District can't be null or empty";
		String actualMessage = exception.getMessage();
		assertTrue(exceptedMessage.equals(actualMessage));
	}
	
	@Test
	public void createPlayerWithNumericalDistrictValue() throws Exception {
		PlayerService playerService = new PlayerService();
		
		Player player = new Player();
		player.setPhoneNumber(9344655211l);
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
            playerService.create(player);
        });
        
        String exceptedMessage = "District does not match pattern";
		String actualMessage = exception.getMessage();
		assertTrue(exceptedMessage.equals(actualMessage));
	}
	
	@Test
	public void createPlayerWithLessAreaCharacterLength() throws Exception {
		PlayerService playerService = new PlayerService();
		
		Player player = new Player();
		player.setPhoneNumber(9344655211l);
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
            playerService.create(player);
        });
        
        String exceptedMessage = "Area length does not match pattern";
		String actualMessage = exception.getMessage();
		assertTrue(exceptedMessage.equals(actualMessage));
	}
	@Test
	public void createPlayerWithMoreAreaCharacterLength() throws Exception {
		PlayerService playerService = new PlayerService();
		
		Player player = new Player();
		player.setPhoneNumber(9344655211l);
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
            playerService.create(player);
        });
        
        String exceptedMessage = "Area length does not match pattern";
		String actualMessage = exception.getMessage();
		assertTrue(exceptedMessage.equals(actualMessage));
	}
	
	@Test
	public void createPlayerWithLessDistrictCharacterLength() throws Exception {
		PlayerService playerService = new PlayerService();
		
		Player player = new Player();
		player.setPhoneNumber(9344655211l);
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
            playerService.create(player);
        });
        
        String exceptedMessage = "District length does not match pattern";
		String actualMessage = exception.getMessage();
		assertTrue(exceptedMessage.equals(actualMessage));
	}
	@Test
	public void createPlayerWithMoreDistrictCharacterLength() throws Exception {
		PlayerService playerService = new PlayerService();
		
		Player player = new Player();
		player.setPhoneNumber(9344655211l);
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
            playerService.create(player);
        });
        
        String exceptedMessage = "District length does not match pattern";
		String actualMessage = exception.getMessage();
		assertTrue(exceptedMessage.equals(actualMessage));
	}
	
	@Test
	public void createPlayerWithLessLastNameCharacterLength() throws Exception {
		PlayerService playerService = new PlayerService();
		
		Player player = new Player();
		player.setPhoneNumber(9344655211l);
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
            playerService.create(player);
        });
        
        String exceptedMessage = "Last name does not match pattern";
		String actualMessage = exception.getMessage();
		assertTrue(exceptedMessage.equals(actualMessage));
	}
	
	@Test
	public void createPlayerWithMoreLastNameCharacterLength() throws Exception {
		PlayerService playerService = new PlayerService();
		
		Player player = new Player();
		player.setPhoneNumber(9344655211l);
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
            playerService.create(player);
        });
        
        String exceptedMessage = "Last name length does not match pattern";
		String actualMessage = exception.getMessage();
		assertTrue(exceptedMessage.equals(actualMessage));
	}
	
	@Test
	public void createPlayerWithNumericLastName() throws Exception {
		PlayerService playerService = new PlayerService();
		
		Player player = new Player();
		player.setPhoneNumber(9344655211l);
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
            playerService.create(player);
        });
        
        String exceptedMessage = "Last name does not match pattern";
		String actualMessage = exception.getMessage();
		assertTrue(exceptedMessage.equals(actualMessage));
	}
	
	@Test
	public void createPlayerWithMoreAboutStringLength() throws Exception {
		PlayerService playerService = new PlayerService();
		
		Player player = new Player();
		player.setPhoneNumber(9344655211l);
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
            playerService.create(player);
        });
        
        String exceptedMessage = "About player data length does not match pattern";
		String actualMessage = exception.getMessage();
		assertTrue(exceptedMessage.equals(actualMessage));
	}
	
	@Test
	public void createPlayerWithExistingPhoneNumber() throws Exception {
		PlayerService playerService = new PlayerService();
		
		Player player = new Player();
		player.setPhoneNumber(9344655211l);
		player.setUserName("Pksaasd");
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
            playerService.create(player);
        });
        
        String exceptedMessage = "Phone number already exist";
		String actualMessage = exception.getMessage();
		assertTrue(exceptedMessage.equals(actualMessage));
	}
	
	
}
