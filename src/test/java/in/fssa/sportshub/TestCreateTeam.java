package in.fssa.sportshub;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import in.fssa.sportshub.model.Team;
import in.fssa.sportshub.service.TeamService;

public class TestCreateTeam {
	@Test
	public void createTeamWithValidData() throws Exception {
		TeamService teamService = new TeamService();
		
		Team team = new Team();

		team.setTeamName("Sharks");// here change team name
		team.setUrl("shssssdsfdsd");
		team.getAddress().setArea("AnnaNagar");
		team.getAddress().setDistrict("Chennai");
		team.setAbout("Playing cricket");
		team.setCreatedBy(1);// here change team name
		team.setModifiedBy(1);
		assertDoesNotThrow(()->{
			teamService.create(team);
		});
	}
	
	@Test
    public void createTeamWithNullValues() {
		TeamService teamService = new TeamService();

        Exception exception = assertThrows(Exception.class, () -> {
        	teamService.create(null);
        });
        
        String exceptedMessage = "Invalid team input";
		String actualMessage = exception.getMessage();
		assertTrue(exceptedMessage.equals(actualMessage));
    }
	
	@Test
	public void createTeamWithTeamNameValueNull() throws Exception {
		TeamService teamService = new TeamService();
		
		Team team = new Team();

		team.setTeamName(null);
		team.setUrl("shssssdsfdsd");
		team.getAddress().setArea("Aminjikarai");
		team.getAddress().setDistrict("Chennai");
		team.setAbout("Playing cricket");
		team.setCreatedBy(2);
		team.setModifiedBy(2);
		Exception exception = assertThrows(Exception.class, () -> {
			teamService.create(team);
        });
        
        String exceptedMessage = "Team name can't be null or empty";
		String actualMessage = exception.getMessage();
		System.out.println(actualMessage);
		assertTrue(exceptedMessage.equals(actualMessage));
	}
	
	@Test
	public void createTeamWithTeamNameValueEmpty() throws Exception {
		TeamService teamService = new TeamService();
		
		Team team = new Team();

		team.setTeamName("  ");
		team.setUrl("shssssdsfdsd");
		team.getAddress().setArea("Aminjikarai");
		team.getAddress().setDistrict("Chennai");
		team.setAbout("Playing cricket");
		team.setCreatedBy(2);
		team.setModifiedBy(2);
		Exception exception = assertThrows(Exception.class, () -> {
			teamService.create(team);
        });
        
        String exceptedMessage = "Team name can't be null or empty";
		String actualMessage = exception.getMessage();
		assertTrue(exceptedMessage.equals(actualMessage));
	}
	
	@Test
	public void createTeamWithNumericTeamName() throws Exception {
		TeamService teamService = new TeamService();
		
		Team team = new Team();

		team.setTeamName("India12ns");
		team.setUrl("shssssdsfdsd");
		team.getAddress().setArea("Aminjikarai");
		team.getAddress().setDistrict("Chennai");
		team.setAbout("Playing cricket");
		team.setCreatedBy(2);
		team.setModifiedBy(2);
		Exception exception = assertThrows(Exception.class, () -> {
			teamService.create(team);
        });
        
        String exceptedMessage = "Team name does not match pattern";
		String actualMessage = exception.getMessage();
		assertTrue(exceptedMessage.equals(actualMessage));
	}
	
	@Test
	public void createTeamWithLessTeamNameCharacterLength() throws Exception {
		TeamService teamService = new TeamService();
		
		Team team = new Team();

		team.setTeamName("Ind");
		team.setUrl("shssssdsfdsd");
		team.getAddress().setArea("Aminjikarai");
		team.getAddress().setDistrict("Chennai");
		team.setAbout("Playing cricket");
		team.setCreatedBy(2);
		team.setModifiedBy(2);
		Exception exception = assertThrows(Exception.class, () -> {
			teamService.create(team);
        });
		
        String exceptedMessage = "Team name length does not match pattern";
		String actualMessage = exception.getMessage();
		assertTrue(exceptedMessage.equals(actualMessage));
	}
	
