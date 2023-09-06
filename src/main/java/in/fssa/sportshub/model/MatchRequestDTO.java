package in.fssa.sportshub.model;

public class MatchRequestDTO extends MatchRequest {
	
	Team createdTeam;
	Team opponentTeam;
	Player createdTeamCaptain;
	Player opponentTeamCaptain;
	Address ToAreaAddress;
	int statusOfResponse;
	
	public Team getCreatedTeam() {
		return createdTeam;
	}
	public void setCreatedTeam(Team createdTeam) {
		this.createdTeam = createdTeam;
	}
	public Team getOpponentTeam() {
		return opponentTeam;
	}
	public void setOpponentTeam(Team opponentTeam) {
		this.opponentTeam = opponentTeam;
	}
	public Player getCreatedTeamCaptain() {
		return createdTeamCaptain;
	}
	public void setCreatedTeamCaptain(Player createdTeamCaptain) {
		this.createdTeamCaptain = createdTeamCaptain;
	}
	public Player getOpponentTeamCaptain() {
		return opponentTeamCaptain;
	}
	public void setOpponentTeamCaptain(Player opponentTeamCaptain) {
		this.opponentTeamCaptain = opponentTeamCaptain;
	}
	public Address getToAreaAddress() {
		return ToAreaAddress;
	}
	public void setToAreaAddress(Address toAreaAddress) {
		ToAreaAddress = toAreaAddress;
	}
	
	
	public int getStatusOfResponse() {
		return statusOfResponse;
	}
	public void setStatusOfResponse(int status_of_response) {
		this.statusOfResponse = status_of_response;
	}
	@Override
	public String toString() {
		return "MatchRequestDTO [createdTeam=" + createdTeam + ", opponentTeam=" + opponentTeam
				+ ", createdTeamCaptain=" + createdTeamCaptain + ", opponentTeamCaptain=" + opponentTeamCaptain
				+ ", ToAreaAddress=" + ToAreaAddress + "]";
	}
	

}
