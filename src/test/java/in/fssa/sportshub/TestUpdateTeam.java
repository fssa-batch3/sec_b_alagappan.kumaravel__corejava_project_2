package in.fssa.sportshub;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import in.fssa.sportshub.model.Team;
import in.fssa.sportshub.service.TeamService;

public class TestUpdateTeam {

	@Test
	public void updateTeamWithValidData() throws Exception {
		TeamService teamService = new TeamService();
		
		Team team = new Team();
		team.setId(1);
		team.setTeamName("Sharks");
		team.setUrl("https://iili.io/HGUnV71.png");
		team.getAddress().setArea("Aminjikarai");
		team.getAddress().setDistrict("Chennai");
		team.setAbout("Playing Street cricket");
		team.setOpenForPlayerStatus(true);
		team.setOpenForPlayerDescription("we need good bowlers");
		team.setModifiedBy(1);
		assertDoesNotThrow(()->{
			teamService.update(team);
		});
	}
	
	@Test
    public void updateTeamWithNullValues() {
		TeamService teamService = new TeamService();

        Exception exception = assertThrows(Exception.class, () -> {
        	teamService.update(null);
        });
        
        String exceptedMessage = "Invalid team input";
		String actualMessage = exception.getMessage();
		System.out.println(actualMessage);
		assertTrue(exceptedMessage.equals(actualMessage));
    }
	
	@Test
	public void updateTeamWithTeamNameValueNull() throws Exception {
		TeamService teamService = new TeamService();
		
		Team team = new Team();

		team.setTeamName(null);
		team.setUrl("shssssdsfdsd");
		team.getAddress().setArea("Aminjikarai");
		team.getAddress().setDistrict("Chennai");
		team.setAbout("Playing cricket");
		team.setId(1);
		team.setModifiedBy(1);
		Exception exception = assertThrows(Exception.class, () -> {
			teamService.update(team);
        });
        
        String exceptedMessage = "Team name can't be null or empty";
		String actualMessage = exception.getMessage();
		System.out.println(actualMessage);
		assertTrue(exceptedMessage.equals(actualMessage));
	}
	
	@Test
	public void updateTeamWithTeamNameValueEmpty() throws Exception {
		TeamService teamService = new TeamService();
		
		Team team = new Team();

		team.setTeamName("  ");
		team.setUrl("shssssdsfdsd");
		team.getAddress().setArea("Aminjikarai");
		team.getAddress().setDistrict("Chennai");
		team.setAbout("Playing cricket");
		team.setId(1);
		team.setModifiedBy(1);
		Exception exception = assertThrows(Exception.class, () -> {
			teamService.update(team);
        });
        
        String exceptedMessage = "Team name can't be null or empty";
		String actualMessage = exception.getMessage();
		assertTrue(exceptedMessage.equals(actualMessage));
	}
	
	@Test
	public void updateTeamWithNumericTeamName() throws Exception {
		TeamService teamService = new TeamService();
		
		Team team = new Team();

		team.setTeamName("India12ns");
		team.setUrl("shssssdsfdsd");
		team.getAddress().setArea("Aminjikarai");
		team.getAddress().setDistrict("Chennai");
		team.setAbout("Playing cricket");
		team.setId(1);
		team.setModifiedBy(1);
		Exception exception = assertThrows(Exception.class, () -> {
			teamService.update(team);
        });
        
        String exceptedMessage = "Team name does not match pattern";
		String actualMessage = exception.getMessage();
		assertTrue(exceptedMessage.equals(actualMessage));
	}
	
	@Test
	public void updateTeamWithLessTeamNameCharacterLength() throws Exception {
		TeamService teamService = new TeamService();
		
		Team team = new Team();

		team.setTeamName("Ind");
		team.setUrl("shssssdsfdsd");
		team.getAddress().setArea("Aminjikarai");
		team.getAddress().setDistrict("Chennai");
		team.setAbout("Playing cricket");
		team.setId(1);
		team.setModifiedBy(1);
		Exception exception = assertThrows(Exception.class, () -> {
			teamService.update(team);
        });
		
        String exceptedMessage = "Team name length does not match pattern";
		String actualMessage = exception.getMessage();
		assertTrue(exceptedMessage.equals(actualMessage));
	}
	
