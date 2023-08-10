package in.fssa.sportshub.model;

public class TeamMember {
	private int id;
	private int teamId;
	private int userId;
	private boolean isCaptain;
	private boolean isActive;
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
	public boolean isCaptain() {
		return isCaptain;
	}
	public void setCaptain(boolean isCaptain) {
		this.isCaptain = isCaptain;
	}
	public boolean isActive() {
		return isActive;
	}
	public void setActive(boolean isActive) {
		this.isActive = isActive;
	}
	@Override
	public String toString() {
		return "TeamMember [id=" + id + ", teamId=" + teamId + ", userId=" + userId + ", isCaptain=" + isCaptain
				+ ", isActive=" + isActive + "]";
	}
	
}
