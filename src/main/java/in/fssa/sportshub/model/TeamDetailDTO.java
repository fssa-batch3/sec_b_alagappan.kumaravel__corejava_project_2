package in.fssa.sportshub.model;

import java.util.HashSet;
import java.util.Set;

public class TeamDetailDTO extends Team{
	
	private String captainName;
	int teamCaptainRelId;
	int teamCaptainId;
	boolean currentTeam;
	
	Set<Player> teamMembers = new HashSet<>();

	public String getCaptainName() {
		return captainName;
	}

	public void setCaptainName(String captainName) {
		this.captainName = captainName;
	}

	
	public int getTeamCaptainId() {
		return teamCaptainId;
	}

	public void setTeamCaptainId(int teamCaptainId) {
		this.teamCaptainId = teamCaptainId;
	}

	public int getTeamCaptainRelId() {
		return teamCaptainRelId;
	}

	public void setTeamCaptainRelId(int teamCaptainRelId) {
		this.teamCaptainRelId = teamCaptainRelId;
	}

	public Set<Player> getTeamMembers() {
		return teamMembers;
	}

	public void setTeamMembers(Set<Player> teamMembers) {
		this.teamMembers = teamMembers;
	}

	public boolean isCurrentTeam() {
		return currentTeam;
	}

	public void setCurrentTeam(boolean currentTeam) {
		this.currentTeam = currentTeam;
	}

	@Override
	public String toString() {
		return "TeamDetailDTO [captainName=" + captainName + ", teamCaptainRelId=" + teamCaptainRelId
				+ ", teamCaptainId=" + teamCaptainId + ", currentTeam=" + currentTeam + ", teamMembers=" + teamMembers
				+ "]";
	}

}
