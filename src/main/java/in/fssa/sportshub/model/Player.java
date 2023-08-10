package in.fssa.sportshub.model;

import java.time.LocalDate;
import java.time.LocalDateTime;

import in.fssa.sportshub.enumm.Gender;

public class Player {
	private int id;
	private long phoneNumber;
	private String userName;
	private String firstName;
	private String lastName;
	private String Url;
	private String Password;
	private Gender gender;
	private Address address = new Address();
	private LocalDate dateOfBirth;
	private String about;
	private LocalDateTime createdAt;
	private LocalDateTime modifiedAt;
	private boolean isActive;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	
	public void setGender(Gender gender) {
		this.gender = gender;
	}
	public Gender getGender() {
		return gender;
	}
	public long getPhoneNumber() {
		return phoneNumber;
	}
	public void setPhoneNumber(long phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public String getUrl() {
		return Url;
	}
	public void setUrl(String url) {
		Url = url;
	}
	public String getPassword() {
		return Password;
	}
	public void setPassword(String password) {
		Password = password;
	}
	public Address getAddress() {
		return address;
	}
	public void setAddress(Address address) {
		this.address = address;
	}
	public LocalDate getDateOfBirth() {
		return dateOfBirth;
	}
	public void setDateOfBirth(LocalDate dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}
	public String getAbout() {
		return about;
	}
	public void setAbout(String about) {
		this.about = about;
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
	public boolean isActive() {
		return isActive;
	}
	public void setActive(boolean isActive) {
		this.isActive = isActive;
	}
	@Override
	public String toString() {
		return "Player [id=" + id + ", phoneNumber=" + phoneNumber + ", userName=" + userName + ", firstName="
				+ firstName + ", lastName=" + lastName + ", Url=" + Url + ", Password=" + Password + ", gender="
				+ gender + ", address=" + address + ", dateOfBirth=" + dateOfBirth + ", about=" + about + ", createdAt="
				+ createdAt + ", modifiedAt=" + modifiedAt + ", isActive=" + isActive + "]";
	}
	
}