	@Test
	public void updateTeamWithMoreTeamNameCharacterLength() throws Exception {
		TeamService teamService = new TeamService();
		
		Team team = new Team();

		team.setTeamName("Indianssssjvjgvjhgvjgvjgvjhvjhvhgvjhvgvhgvjhvjhbvjjhvbjhh");
		team.setUrl("shssssdsfdsd");
		team.getAddress().setArea("Aminjikarai");
		team.getAddress().setDistrict("Chennai");
		team.setAbout("Playing cricket");
		team.setId(1);
		team.setModifiedBy(1);
		Exception exception = assertThrows(Exception.class, () -> {
			teamService.update(team);
        });
        
        String exceptedMessage = "Team name length does not match pattern";
		String actualMessage = exception.getMessage();
		assertTrue(exceptedMessage.equals(actualMessage));
	}
	
	@Test
	public void updateTeamWithMoreAboutStringLength() throws Exception {
		TeamService teamService = new TeamService();
		
		Team team = new Team();

		team.setTeamName("Indianssss");
		team.setUrl("shssssdsfdsd");
		team.getAddress().setArea("Aminjikarai");
		team.getAddress().setDistrict("Chennai");
		team.setAbout("Playing cricket dfsdkjnhjfbvjsdvbsjdhvbsxdsdfjdnfksdjnsdkfjnsdfkjnsdkjndjnfojnfsojdnskdjfnskjdnfskjdnfskjdfnskjdnfksjdfnsjgygfwejfsygsdhbasdfilysgoqr");
		team.setId(1);
		team.setModifiedBy(1);
		Exception exception = assertThrows(Exception.class, () -> {
			teamService.update(team);
        });
		
		String exceptedMessage = "About team data length does not match pattern";
		String actualMessage = exception.getMessage();
		assertTrue(exceptedMessage.equals(actualMessage));
	}
	
	@Test
	public void updateTeamWithAddressValueNull() throws Exception {
		TeamService teamService = new TeamService();
		
		Team team = new Team();

		team.setTeamName("Indianssss");
		team.setUrl("shssssdsfdsd");
		team.setAddress(null);
		team.setAbout("Playing cricket");
		team.setId(1);
		team.setModifiedBy(1);
		Exception exception = assertThrows(Exception.class, () -> {
			teamService.update(team);
        });
        
        String exceptedMessage = "Invalid Address input";
		String actualMessage = exception.getMessage();
		assertTrue(exceptedMessage.equals(actualMessage));
	}
	
	@Test
	public void updateTeamWithAreaValueNull() throws Exception {
		TeamService teamService = new TeamService();
		
		Team team = new Team();

		team.setTeamName("Indianssss");
		team.setUrl("shssssdsfdsd");
		team.getAddress().setArea(null);
		team.getAddress().setDistrict("Chennai");
		team.setAbout("Playing cricket");
		team.setId(1);
		team.setModifiedBy(1);
		Exception exception = assertThrows(Exception.class, () -> {
			teamService.update(team);
        });
        
        String exceptedMessage = "Area can't be null or empty";
		String actualMessage = exception.getMessage();
		assertTrue(exceptedMessage.equals(actualMessage));
	}
	
	@Test
	public void updateTeamWithAreaValueEmpty() throws Exception {
		TeamService teamService = new TeamService();
		
		Team team = new Team();

		team.setTeamName("Indianssss");
		team.setUrl("shssssdsfdsd");
		team.getAddress().setArea("  ");
		team.getAddress().setDistrict("Chennai");
		team.setAbout("Playing cricket");
		team.setId(1);
		team.setModifiedBy(1);
		Exception exception = assertThrows(Exception.class, () -> {
			teamService.update(team);
        });
        
        String exceptedMessage = "Area can't be null or empty";
		String actualMessage = exception.getMessage();
		assertTrue(exceptedMessage.equals(actualMessage));
	}
	
	@Test
	public void updateTeamWithNumericalAreaValue() throws Exception {
		TeamService teamService = new TeamService();
		
		Team team = new Team();

		team.setTeamName("Indianssss");
		team.setUrl("shssssdsfdsd");
		team.getAddress().setArea("Ami154karai");
		team.getAddress().setDistrict("Chennai");
		team.setAbout("Playing cricket");
		team.setId(1);
		team.setModifiedBy(1);
		Exception exception = assertThrows(Exception.class, () -> {
			teamService.update(team);
        });
        
        String exceptedMessage = "Area does not match pattern";
		String actualMessage = exception.getMessage();
		assertTrue(exceptedMessage.equals(actualMessage));
	}
	
