package in.fssa.sportshub.model;

public class TeamMember {
	private int id;
	private int teamId;
	private int userId;
	private int isCaptain;
	private int isActive;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getTeamId() {
		return teamId;
	}
	public void setTeamId(int teamId) {
		this.teamId = teamId;
	}
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	public int getIsCaptain() {
		return isCaptain;
	}
	public void setIsCaptain(int isCaptain) {
		this.isCaptain = isCaptain;
	}
	public int getIsActive() {
		return isActive;
	}
	public void setIsActive(int isActive) {
		this.isActive = isActive;
	}
	@Override
	public String toString() {
		return "TeamMember [id=" + id + ", teamId=" + teamId + ", userId=" + userId + ", isCaptain=" + isCaptain
				+ ", isActive=" + isActive + "]";
	}
	
}
