package in.fssa.sportshub.model;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;


public class Player {
	private int id;
	private long phoneNumber;
	private String userName;
	private String firstName;
	private String lastName;//can null
	private String Url;//can null not validate
	private String Password;
	private Gender gender;// not validate
	private Address address = new Address();
	private String dateOfBirth;
	private String about;//can null
	private String createdAt;
	private String modifiedAt;
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
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		LocalDate localDate = null;
		 try {
			 System.out.println();
	             localDate = LocalDate.parse(dateOfBirth, formatter);

	        } catch (DateTimeParseException e) {
	            System.err.println("Error parsing LocalDateTime: " + e.getMessage());
	        }
		return localDate;	
	}
	public void setDateOfBirth(LocalDate dateOfBirth) {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		this.dateOfBirth = dateOfBirth.format(formatter);	
	}
	public String getAbout() {
		return about;
	}
	public void setAbout(String about) {
		this.about = about;
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
