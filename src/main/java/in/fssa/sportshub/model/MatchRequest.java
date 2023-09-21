package in.fssa.sportshub.model;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;


public class MatchRequest {
	private int id;	
	private int createdBy;
	private int toTeam;
	private int addressId;
	private boolean Status;
	private OpponentType opponentType;
	private int typeOfMatch;
	private int members; 
	private int membersAgeFrom; 
	private int membersAgeTo; 
	private String matchTime;
	private String location;
	private String information;
	private String createdAt;
	private String modifiedAt;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getCreatedBy() {
		return createdBy;
	}
	public void setCreatedBy(int createdBy) {
		this.createdBy = createdBy;
	}
	public int getToTeam() {
		return toTeam;
	}
	public void setToTeam(int toTeam) {
		this.toTeam = toTeam;
	}
	
	public int getAddressId() {
		return addressId;
	}
	public void setAddressId(int addressId) {
		this.addressId = addressId;
	}
	public boolean getStatus() {
		return Status;
	}
	public void setStatus(boolean status) {
		Status = status;
	}
	public int getTypeOfMatch() {
		return typeOfMatch;
	}
	public void setTypeOfMatch(int typeOfMatch) {
		this.typeOfMatch = typeOfMatch;
	}
	public int getMembers() {
		return members;
	}
	public void setMembers(int members) {
		this.members = members;
	}
	public int getMembersAgeFrom() {
		return membersAgeFrom;
	}
	public void setMembersAgeFrom(int membersAgeFrom) {
		this.membersAgeFrom = membersAgeFrom;
	}
	public int getMembersAgeTo() {
		return membersAgeTo;
	}
	public void setMembersAgeTo(int membersAgeTo) {
		this.membersAgeTo = membersAgeTo;
	}
	
	public LocalDateTime getMatchTime() {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
		LocalDateTime localDateTime = null;
		 try {
	             localDateTime = LocalDateTime.parse(matchTime, formatter);

	        } catch (DateTimeParseException e) {
	            System.err.println("Error parsing LocalDateTime: " + e.getMessage());
	        }
		return localDateTime;
	}
	public void setMatchTime(LocalDateTime matchTime) {
		
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
		this.matchTime = matchTime.format(formatter);
	}
	
	public String getLocation() {
		return location;
	}
	public void setLocation(String location) {
		this.location = location;
	}
	public String getInformation() {
		return information;
	}
	public void setInformation(String information) {
		this.information = information;
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
	
	
	public OpponentType getOpponentType() {
		return opponentType;
	}
	public void setOpponentType(OpponentType opponentType) {
		this.opponentType = opponentType;
	}
	
	@Override
	public String toString() {
		return "MatchRequest [id=" + id + ", createdBy=" + createdBy + ", toTeam=" + toTeam + ", addressId=" + addressId
				+ ", Status=" + Status + ", opponentType=" + opponentType + ", typeOfMatch=" + typeOfMatch
				+ ", members=" + members + ", membersAgeFrom=" + membersAgeFrom + ", membersAgeTo=" + membersAgeTo
				+ ", matchTime=" + matchTime + ", location=" + location + ", information=" + information
				+ ", createdAt=" + createdAt + ", modifiedAt=" + modifiedAt + "]";
	}
	
	
}
