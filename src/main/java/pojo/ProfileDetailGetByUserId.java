package pojo;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ProfileDetailGetByUserId {

	@JsonProperty("userName")
	private String userName;

	@JsonProperty("accountId")
	private String accountId;

	@JsonProperty("email")
	private String email;

	@JsonProperty("firstName")
	private String firstName;

	@JsonProperty("middleName")
	private String middleName;

	@JsonProperty("lastName")
	private String lastName;

	@JsonProperty("address")
	private String address;

	@JsonProperty("pinCode")
	private String pinCode;

	@JsonProperty("countryId")
	private String countryId;

	@JsonProperty("countryName")
	private String countryName;

	@JsonProperty("stateId")
	private String stateId;

	@JsonProperty("stateName")
	private String stateName;

	@JsonProperty("cityId")
	private String cityId;

	@JsonProperty("cityName")
	private String cityName;

	public ProfileDetailGetByUserId() {
		// Default constructor
	}

	// Getters and Setters
	@JsonProperty("userName")
	public String getUserName() {
		return userName;
	}

	@JsonProperty("userName")
	public void setUserName(String userName) {
		this.userName = userName;
	}

	@JsonProperty("accountId")
	public String getAccountId() {
		return accountId;
	}

	@JsonProperty("accountId")
	public void setAccountId(String accountId) {
		this.accountId = accountId;
	}

	@JsonProperty("email")
	public String getEmail() {
		return email;
	}

	@JsonProperty("email")
	public void setEmail(String email) {
		this.email = email;
	}

	@JsonProperty("firstName")
	public String getFirstName() {
		return firstName;
	}

	@JsonProperty("firstName")
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	@JsonProperty("middleName")
	public String getMiddleName() {
		return middleName;
	}

	@JsonProperty("middleName")
	public void setMiddleName(String middleName) {
		this.middleName = middleName;
	}

	@JsonProperty("lastName")
	public String getLastName() {
		return lastName;
	}

	@JsonProperty("lastName")
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	@JsonProperty("address")
	public String getAddress() {
		return address;
	}

	@JsonProperty("address")
	public void setAddress(String address) {
		this.address = address;
	}

	@JsonProperty("pinCode")
	public String getPinCode() {
		return pinCode;
	}

	@JsonProperty("pinCode")
	public void setPinCode(String pinCode) {
		this.pinCode = pinCode;
	}

	@JsonProperty("countryId")
	public String getCountryId() {
		return countryId;
	}

	@JsonProperty("countryId")
	public void setCountryId(String countryId) {
		this.countryId = countryId;
	}

	@JsonProperty("countryName")
	public String getCountryName() {
		return countryName;
	}

	@JsonProperty("countryName")
	public void setCountryName(String countryName) {
		this.countryName = countryName;
	}

	@JsonProperty("stateId")
	public String getStateId() {
		return stateId;
	}

	@JsonProperty("stateId")
	public void setStateId(String stateId) {
		this.stateId = stateId;
	}

	@JsonProperty("stateName")
	public String getStateName() {
		return stateName;
	}

	@JsonProperty("stateName")
	public void setStateName(String stateName) {
		this.stateName = stateName;
	}

	@JsonProperty("cityId")
	public String getCityId() {
		return cityId;
	}

	@JsonProperty("cityId")
	public void setCityId(String cityId) {
		this.cityId = cityId;
	}

	@JsonProperty("cityName")
	public String getCityName() {
		return cityName;
	}

	@JsonProperty("cityName")
	public void setCityName(String cityName) {
		this.cityName = cityName;
	}

}