	@Test
	public void createTeamWithMoreTeamNameCharacterLength() throws Exception {
		TeamService teamService = new TeamService();
		
		Team team = new Team();

		team.setTeamName("Indiansjvjgvjhgvjgvjgvjhvjhvhgvjhvgvhgvjhvjhbvjjhvbjhh");
		team.setUrl("shssssdsfdsd");
		team.getAddress().setArea("Aminjikarai");
		team.getAddress().setDistrict("Chennai");
		team.setAbout("Playing cricket");
		team.setCreatedBy(2);
		team.setModifiedBy(2);
		Exception exception = assertThrows(Exception.class, () -> {
			teamService.create(team);
        });
        
        String exceptedMessage = "Team name length does not match pattern";
		String actualMessage = exception.getMessage();
		assertTrue(exceptedMessage.equals(actualMessage));
	}
	
	@Test
	public void createTeamWithMoreAboutStringLength() throws Exception {
		TeamService teamService = new TeamService();
		
		Team team = new Team();

		team.setTeamName("Indians");
		team.setUrl("shssssdsfdsd");
		team.getAddress().setArea("Aminjikarai");
		team.getAddress().setDistrict("Chennai");
		team.setAbout("Playing cricket dfsdkjnhjfbvjsdvbsjdhvbsxdsdfjdnfksdjnsdkfjnsdfkjnsdkjndjnfojnfsojdnskdjfnskjdnfskjdnfskjdfnskjdnfksjdfnsjgygfwejfsygsdhbasdfilysgoqr");
		team.setCreatedBy(2);
		team.setModifiedBy(2);
		Exception exception = assertThrows(Exception.class, () -> {
			teamService.create(team);
        });
		
		String exceptedMessage = "About team data length does not match pattern";
		String actualMessage = exception.getMessage();
		assertTrue(exceptedMessage.equals(actualMessage));
	}
	
	@Test
	public void createTeamWithAddressValueNull() throws Exception {
		TeamService teamService = new TeamService();
		
		Team team = new Team();

		team.setTeamName("Indians");
		team.setUrl("shssssdsfdsd");
		team.setAddress(null);
		team.setAbout("Playing cricket");
		team.setCreatedBy(2);
		team.setModifiedBy(2);
		Exception exception = assertThrows(Exception.class, () -> {
			teamService.create(team);
        });
        
        String exceptedMessage = "Invalid Address input";
		String actualMessage = exception.getMessage();
		assertTrue(exceptedMessage.equals(actualMessage));
	}
	
	@Test
	public void createTeamWithAreaValueNull() throws Exception {
		TeamService teamService = new TeamService();
		
		Team team = new Team();

		team.setTeamName("Indians");
		team.setUrl("shssssdsfdsd");
		team.getAddress().setArea(null);
		team.getAddress().setDistrict("Chennai");
		team.setAbout("Playing cricket");
		team.setCreatedBy(2);
		team.setModifiedBy(2);
		Exception exception = assertThrows(Exception.class, () -> {
			teamService.create(team);
        });
        
        String exceptedMessage = "Area can't be null or empty";
		String actualMessage = exception.getMessage();
		assertTrue(exceptedMessage.equals(actualMessage));
	}
	
	@Test
	public void createTeamWithAreaValueEmpty() throws Exception {
		TeamService teamService = new TeamService();
		
		Team team = new Team();

		team.setTeamName("Indians");
		team.setUrl("shssssdsfdsd");
		team.getAddress().setArea("  ");
		team.getAddress().setDistrict("Chennai");
		team.setAbout("Playing cricket");
		team.setCreatedBy(2);
		team.setModifiedBy(2);
		Exception exception = assertThrows(Exception.class, () -> {
			teamService.create(team);
        });
        
        String exceptedMessage = "Area can't be null or empty";
		String actualMessage = exception.getMessage();
		assertTrue(exceptedMessage.equals(actualMessage));
	}
	
	@Test
	public void createTeamWithNumericalAreaValue() throws Exception {
		TeamService teamService = new TeamService();
		
		Team team = new Team();

		team.setTeamName("Indians");
		team.setUrl("shssssdsfdsd");
		team.getAddress().setArea("Ami354karai");
		team.getAddress().setDistrict("Chennai");
		team.setAbout("Playing cricket");
		team.setCreatedBy(2);
		team.setModifiedBy(2);
		Exception exception = assertThrows(Exception.class, () -> {
			teamService.create(team);
        });
        
        String exceptedMessage = "Area does not match pattern";
		String actualMessage = exception.getMessage();
		assertTrue(exceptedMessage.equals(actualMessage));
	}
	
