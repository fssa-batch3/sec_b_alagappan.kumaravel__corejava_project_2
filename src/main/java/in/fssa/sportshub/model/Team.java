package in.fssa.sportshub.model;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class Team {
	private int id;
	private String teamName;
	private String Url;
	private Address address = new Address();
	private String about;
	private String openForPlayerDescription;
	private boolean openForPlayerStatus;
	private String createdAt;
	private String modifiedAt;
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
	
	public String getOpenForPlayerDescription() {
		return openForPlayerDescription;
	}
	public void setOpenForPlayerDescription(String openForPlayerDescription) {
		this.openForPlayerDescription = openForPlayerDescription;
	}
	public boolean isOpenForPlayerStatus() {
		return openForPlayerStatus;
	}
	public void setOpenForPlayerStatus(boolean openForPlayerStatus) {
		this.openForPlayerStatus = openForPlayerStatus;
	}
	public LocalDateTime getCreatedAt() {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
		LocalDateTime localDateTime = null;
		 try {
	             localDateTime = LocalDateTime.parse(createdAt, formatter);

	        } catch (DateTimeParseException e) {
	            System.err.println("Error parsing LocalDateTime: " + e.getMessage());
	        }
		return localDateTime;
	}
	public void setCreatedAt(LocalDateTime createdAt) {
		
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
		this.createdAt = createdAt.format(formatter);
	}
	public LocalDateTime getModifiedAt() {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
		LocalDateTime localDateTime = null;
		 try {
	             localDateTime = LocalDateTime.parse(modifiedAt, formatter);

	        } catch (DateTimeParseException e) {
	            System.err.println("Error parsing LocalDateTime: " + e.getMessage());
	        }
		return localDateTime;
	}
	public void setModifiedAt(LocalDateTime modifiedAt) {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
		this.modifiedAt = modifiedAt.format(formatter);
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
		return "Team [id=" + id + ", teamName=" + teamName + ", Url=" + Url + ", address=" + address + ", about="
				+ about + ", openForPlayerDescription=" + openForPlayerDescription + ", openForPlayerStatus="
				+ openForPlayerStatus + ", createdAt=" + createdAt + ", modifiedAt=" + modifiedAt + ", createdBy="
				+ createdBy + ", modifiedBy=" + modifiedBy + ", isActive=" + isActive + "]";
	}
	
	

	
}