	@Test
	public void updateTeamWithDistrictValueNull() throws Exception {
		TeamService teamService = new TeamService();
		
		Team team = new Team();

		team.setTeamName("Indianssss");
		team.setUrl("shssssdsfdsd");
		team.getAddress().setArea("Aminjikarai");
		team.getAddress().setDistrict(null);
		team.setAbout("Playing cricket");
		team.setId(1);
		team.setModifiedBy(1);
		Exception exception = assertThrows(Exception.class, () -> {
			teamService.update(team);
        });
        
        String exceptedMessage = "District can't be null or empty";
		String actualMessage = exception.getMessage();
		System.out.println(actualMessage);
		assertTrue(exceptedMessage.equals(actualMessage));
	}
	
	@Test
	public void updateTeamWithDistrictValueEmpty() throws Exception {
		TeamService teamService = new TeamService();
		
		Team team = new Team();

		team.setTeamName("Indianssss");
		team.setUrl("shssssdsfdsd");
		team.getAddress().setArea("Aminjikarai");
		team.getAddress().setDistrict("  ");
		team.setAbout("Playing cricket");
		team.setId(1);
		team.setModifiedBy(1);
		Exception exception = assertThrows(Exception.class, () -> {
			teamService.update(team);
        });
        
        String exceptedMessage = "District can't be null or empty";
		String actualMessage = exception.getMessage();
		assertTrue(exceptedMessage.equals(actualMessage));
	}
	
	@Test
	public void updateTeamWithNumericalDistrictValue() throws Exception {
		TeamService teamService = new TeamService();
		
		Team team = new Team();

		team.setTeamName("Indianssss");
		team.setUrl("shssssdsfdsd");
		team.getAddress().setArea("Aminjikarai");
		team.getAddress().setDistrict("Chen461nai");
		team.setAbout("Playing cricket");
		team.setId(1);
		team.setModifiedBy(1);
		Exception exception = assertThrows(Exception.class, () -> {
			teamService.update(team);
        });
        
        String exceptedMessage = "District does not match pattern";
		String actualMessage = exception.getMessage();
		assertTrue(exceptedMessage.equals(actualMessage));
	}
	
	@Test
	public void updateTeamWithLessAreaCharacterLength() throws Exception {
		TeamService teamService = new TeamService();
		
		Team team = new Team();

		team.setTeamName("Indianssss");
		team.setUrl("shssssdsfdsd");
		team.getAddress().setArea("Am");
		team.getAddress().setDistrict("Chennai");
		team.setAbout("Playing cricket");
		team.setId(1);
		team.setModifiedBy(1);
		Exception exception = assertThrows(Exception.class, () -> {
			teamService.update(team);
        });
        
        String exceptedMessage = "Area length does not match pattern";
		String actualMessage = exception.getMessage();
		assertTrue(exceptedMessage.equals(actualMessage));
	}
	@Test
	public void updateTeamWithMoreAreaCharacterLength() throws Exception {
		TeamService teamService = new TeamService();
		
		Team team = new Team();

		team.setTeamName("Indianssss");
		team.setUrl("shssssdsfdsd");
		team.getAddress().setArea("Aminjikaraitfjhgvhkgvjhgfvkjgfhjgxdgfufhfghgfjhggjhgjhgjhghjhgkhgjh");
		team.getAddress().setDistrict("Chennai");
		team.setAbout("Playing cricket");
		team.setId(1);
		team.setModifiedBy(3);
		Exception exception = assertThrows(Exception.class, () -> {
			teamService.update(team);
        });
        
        String exceptedMessage = "Area length does not match pattern";
		String actualMessage = exception.getMessage();
		System.out.println(actualMessage);
		assertTrue(exceptedMessage.equals(actualMessage));
	}
	