	@Test
	public void createTeamWithDistrictValueNull() throws Exception {
		TeamService teamService = new TeamService();
		
		Team team = new Team();

		team.setTeamName("Indians");
		team.setUrl("shssssdsfdsd");
		team.getAddress().setArea("Aminjikarai");
		team.getAddress().setDistrict(null);
		team.setAbout("Playing cricket");
		team.setCreatedBy(2);
		team.setModifiedBy(2);
		Exception exception = assertThrows(Exception.class, () -> {
			teamService.create(team);
        });
        
        String exceptedMessage = "District can't be null or empty";
		String actualMessage = exception.getMessage();
		System.out.println(actualMessage);
		assertTrue(exceptedMessage.equals(actualMessage));
	}
	
	@Test
	public void createTeamWithDistrictValueEmpty() throws Exception {
		TeamService teamService = new TeamService();
		
		Team team = new Team();

		team.setTeamName("Indians");
		team.setUrl("shssssdsfdsd");
		team.getAddress().setArea("Aminjikarai");
		team.getAddress().setDistrict("  ");
		team.setAbout("Playing cricket");
		team.setCreatedBy(2);
		team.setModifiedBy(2);
		Exception exception = assertThrows(Exception.class, () -> {
			teamService.create(team);
        });
        
        String exceptedMessage = "District can't be null or empty";
		String actualMessage = exception.getMessage();
		assertTrue(exceptedMessage.equals(actualMessage));
	}
	
	@Test
	public void createTeamWithNumericalDistrictValue() throws Exception {
		TeamService teamService = new TeamService();
		
		Team team = new Team();

		team.setTeamName("Indians");
		team.setUrl("shssssdsfdsd");
		team.getAddress().setArea("Aminjikarai");
		team.getAddress().setDistrict("Chen467nai");
		team.setAbout("Playing cricket");
		team.setCreatedBy(2);
		team.setModifiedBy(2);
		Exception exception = assertThrows(Exception.class, () -> {
			teamService.create(team);
        });
        
        String exceptedMessage = "District does not match pattern";
		String actualMessage = exception.getMessage();
		assertTrue(exceptedMessage.equals(actualMessage));
	}
	
	@Test
	public void createTeamWithLessAreaCharacterLength() throws Exception {
		TeamService teamService = new TeamService();
		
		Team team = new Team();

		team.setTeamName("Indians");
		team.setUrl("shssssdsfdsd");
		team.getAddress().setArea("Am");
		team.getAddress().setDistrict("Chennai");
		team.setAbout("Playing cricket");
		team.setCreatedBy(2);
		team.setModifiedBy(2);
		Exception exception = assertThrows(Exception.class, () -> {
			teamService.create(team);
        });
        
        String exceptedMessage = "Area length does not match pattern";
		String actualMessage = exception.getMessage();
		assertTrue(exceptedMessage.equals(actualMessage));
	}
	@Test
	public void createTeamWithMoreAreaCharacterLength() throws Exception {
		TeamService teamService = new TeamService();
		
		Team team = new Team();

		team.setTeamName("Indians");
		team.setUrl("shssssdsfdsd");
		team.getAddress().setArea("Aminjikaraitfjhgvhkgvjhgfvkjgfhjgxdgfufhfghgfjhggjhgjhgjhghjhgkhgjh");
		team.getAddress().setDistrict("Chennai");
		team.setAbout("Playing cricket");
		team.setCreatedBy(2);
		team.setModifiedBy(2);
		Exception exception = assertThrows(Exception.class, () -> {
			teamService.create(team);
        });
        
        String exceptedMessage = "Area length does not match pattern";
		String actualMessage = exception.getMessage();
		assertTrue(exceptedMessage.equals(actualMessage));
	}
	
