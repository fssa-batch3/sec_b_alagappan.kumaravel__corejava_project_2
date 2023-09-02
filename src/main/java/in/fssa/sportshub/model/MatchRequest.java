package in.fssa.sportshub.model;

import java.time.LocalDate;
import java.time.LocalDateTime;


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
	private LocalDateTime matchTime;
	private String location;
	private String information;
	private LocalDateTime createdAt;
	private LocalDateTime modifiedAt;
	
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
	public boolean isStatus() {
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
		return matchTime;
	}
	public void setMatchTime(LocalDateTime matchTime) {
		this.matchTime = matchTime;
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
		return createdAt;
	}
	public void setCreatedAt(LocalDateTime createdAt) {
		this.createdAt = createdAt;
	}
	public LocalDateTime getModifiedAt() {
		return modifiedAt;
	}
	public void setModifiedAt(LocalDateTime modifiedAt) {
		this.modifiedAt = modifiedAt;
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
