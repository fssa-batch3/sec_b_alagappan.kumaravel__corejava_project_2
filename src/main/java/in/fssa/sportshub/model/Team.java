package in.fssa.sportshub.model;

import java.time.LocalDate;

public class Team {
	private int id;
	private String teamName;
	private String Url;
	private Address address = new Address();
	private String about;
	private LocalDate createdAt;
	private LocalDate modifiedAt;
	private int createdBy;
	private int modifiedBy;
	private boolean isActive;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public Address getAddress() {
		return address;
	}
	public void setAddress(Address address) {
		this.address = address;
	}
	public String getTeamName() {
		return teamName;
	}
	public void setTeamName(String teamName) {
		this.teamName = teamName;
	}
	public String getUrl() {
		return Url;
	}
	public void setUrl(String url) {
		Url = url;
	}
	public String getAbout() {
		return about;
	}
	public void setAbout(String about) {
		this.about = about;
	}
	public LocalDate getCreatedAt() {
		return createdAt;
	}
	public void setCreatedAt(LocalDate createdAt) {
		this.createdAt = createdAt;
	}
	public LocalDate getModifiedAt() {
		return modifiedAt;
	}
	public void setModifiedAt(LocalDate modifiedAt) {
		this.modifiedAt = modifiedAt;
	}
	public int getCreatedBy() {
		return createdBy;
	}
	public void setCreatedBy(int createdBy) {
		this.createdBy = createdBy;
	}
	public int getModifiedBy() {
		return modifiedBy;
	}
	public void setModifiedBy(int modifiedBy) {
		this.modifiedBy = modifiedBy;
	}
	public boolean isActive() {
		return isActive;
	}
	public void setActive(boolean isActive) {
		this.isActive = isActive;
	}
	@Override
	public String toString() {
		return "Team [id=" + id + ", teamName=" + teamName + ", Url=" + Url + ", Address="
				+ address + ", about=" + about + ", createdAt=" + createdAt
				+ ", modifiedAt=" + modifiedAt + ", createdBy=" + createdBy + ", modifiedBy=" + modifiedBy
				+ ", isActive=" + isActive + "]";
	}
	
}