	@Test
	public void createTeamWithLessDistrictCharacterLength() throws Exception {
		TeamService teamService = new TeamService();
		
		Team team = new Team();

		team.setTeamName("Indians");
		team.setUrl("shssssdsfdsd");
		team.getAddress().setArea("Aminjikarai");
		team.getAddress().setDistrict("Ch");
		team.setAbout("Playing cricket");
		team.setCreatedBy(2);
		team.setModifiedBy(2);
		Exception exception = assertThrows(Exception.class, () -> {
			teamService.create(team);
        });
        
        String exceptedMessage = "District length does not match pattern";
		String actualMessage = exception.getMessage();
		assertTrue(exceptedMessage.equals(actualMessage));
	}
	@Test
	public void createTeamWithMoreDistrictCharacterLength() throws Exception {
		TeamService teamService = new TeamService();
		
		Team team = new Team();

		team.setTeamName("Indians");
		team.setUrl("shssssdsfdsd");
		team.getAddress().setArea("Aminjikarai");
		team.getAddress().setDistrict("chinjikaraitfjhgvhkgvjhgfvkjgfhjgxdgfufhfghgfjhggjhgjhgjhghjhgkhgjh");
		team.setAbout("Playing cricket");
		team.setCreatedBy(2);
		team.setModifiedBy(2);
		Exception exception = assertThrows(Exception.class, () -> {
			teamService.create(team);
        });
        
        String exceptedMessage = "District length does not match pattern";
		String actualMessage = exception.getMessage();
		assertTrue(exceptedMessage.equals(actualMessage));
	}
	
	@Test
	public void createTeamWithInvalidCreatedById() throws Exception {
		TeamService teamService = new TeamService();
		
		Team team = new Team();

		team.setTeamName("Indians");
		team.setUrl("shssssdsfdsd");
		team.getAddress().setArea("Aminjikarai");
		team.getAddress().setDistrict("chennai");
		team.setAbout("Playing cricket");
		team.setCreatedBy(-1);
		team.setModifiedBy(2);
		Exception exception = assertThrows(Exception.class, () -> {
			teamService.create(team);
        });
        
        String exceptedMessage = "Invalid create player id";
		String actualMessage = exception.getMessage();
		assertTrue(exceptedMessage.equals(actualMessage));
	}
	
	@Test
	public void createTeamWithNotExistingPlayer() throws Exception {
		TeamService teamService = new TeamService();
		
		Team team = new Team();

		team.setTeamName("Indians");
		team.setUrl("shssssdsfdsd");
		team.getAddress().setArea("Aminjikarai");
		team.getAddress().setDistrict("chennai");
		team.setAbout("Playing cricket");
		team.setCreatedBy(100);
		team.setModifiedBy(2);
		Exception exception = assertThrows(Exception.class, () -> {
			teamService.create(team);
        });
        
        String exceptedMessage = "Player not exist";
		String actualMessage = exception.getMessage();
		assertTrue(exceptedMessage.equals(actualMessage));
	}
	
	@Test
	public void createTeamWithAlreadyCaptainInTeam() throws Exception {
		TeamService teamService = new TeamService();
		
		Team team = new Team();

		team.setTeamName("Indians");
		team.setUrl("shssssdsfdsd");
		team.getAddress().setArea("Aminjikarai");
		team.getAddress().setDistrict("chennai");
		team.setAbout("Playing cricket");
		team.setCreatedBy(1);
		team.setModifiedBy(2);
		Exception exception = assertThrows(Exception.class, () -> {
			teamService.create(team);
        });
        
        String exceptedMessage = "Player already a captain in team";
		String actualMessage = exception.getMessage();
		assertTrue(exceptedMessage.equals(actualMessage));
	}
	
	@Test
	public void createTeamWithExistingTeamName() throws Exception {
		TeamService teamService = new TeamService();
		
		Team team = new Team();

		team.setTeamName("Indians");
		team.setUrl("shssssdsfdsd");
		team.getAddress().setArea("Aminjikarai");
		team.getAddress().setDistrict("chennai");
		team.setAbout("Playing cricket");
		team.setCreatedBy(3);//here should change
		team.setModifiedBy(3);
		Exception exception = assertThrows(Exception.class, () -> {
			teamService.create(team);
        });
        
        String exceptedMessage = "Team name already exist";
		String actualMessage = exception.getMessage();
		assertTrue(exceptedMessage.equals(actualMessage));
	}
}