	@Test
	public void updateTeamWithLessDistrictCharacterLength() throws Exception {
		TeamService teamService = new TeamService();
		
		Team team = new Team();

		team.setTeamName("Indianssss");
		team.setUrl("shssssdsfdsd");
		team.getAddress().setArea("Aminjikarai");
		team.getAddress().setDistrict("Ch");
		team.setAbout("Playing cricket");
		team.setId(1);
		team.setModifiedBy(1);
		Exception exception = assertThrows(Exception.class, () -> {
			teamService.update(team);
        });
        
        String exceptedMessage = "District length does not match pattern";
		String actualMessage = exception.getMessage();
		assertTrue(exceptedMessage.equals(actualMessage));
	}
	@Test
	public void updateTeamWithMoreDistrictCharacterLength() throws Exception {
		TeamService teamService = new TeamService();
		
		Team team = new Team();

		team.setTeamName("Indianssss");
		team.setUrl("shssssdsfdsd");
		team.getAddress().setArea("Aminjikarai");
		team.getAddress().setDistrict("chinjikaraitfjhgvhkgvjhgfvkjgfhjgxdgfufhfghgfjhggjhgjhgjhghjhgkhgjh");
		team.setAbout("Playing cricket");
		team.setId(1);
		team.setModifiedBy(1);
		Exception exception = assertThrows(Exception.class, () -> {
			teamService.update(team);
        });
        
        String exceptedMessage = "District length does not match pattern";
		String actualMessage = exception.getMessage();
		assertTrue(exceptedMessage.equals(actualMessage));
	}
	
	@Test
	public void updateTeamWithInvalidModifiedById() throws Exception {
		TeamService teamService = new TeamService();
		
		Team team = new Team();

		team.setTeamName("Indianssss");
		team.setUrl("shssssdsfdsd");
		team.getAddress().setArea("Aminjikarai");
		team.getAddress().setDistrict("chennai");
		team.setAbout("Playing cricket");
		team.setId(1);
		team.setModifiedBy(-1);
		Exception exception = assertThrows(Exception.class, () -> {
			teamService.update(team);
        });
        
        String exceptedMessage = "Invalid modify player id";
		String actualMessage = exception.getMessage();
		assertTrue(exceptedMessage.equals(actualMessage));
	}
	
	@Test
	public void updateTeamWithNotExistingPlayer() throws Exception {
		TeamService teamService = new TeamService();
		
		Team team = new Team();

		team.setTeamName("Indianssss");
		team.setUrl("shssssdsfdsd");
		team.getAddress().setArea("Aminjikarai");
		team.getAddress().setDistrict("chennai");
		team.setAbout("Playing cricket");
		team.setId(1);
		team.setModifiedBy(100);
		Exception exception = assertThrows(Exception.class, () -> {
			teamService.update(team);
        });
        
        String exceptedMessage = "Player not exist";
		String actualMessage = exception.getMessage();
		assertTrue(exceptedMessage.equals(actualMessage));
	}
	
	@Test
	public void updateTeamWithInvalidTeamId() throws Exception {
		TeamService teamService = new TeamService();
		
		Team team = new Team();

		team.setTeamName("Indianssss");
		team.setUrl("shssssdsfdsd");
		team.getAddress().setArea("Aminjikarai");
		team.getAddress().setDistrict("chennai");
		team.setAbout("Playing cricket");
		team.setId(-1);
		team.setModifiedBy(1);
		Exception exception = assertThrows(Exception.class, () -> {
			teamService.update(team);
        });
        
        String exceptedMessage = "Invalid Team id";
		String actualMessage = exception.getMessage();
		assertTrue(exceptedMessage.equals(actualMessage));
	}
	
	@Test
	public void updateTeamWithNotExistingTeam() throws Exception {
		TeamService teamService = new TeamService();
		
		Team team = new Team();

		team.setTeamName("Indianssss");
		team.setUrl("shssssdsfdsd");
		team.getAddress().setArea("Aminjikarai");
		team.getAddress().setDistrict("chennai");
		team.setAbout("Playing cricket");
		team.setId(100);
		team.setModifiedBy(1);
		Exception exception = assertThrows(Exception.class, () -> {
			teamService.update(team);
        });
        
        String exceptedMessage = "Team not exist";
		String actualMessage = exception.getMessage();
		assertTrue(exceptedMessage.equals(actualMessage));
	}
	
	@Test
	public void updateTeamWithNotCaptainOfTeam() throws Exception {
		TeamService teamService = new TeamService();
		
		Team team = new Team();

		team.setTeamName("Indianssss");
		team.setUrl("shssssdsfdsd");
		team.getAddress().setArea("Aminjikarai");
		team.getAddress().setDistrict("chennai");
		team.setAbout("Playing cricket");
		team.setId(1);
		team.setModifiedBy(2);
		Exception exception = assertThrows(Exception.class, () -> {
			teamService.update(team);
        });
        
        String exceptedMessage = "player not a captian of this team";
		String actualMessage = exception.getMessage();
		System.out.println(actualMessage);
		assertTrue(exceptedMessage.equals(actualMessage));
	}
	
}
