package in.fssa.sportshub.model;

import java.time.LocalDate;

public class MatchRequest {
	private int id;
	private int createdBy;
	private int toTeam;
	private String area;
	private String district;
	private int addressId;
	private boolean Status;
	private char typeOfMatch;
	private int members; 
	private int membersAgeFrom; 
	private int membersAgeTo; 
	private LocalDate matchTime;
	private String location;
	private String information;
	private LocalDate createdAt;
	private LocalDate modifiedAt;
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
	public String getArea() {
		return area;
	}
	public void setArea(String area) {
		this.area = area;
	}
	public String getDistrict() {
		return district;
	}
	public void setDistrict(String district) {
		this.district = district;
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
	public char getTypeOfMatch() {
		return typeOfMatch;
	}
	public void setTypeOfMatch(char typeOfMatch) {
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
	public LocalDate getMatchTime() {
		return matchTime;
	}
	public void setMatchTime(LocalDate matchTime) {
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
	@Override
	public String toString() {
		return "MatchRequest [id=" + id + ", createdBy=" + createdBy + ", toTeam=" + toTeam + ", area=" + area
				+ ", district=" + district + ", addressId=" + addressId + ", Status=" + Status + ", typeOfMatch="
				+ typeOfMatch + ", members=" + members + ", membersAgeFrom=" + membersAgeFrom + ", membersAgeTo="
				+ membersAgeTo + ", matchTime=" + matchTime + ", location=" + location + ", information=" + information
				+ ", createdAt=" + createdAt + ", modifiedAt=" + modifiedAt + "]";
	}
	
}
