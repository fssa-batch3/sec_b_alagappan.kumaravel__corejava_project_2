package in.fssa.sportshub;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

import org.junit.jupiter.api.Test;


import in.fssa.sportshub.model.MatchRequest;
import in.fssa.sportshub.model.OpponentType;
import in.fssa.sportshub.service.MatchRequestService;

public class TestCreateMatchRequest {
	
//	database update pannanum for matchrequest type of match update need 
//	matchcreate testcase , service validator need to update	
//	github la user flow update pannnum
	
	@Test
	public void CreateMatchRequestWithValidDataToTeam() throws Exception {
		MatchRequestService matchRequestServ = new MatchRequestService();
		MatchRequest matchRequest = new MatchRequest();
		
		matchRequest.setCreatedBy(1);
		matchRequest.setToTeam(3);
		matchRequest.setTypeOfMatch(1);
		matchRequest.setMembers(10);
		matchRequest.setMembersAgeFrom(19);
		matchRequest.setMembersAgeTo(22);	
		OpponentType opponentType = OpponentType.TO_TEAM;
		matchRequest.setOpponentType(opponentType);
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
		matchRequest.setAddressId(1);
		matchRequest.setTypeOfMatch(1);
		matchRequest.setMembers(10);
		matchRequest.setMembersAgeFrom(19);
		matchRequest.setMembersAgeTo(22);	
		OpponentType opponentType = OpponentType.TO_AREA;	
		matchRequest.setOpponentType(opponentType);
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
    public void createMatchRequestWithNullValues() {
		MatchRequestService matchRequestServ = new MatchRequestService();

        Exception exception = assertThrows(Exception.class, () -> {
        	matchRequestServ.create(null, 1);
        });
        
        String exceptedMessage = "Invalid match request input";
		String actualMessage = exception.getMessage();
		assertTrue(exceptedMessage.equals(actualMessage));
    }
	
	@Test
    public void createMatchRequestWithInvalidCreatedPlayerId() {
		MatchRequestService matchRequestServ = new MatchRequestService();
		MatchRequest matchRequest = new MatchRequest();
		
		matchRequest.setCreatedBy(-1);
		matchRequest.setAddressId(4);
		matchRequest.setTypeOfMatch(1);
		matchRequest.setMembers(10);
		matchRequest.setMembersAgeFrom(19);
		matchRequest.setMembersAgeTo(22);
		OpponentType opponentType = OpponentType.TO_TEAM;
		matchRequest.setOpponentType(opponentType);
        LocalDateTime currentDateTime = LocalDateTime.now();
        long amountToAdd = 3; // Change this to the desired amount
        ChronoUnit unit = ChronoUnit.DAYS; // Change this to the desired unit

        LocalDateTime futureDateTime = currentDateTime.plus(amountToAdd, unit);

		matchRequest.setMatchTime(futureDateTime);
		matchRequest.setLocation("Shennoi nagar ground");
		matchRequest.setInformation("100 rs betting");
        Exception exception = assertThrows(Exception.class, () -> {
        	matchRequestServ.create(matchRequest, 1);
        });
        
        String exceptedMessage = "Invalid create player id";
		String actualMessage = exception.getMessage();
		System.out.println(actualMessage);
		assertTrue(exceptedMessage.equals(actualMessage));
    }
	
	@Test
    public void createMatchRequestWithInavlidTypeOfMatch() {
		MatchRequestService matchRequestServ = new MatchRequestService();
		MatchRequest matchRequest = new MatchRequest();
		
		matchRequest.setCreatedBy(1);
		matchRequest.setAddressId(4);
		matchRequest.setTypeOfMatch(3);
		matchRequest.setMembers(10);
		matchRequest.setMembersAgeFrom(19);
		matchRequest.setMembersAgeTo(22);
		OpponentType opponentType = OpponentType.TO_TEAM;
		matchRequest.setOpponentType(opponentType);
        LocalDateTime currentDateTime = LocalDateTime.now();
        long amountToAdd = 3; // Change this to the desired amount
        ChronoUnit unit = ChronoUnit.DAYS; // Change this to the desired unit

        LocalDateTime futureDateTime = currentDateTime.plus(amountToAdd, unit);

		matchRequest.setMatchTime(futureDateTime);
		matchRequest.setLocation("Shennoi nagar ground");
		matchRequest.setInformation("100 rs betting");
        Exception exception = assertThrows(Exception.class, () -> {
        	matchRequestServ.create(matchRequest, 1);
        });
        
        String exceptedMessage = "Invalid type of match input";
		String actualMessage = exception.getMessage();
		assertTrue(exceptedMessage.equals(actualMessage));
    }
	
	@Test
    public void createMatchRequestWithLessMember() {
		MatchRequestService matchRequestServ = new MatchRequestService();
		MatchRequest matchRequest = new MatchRequest();
		
		matchRequest.setCreatedBy(1);
		matchRequest.setAddressId(4);
		matchRequest.setTypeOfMatch(1);
		matchRequest.setMembers(0);
		matchRequest.setMembersAgeFrom(19);
		matchRequest.setMembersAgeTo(22);		OpponentType opponentType = OpponentType.TO_TEAM;		matchRequest.setOpponentType(opponentType);
        LocalDateTime currentDateTime = LocalDateTime.now();
        long amountToAdd = 3; // Change this to the desired amount
        ChronoUnit unit = ChronoUnit.DAYS; // Change this to the desired unit

        LocalDateTime futureDateTime = currentDateTime.plus(amountToAdd, unit);

		matchRequest.setMatchTime(futureDateTime);
		matchRequest.setLocation("Shennoi nagar ground");
		matchRequest.setInformation("100 rs betting");
        Exception exception = assertThrows(Exception.class, () -> {
        	matchRequestServ.create(matchRequest, 1);
        });
        
        String exceptedMessage = "Invalid members count";
		String actualMessage = exception.getMessage();
		assertTrue(exceptedMessage.equals(actualMessage));
    }
	
	@Test
    public void createMatchRequestWithMoreMember() {
		MatchRequestService matchRequestServ = new MatchRequestService();
		MatchRequest matchRequest = new MatchRequest();
		
		matchRequest.setCreatedBy(1);
		matchRequest.setAddressId(4);
		matchRequest.setTypeOfMatch(1);
		matchRequest.setMembers(50);
		matchRequest.setMembersAgeFrom(19);
		matchRequest.setMembersAgeTo(22);		OpponentType opponentType = OpponentType.TO_TEAM;		matchRequest.setOpponentType(opponentType);
        LocalDateTime currentDateTime = LocalDateTime.now();
        long amountToAdd = 3; // Change this to the desired amount
        ChronoUnit unit = ChronoUnit.DAYS; // Change this to the desired unit

        LocalDateTime futureDateTime = currentDateTime.plus(amountToAdd, unit);

		matchRequest.setMatchTime(futureDateTime);
		matchRequest.setLocation("Shennoi nagar ground");
		matchRequest.setInformation("100 rs betting");
        Exception exception = assertThrows(Exception.class, () -> {
        	matchRequestServ.create(matchRequest, 1);
        });
        
        String exceptedMessage = "Invalid members count";
		String actualMessage = exception.getMessage();
		assertTrue(exceptedMessage.equals(actualMessage));
    }
	
	@Test
	 public void createMatchRequestWithLessMembersFromAge() {
			MatchRequestService matchRequestServ = new MatchRequestService();
			MatchRequest matchRequest = new MatchRequest();
			
			matchRequest.setCreatedBy(1);
			matchRequest.setAddressId(4);
			matchRequest.setTypeOfMatch(1);
			matchRequest.setMembers(10);
			matchRequest.setMembersAgeFrom(7);
			matchRequest.setMembersAgeTo(22);		OpponentType opponentType = OpponentType.TO_TEAM;		matchRequest.setOpponentType(opponentType);
	        LocalDateTime currentDateTime = LocalDateTime.now();
	        long amountToAdd = 3; // Change this to the desired amount
	        ChronoUnit unit = ChronoUnit.DAYS; // Change this to the desired unit

	        LocalDateTime futureDateTime = currentDateTime.plus(amountToAdd, unit);

			matchRequest.setMatchTime(futureDateTime);
			matchRequest.setLocation("Shennoi nagar ground");
			matchRequest.setInformation("100 rs betting");
	        Exception exception = assertThrows(Exception.class, () -> {
	        	matchRequestServ.create(matchRequest, 1);
	        });
	        
	        String exceptedMessage = "Invalid members from age";
			String actualMessage = exception.getMessage();
			assertTrue(exceptedMessage.equals(actualMessage));
	    }
	 
	@Test
	 public void createMatchRequestWithMoreMembersFromAge() {
			MatchRequestService matchRequestServ = new MatchRequestService();
			MatchRequest matchRequest = new MatchRequest();
			
			matchRequest.setCreatedBy(1);
			matchRequest.setAddressId(4);
			matchRequest.setTypeOfMatch(1);
			matchRequest.setMembers(10);
			matchRequest.setMembersAgeFrom(70);
			matchRequest.setMembersAgeTo(22);		OpponentType opponentType = OpponentType.TO_TEAM;		matchRequest.setOpponentType(opponentType);
	        LocalDateTime currentDateTime = LocalDateTime.now();
	        long amountToAdd = 3; // Change this to the desired amount
	        ChronoUnit unit = ChronoUnit.DAYS; // Change this to the desired unit

	        LocalDateTime futureDateTime = currentDateTime.plus(amountToAdd, unit);

			matchRequest.setMatchTime(futureDateTime);
			matchRequest.setLocation("Shennoi nagar ground");
			matchRequest.setInformation("100 rs betting");
	        Exception exception = assertThrows(Exception.class, () -> {
	        	matchRequestServ.create(matchRequest, 1);
	        });
	        
	        String exceptedMessage = "Invalid members from age";
			String actualMessage = exception.getMessage();
			assertTrue(exceptedMessage.equals(actualMessage));
	    }
	
	@Test
	 public void createMatchRequestWithLessMembersToAge() {
			MatchRequestService matchRequestServ = new MatchRequestService();
			MatchRequest matchRequest = new MatchRequest();
			
			matchRequest.setCreatedBy(1);
			matchRequest.setAddressId(4);
			matchRequest.setTypeOfMatch(1);
			matchRequest.setMembers(10);
			matchRequest.setMembersAgeFrom(18);
			matchRequest.setMembersAgeTo(7);
	        LocalDateTime currentDateTime = LocalDateTime.now();
	        long amountToAdd = 3; // Change this to the desired amount
	        ChronoUnit unit = ChronoUnit.DAYS; // Change this to the desired unit

	        LocalDateTime futureDateTime = currentDateTime.plus(amountToAdd, unit);

			matchRequest.setMatchTime(futureDateTime);
			matchRequest.setLocation("Shennoi nagar ground");
			matchRequest.setInformation("100 rs betting");
	        Exception exception = assertThrows(Exception.class, () -> {
	        	matchRequestServ.create(matchRequest, 1);
	        });
	        
	        String exceptedMessage = "Invalid members to age";
			String actualMessage = exception.getMessage();
			assertTrue(exceptedMessage.equals(actualMessage));
	    }
	 
	@Test
	 public void createMatchRequestWithMoreMembersToAge() {
			MatchRequestService matchRequestServ = new MatchRequestService();
			MatchRequest matchRequest = new MatchRequest();
			
			matchRequest.setCreatedBy(1);
			matchRequest.setAddressId(4);
			matchRequest.setTypeOfMatch(1);
			matchRequest.setMembers(10);
			matchRequest.setMembersAgeFrom(19);
			matchRequest.setMembersAgeTo(70);
	        LocalDateTime currentDateTime = LocalDateTime.now();
	        long amountToAdd = 3; // Change this to the desired amount
	        ChronoUnit unit = ChronoUnit.DAYS; // Change this to the desired unit

	        LocalDateTime futureDateTime = currentDateTime.plus(amountToAdd, unit);

			matchRequest.setMatchTime(futureDateTime);
			matchRequest.setLocation("Shennoi nagar ground");
			matchRequest.setInformation("100 rs betting");
	        Exception exception = assertThrows(Exception.class, () -> {
	        	matchRequestServ.create(matchRequest, 1);
	        });
	        
	        String exceptedMessage = "Invalid members to age";
			String actualMessage = exception.getMessage();
			assertTrue(exceptedMessage.equals(actualMessage));
	    }
	
	@Test
	 public void createMatchRequestWithPastMatchDateAndTime() {
			MatchRequestService matchRequestServ = new MatchRequestService();
			MatchRequest matchRequest = new MatchRequest();
			
			matchRequest.setCreatedBy(1);
			matchRequest.setAddressId(4);
			matchRequest.setTypeOfMatch(1);
			matchRequest.setMembers(10);
			matchRequest.setMembersAgeFrom(19);
			matchRequest.setMembersAgeTo(22);		OpponentType opponentType = OpponentType.TO_TEAM;		matchRequest.setOpponentType(opponentType);
	        LocalDateTime currentDateTime = LocalDateTime.now();
	        long amountToAdd = -1; // Change this to the desired amount
	        ChronoUnit unit = ChronoUnit.DAYS; // Change this to the desired unit

	        LocalDateTime futureDateTime = currentDateTime.plus(amountToAdd, unit);

			matchRequest.setMatchTime(futureDateTime);
			matchRequest.setLocation("Shennoi nagar ground");
			matchRequest.setInformation("100 rs betting");
	        Exception exception = assertThrows(Exception.class, () -> {
	        	matchRequestServ.create(matchRequest, 1);
	        });
	        
	        String exceptedMessage = "Match time should be in future";
			String actualMessage = exception.getMessage();
			assertTrue(exceptedMessage.equals(actualMessage));
	    }
	
	@Test
	 public void createMatchRequestWithLocationNullValue() {
			MatchRequestService matchRequestServ = new MatchRequestService();
			MatchRequest matchRequest = new MatchRequest();
			
			matchRequest.setCreatedBy(1);
			matchRequest.setAddressId(4);
			matchRequest.setTypeOfMatch(1);
			matchRequest.setMembers(10);
			matchRequest.setMembersAgeFrom(19);
			matchRequest.setMembersAgeTo(22);		OpponentType opponentType = OpponentType.TO_TEAM;		matchRequest.setOpponentType(opponentType);
	        LocalDateTime currentDateTime = LocalDateTime.now();
	        long amountToAdd = 3; // Change this to the desired amount
	        ChronoUnit unit = ChronoUnit.DAYS; // Change this to the desired unit

	        LocalDateTime futureDateTime = currentDateTime.plus(amountToAdd, unit);

			matchRequest.setMatchTime(futureDateTime);
			matchRequest.setLocation(null);
			matchRequest.setInformation("100 rs betting");
	        Exception exception = assertThrows(Exception.class, () -> {
	        	matchRequestServ.create(matchRequest, 1);
	        });
	        
	        String exceptedMessage = "Match location can't be null or empty";
			String actualMessage = exception.getMessage();
			assertTrue(exceptedMessage.equals(actualMessage));
	    }
	
	@Test
	 public void createMatchRequestWithLocationEmptyValue() {
			MatchRequestService matchRequestServ = new MatchRequestService();
			MatchRequest matchRequest = new MatchRequest();
			
			matchRequest.setCreatedBy(1);
			matchRequest.setAddressId(4);
			matchRequest.setTypeOfMatch(1);
			matchRequest.setMembers(10);
			matchRequest.setMembersAgeFrom(19);
			matchRequest.setMembersAgeTo(22);		OpponentType opponentType = OpponentType.TO_TEAM;		matchRequest.setOpponentType(opponentType);
	        LocalDateTime currentDateTime = LocalDateTime.now();
	        long amountToAdd = 3; // Change this to the desired amount
	        ChronoUnit unit = ChronoUnit.DAYS; // Change this to the desired unit

	        LocalDateTime futureDateTime = currentDateTime.plus(amountToAdd, unit);

			matchRequest.setMatchTime(futureDateTime);
			matchRequest.setLocation("  ");
			matchRequest.setInformation("100 rs betting");
	        Exception exception = assertThrows(Exception.class, () -> {
	        	matchRequestServ.create(matchRequest, 1);
	        });
	        
	        String exceptedMessage = "Match location can't be null or empty";
			String actualMessage = exception.getMessage();
			assertTrue(exceptedMessage.equals(actualMessage));
	    }
	
	@Test
	 public void createMatchRequestWithLessLocationChar() {
			MatchRequestService matchRequestServ = new MatchRequestService();
			MatchRequest matchRequest = new MatchRequest();
			
			matchRequest.setCreatedBy(1);
			matchRequest.setAddressId(4);
			matchRequest.setTypeOfMatch(1);
			matchRequest.setMembers(10);
			matchRequest.setMembersAgeFrom(19);
			matchRequest.setMembersAgeTo(22);		OpponentType opponentType = OpponentType.TO_TEAM;		matchRequest.setOpponentType(opponentType);
	        LocalDateTime currentDateTime = LocalDateTime.now();
	        long amountToAdd = 3; // Change this to the desired amount
	        ChronoUnit unit = ChronoUnit.DAYS; // Change this to the desired unit

	        LocalDateTime futureDateTime = currentDateTime.plus(amountToAdd, unit);

			matchRequest.setMatchTime(futureDateTime);
			matchRequest.setLocation("asd");
			matchRequest.setInformation("100 rs betting");
	        Exception exception = assertThrows(Exception.class, () -> {
	        	matchRequestServ.create(matchRequest, 1);
	        });
	        
	        String exceptedMessage = "Location char length does not match pattern";
			String actualMessage = exception.getMessage();
			assertTrue(exceptedMessage.equals(actualMessage));
	    }
		
	@Test
	 public void createMatchRequestWithMoreLocationChar() {
			MatchRequestService matchRequestServ = new MatchRequestService();
			MatchRequest matchRequest = new MatchRequest();
			
			matchRequest.setCreatedBy(1);
			matchRequest.setAddressId(4);
			matchRequest.setTypeOfMatch(1);
			matchRequest.setMembers(10);
			matchRequest.setMembersAgeFrom(19);
			matchRequest.setMembersAgeTo(22);		OpponentType opponentType = OpponentType.TO_TEAM;		matchRequest.setOpponentType(opponentType);
	        LocalDateTime currentDateTime = LocalDateTime.now();
	        long amountToAdd = 3; // Change this to the desired amount
	        ChronoUnit unit = ChronoUnit.DAYS; // Change this to the desired unit

	        LocalDateTime futureDateTime = currentDateTime.plus(amountToAdd, unit);

			matchRequest.setMatchTime(futureDateTime);
			matchRequest.setLocation("asdsdkjldskfmsdlkfjslkdfjsljdfnsjfnsfnjnskjnknlasdlfblahbsddsffdsfsdfsdfsdfkjdsfksjdfnsljdfnskjfnjsdjnsdjljndsjjkkjnsdfjnlsdjfnsldjfnlsjdf");
			matchRequest.setInformation("100 rs betting");
	        Exception exception = assertThrows(Exception.class, () -> {
	        	matchRequestServ.create(matchRequest, 1);
	        });
	        
	        String exceptedMessage = "Location char length does not match pattern";
			String actualMessage = exception.getMessage();
			assertTrue(exceptedMessage.equals(actualMessage));
	    }
	
	@Test
	 public void createMatchRequestWithMoreInformationChar() {
			MatchRequestService matchRequestServ = new MatchRequestService();
			MatchRequest matchRequest = new MatchRequest();
			
			matchRequest.setCreatedBy(1);
			matchRequest.setAddressId(4);
			matchRequest.setTypeOfMatch(1);
			matchRequest.setMembers(10);
			matchRequest.setMembersAgeFrom(19);
			matchRequest.setMembersAgeTo(22);		OpponentType opponentType = OpponentType.TO_TEAM;		matchRequest.setOpponentType(opponentType);
	        LocalDateTime currentDateTime = LocalDateTime.now();
	        long amountToAdd = 3; // Change this to the desired amount
	        ChronoUnit unit = ChronoUnit.DAYS; // Change this to the desired unit

	        LocalDateTime futureDateTime = currentDateTime.plus(amountToAdd, unit);

			matchRequest.setMatchTime(futureDateTime);
			matchRequest.setLocation("gvhgvhgvhvhjhjh");
			matchRequest.setInformation("100 rs bettingasdsdkjldskfmsdlkfjslkdfjsljdfnsjfnsfnjnskjnknlasdlfblahbsddsffdsfsdfsdfsdfkjdsfksjdfnsljdfnskjfnjsdjnsdjljndsjjkkjnsdfjnlsdjfnsldjfnlsjdf");
	        Exception exception = assertThrows(Exception.class, () -> {
	        	matchRequestServ.create(matchRequest, 1);
	        });
	        
	        String exceptedMessage = "Information char length does not match pattern";
			String actualMessage = exception.getMessage();
			assertTrue(exceptedMessage.equals(actualMessage));
	    }
	
	@Test
	 public void createMatchRequestWithInvalidPlayerId() {
			MatchRequestService matchRequestServ = new MatchRequestService();
			MatchRequest matchRequest = new MatchRequest();
			
			matchRequest.setCreatedBy(1);
			matchRequest.setAddressId(4);
			matchRequest.setTypeOfMatch(1);
			matchRequest.setMembers(10);
			matchRequest.setMembersAgeFrom(19);
			matchRequest.setMembersAgeTo(22);
			OpponentType opponentType = OpponentType.TO_TEAM;
			matchRequest.setOpponentType(opponentType);
	        LocalDateTime currentDateTime = LocalDateTime.now();
	        long amountToAdd = 3; // Change this to the desired amount
	        ChronoUnit unit = ChronoUnit.DAYS; // Change this to the desired unit

	        LocalDateTime futureDateTime = currentDateTime.plus(amountToAdd, unit);

			matchRequest.setMatchTime(futureDateTime);
			matchRequest.setLocation("gvhgvhgvhvhjhjh");
			matchRequest.setInformation("100 rs betting");
	        Exception exception = assertThrows(Exception.class, () -> {
	        	matchRequestServ.create(matchRequest, -1);
	        });
	        
	        String exceptedMessage = "Invalid player id";
			String actualMessage = exception.getMessage();
			assertTrue(exceptedMessage.equals(actualMessage));
	    }
	
	@Test
	 public void createMatchRequestWithInvalidToTeamId() {
			MatchRequestService matchRequestServ = new MatchRequestService();
			MatchRequest matchRequest = new MatchRequest();
			
			matchRequest.setCreatedBy(1);
			matchRequest.setToTeam(-12);
			matchRequest.setTypeOfMatch(1);
			matchRequest.setMembers(10);
			matchRequest.setMembersAgeFrom(19);
			matchRequest.setMembersAgeTo(22);
			OpponentType opponentType = OpponentType.TO_TEAM;
			matchRequest.setOpponentType(opponentType);
	        LocalDateTime currentDateTime = LocalDateTime.now();
	        long amountToAdd = 3; // Change this to the desired amount
	        ChronoUnit unit = ChronoUnit.DAYS; // Change this to the desired unit

	        LocalDateTime futureDateTime = currentDateTime.plus(amountToAdd, unit);

			matchRequest.setMatchTime(futureDateTime);
			matchRequest.setLocation("gvhgvhgvhvhjhjh");
			matchRequest.setInformation("100 rs betting");
	        Exception exception = assertThrows(Exception.class, () -> {
	        	matchRequestServ.create(matchRequest, 1);
	        });
	        
	        String exceptedMessage = "Invalid to team id";
			String actualMessage = exception.getMessage();
			assertTrue(exceptedMessage.equals(actualMessage));
	    }
	
	@Test
	 public void createMatchRequestWithInvalidToAddressId() {
			MatchRequestService matchRequestServ = new MatchRequestService();
			MatchRequest matchRequest = new MatchRequest();
			
			matchRequest.setCreatedBy(1);
			matchRequest.setAddressId(-1);;
			matchRequest.setTypeOfMatch(1);
			matchRequest.setMembers(10);
			matchRequest.setMembersAgeFrom(19);
			matchRequest.setMembersAgeTo(22);
			OpponentType opponentType = OpponentType.TO_AREA;
			matchRequest.setOpponentType(opponentType);
	        LocalDateTime currentDateTime = LocalDateTime.now();
	        long amountToAdd = 3; // Change this to the desired amount
	        ChronoUnit unit = ChronoUnit.DAYS; // Change this to the desired unit

	        LocalDateTime futureDateTime = currentDateTime.plus(amountToAdd, unit);

			matchRequest.setMatchTime(futureDateTime);
			matchRequest.setLocation("gvhgvhgvhvhjhjh");
			matchRequest.setInformation("100 rs betting");
	        Exception exception = assertThrows(Exception.class, () -> {
	        	matchRequestServ.create(matchRequest, 1);
	        });
	        
	        String exceptedMessage = "Invalid to address id";
			String actualMessage = exception.getMessage();
			assertTrue(exceptedMessage.equals(actualMessage));
	    }
	
	@Test
	 public void createMatchRequestWithNotExistPlayerId() {
			MatchRequestService matchRequestServ = new MatchRequestService();
			MatchRequest matchRequest = new MatchRequest();
			
			matchRequest.setCreatedBy(1);
			matchRequest.setAddressId(4);
			matchRequest.setTypeOfMatch(1);
			matchRequest.setMembers(10);
			matchRequest.setMembersAgeFrom(19);
			matchRequest.setMembersAgeTo(22);		OpponentType opponentType = OpponentType.TO_TEAM;		matchRequest.setOpponentType(opponentType);
	        LocalDateTime currentDateTime = LocalDateTime.now();
	        long amountToAdd = 3; // Change this to the desired amount
	        ChronoUnit unit = ChronoUnit.DAYS; // Change this to the desired unit

	        LocalDateTime futureDateTime = currentDateTime.plus(amountToAdd, unit);

			matchRequest.setMatchTime(futureDateTime);
			matchRequest.setLocation("gvhgvhgvhvhjhjh");
			matchRequest.setInformation("100 rs betting");
	        Exception exception = assertThrows(Exception.class, () -> {
	        	matchRequestServ.create(matchRequest, 100);
	        });
	        
	        String exceptedMessage = "Player not exist";
			String actualMessage = exception.getMessage();
			assertTrue(exceptedMessage.equals(actualMessage));
	    }
	
	@Test
	 public void createMatchRequestWithNotCaptainOfAnyTeam() {
			MatchRequestService matchRequestServ = new MatchRequestService();
			MatchRequest matchRequest = new MatchRequest();
			
			matchRequest.setCreatedBy(1);
			matchRequest.setAddressId(4);
			matchRequest.setTypeOfMatch(1);
			matchRequest.setMembers(10);
			matchRequest.setMembersAgeFrom(19);
			matchRequest.setMembersAgeTo(22);
			OpponentType opponentType = OpponentType.TO_AREA;
			matchRequest.setOpponentType(opponentType);
	        LocalDateTime currentDateTime = LocalDateTime.now();
	        long amountToAdd = 3; // Change this to the desired amount
	        ChronoUnit unit = ChronoUnit.DAYS; // Change this to the desired unit

	        LocalDateTime futureDateTime = currentDateTime.plus(amountToAdd, unit);

			matchRequest.setMatchTime(futureDateTime);
			matchRequest.setLocation("gvhgvhgvhvhjhjh");
			matchRequest.setInformation("100 rs betting");
	        Exception exception = assertThrows(Exception.class, () -> {
	        	matchRequestServ.create(matchRequest, 5);
	        });
	        
	        String exceptedMessage = "Player not captain of any team";
			String actualMessage = exception.getMessage();
			assertTrue(exceptedMessage.equals(actualMessage));
	    }
	
	@Test
	 public void createMatchRequestWithNotExistCreatedById() {
			MatchRequestService matchRequestServ = new MatchRequestService();
			MatchRequest matchRequest = new MatchRequest();
			
			matchRequest.setCreatedBy(100);
			matchRequest.setAddressId(4);
			matchRequest.setTypeOfMatch(1);
			matchRequest.setMembers(10);
			matchRequest.setMembersAgeFrom(19);
			matchRequest.setMembersAgeTo(22);		OpponentType opponentType = OpponentType.TO_TEAM;		matchRequest.setOpponentType(opponentType);
	        LocalDateTime currentDateTime = LocalDateTime.now();
	        long amountToAdd = 3; // Change this to the desired amount
	        ChronoUnit unit = ChronoUnit.DAYS; // Change this to the desired unit

	        LocalDateTime futureDateTime = currentDateTime.plus(amountToAdd, unit);

			matchRequest.setMatchTime(futureDateTime);
			matchRequest.setLocation("gvhgvhgvhvhjhjh");
			matchRequest.setInformation("100 rs betting");
	        Exception exception = assertThrows(Exception.class, () -> {
	        	matchRequestServ.create(matchRequest, 1);
	        });
	        
	        String exceptedMessage = "Created by id not exist";
			String actualMessage = exception.getMessage();
			assertTrue(exceptedMessage.equals(actualMessage));
	    }
	
	@Test
	 public void createMatchRequestWithNotCaptianOfThisTeam() {
			MatchRequestService matchRequestServ = new MatchRequestService();
			MatchRequest matchRequest = new MatchRequest();
			
			matchRequest.setCreatedBy(1);
			matchRequest.setAddressId(4);
			matchRequest.setTypeOfMatch(1);
			matchRequest.setMembers(10);
			matchRequest.setMembersAgeFrom(19);
			matchRequest.setMembersAgeTo(22);		OpponentType opponentType = OpponentType.TO_AREA;		matchRequest.setOpponentType(opponentType);
	        LocalDateTime currentDateTime = LocalDateTime.now();
	        long amountToAdd = 3; // Change this to the desired amount
	        ChronoUnit unit = ChronoUnit.DAYS; // Change this to the desired unit

	        LocalDateTime futureDateTime = currentDateTime.plus(amountToAdd, unit);

			matchRequest.setMatchTime(futureDateTime);
			matchRequest.setLocation("gvhgvhgvhvhjhjh");
			matchRequest.setInformation("100 rs betting");
	        Exception exception = assertThrows(Exception.class, () -> {
	        	matchRequestServ.create(matchRequest, 2);
	        });
	        
	        String exceptedMessage = "Captain id not match with the team member captain id";
			String actualMessage = exception.getMessage();
			assertTrue(exceptedMessage.equals(actualMessage));
	    }
	
	@Test
	 public void createMatchRequestWithCreateTeamAndSentTeamSame() {
			MatchRequestService matchRequestServ = new MatchRequestService();
			MatchRequest matchRequest = new MatchRequest();
			
			matchRequest.setCreatedBy(1);
			matchRequest.setToTeam(1);
			matchRequest.setTypeOfMatch(1);
			matchRequest.setMembers(10);
			matchRequest.setMembersAgeFrom(19);
			matchRequest.setMembersAgeTo(22);		OpponentType opponentType = OpponentType.TO_TEAM;		matchRequest.setOpponentType(opponentType);
	        LocalDateTime currentDateTime = LocalDateTime.now();
	        long amountToAdd = 3; // Change this to the desired amount
	        ChronoUnit unit = ChronoUnit.DAYS; // Change this to the desired unit

	        LocalDateTime futureDateTime = currentDateTime.plus(amountToAdd, unit);

			matchRequest.setMatchTime(futureDateTime);
			matchRequest.setLocation("gvhgvhgvhvhjhjh");
			matchRequest.setInformation("100 rs betting");
	        Exception exception = assertThrows(Exception.class, () -> {
	        	matchRequestServ.create(matchRequest, 1);
	        });
	        
	        String exceptedMessage = "Created team and sent team same";
			String actualMessage = exception.getMessage();
			assertTrue(exceptedMessage.equals(actualMessage));
	    }
	
	@Test
	 public void createMatchRequestWithToTeamNotExist() {
			MatchRequestService matchRequestServ = new MatchRequestService();
			MatchRequest matchRequest = new MatchRequest();
			
			matchRequest.setCreatedBy(1);
			matchRequest.setToTeam(100);
			matchRequest.setTypeOfMatch(1);
			matchRequest.setMembers(10);
			matchRequest.setMembersAgeFrom(19);
			matchRequest.setMembersAgeTo(22);		OpponentType opponentType = OpponentType.TO_TEAM;		matchRequest.setOpponentType(opponentType);
	        LocalDateTime currentDateTime = LocalDateTime.now();
	        long amountToAdd = 3; // Change this to the desired amount
	        ChronoUnit unit = ChronoUnit.DAYS; // Change this to the desired unit

	        LocalDateTime futureDateTime = currentDateTime.plus(amountToAdd, unit);

			matchRequest.setMatchTime(futureDateTime);
			matchRequest.setLocation("gvhgvhgvhvhjhjh");
			matchRequest.setInformation("100 rs betting");
	        Exception exception = assertThrows(Exception.class, () -> {
	        	matchRequestServ.create(matchRequest, 1);
	        });
	        
	        String exceptedMessage = "To team not exist";
			String actualMessage = exception.getMessage();
			assertTrue(exceptedMessage.equals(actualMessage));
	    }
	
	@Test
	 public void createMatchRequestWithNotExistAreaId() {
			MatchRequestService matchRequestServ = new MatchRequestService();
			MatchRequest matchRequest = new MatchRequest();
			
			matchRequest.setCreatedBy(1);
			matchRequest.setAddressId(100);
			matchRequest.setTypeOfMatch(1);
			matchRequest.setMembers(10);
			matchRequest.setMembersAgeFrom(19);
			matchRequest.setMembersAgeTo(22);
			OpponentType opponentType = OpponentType.TO_TEAM;
			matchRequest.setOpponentType(opponentType);
	        LocalDateTime currentDateTime = LocalDateTime.now();
	        long amountToAdd = 3; // Change this to the desired amount
	        ChronoUnit unit = ChronoUnit.DAYS; // Change this to the desired unit

	        LocalDateTime futureDateTime = currentDateTime.plus(amountToAdd, unit);

			matchRequest.setMatchTime(futureDateTime);
			matchRequest.setLocation("gvhgvhgvhvhjhjh");
			matchRequest.setInformation("100 rs betting");
	        Exception exception = assertThrows(Exception.class, () -> {
	        	matchRequestServ.create(matchRequest, 1);
	        });
	        
	        String exceptedMessage = "Address not exist";
			String actualMessage = exception.getMessage();
			assertTrue(exceptedMessage.equals(actualMessage));
	    }
	
	
	
//	@Test
//	public void GetAllOpenMAtchRequest() throws Exception {
//		MatchRequestService matchRequestServ = new MatchRequestService();
//		matchRequestServ.listAllOpenRequest();
//	}
}
