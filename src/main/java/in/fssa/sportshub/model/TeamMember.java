package in.fssa.sportshub.model;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class TeamMember {
	private int id;
	private int teamId;
	private int userId;
	private int isCaptain;
	private int isActive;
	private int requestStatus;
	private String createdAt;
	private String startDate;
	private String endDate;
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
	
	public int getRequestStatus() {
		return requestStatus;
	}
	public void setRequestStatus(int requestStatus) {
		this.requestStatus = requestStatus;
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
	
	public LocalDateTime getStartDate() {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
		LocalDateTime localDateTime = null;
		 try {
	             localDateTime = LocalDateTime.parse(startDate, formatter);

	        } catch (DateTimeParseException e) {
	            System.err.println("Error parsing LocalDateTime: " + e.getMessage());
	        }
		return localDateTime;
	}
	
	public void setStartDate(LocalDateTime startDate) {
		
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
		this.startDate = startDate.format(formatter);
	}
	
	public LocalDateTime getEndDate() {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
		LocalDateTime localDateTime = null;
		 try {
	             localDateTime = LocalDateTime.parse(endDate, formatter);

	        } catch (DateTimeParseException e) {
	            System.err.println("Error parsing LocalDateTime: " + e.getMessage());
	        }
		return localDateTime;
	}
	public void setEndDate(LocalDateTime endDate) {
		
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
		this.endDate = endDate.format(formatter);
	}
	@Override
	public String toString() {
		return "TeamMember [id=" + id + ", teamId=" + teamId + ", userId=" + userId + ", isCaptain=" + isCaptain
				+ ", isActive=" + isActive + ", requestStatus=" + requestStatus + ", createdAt=" + createdAt
				+ ", startDate=" + startDate + ", endDate=" + endDate + "]";
	}
	
	
	
	
}
