package com.example.petstore.account.web;

import com.example.petstore.account.Account;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Null;
import jakarta.validation.constraints.Size;

public class AccountForm {

	public interface NewAccount {

	}

	public interface EditAccount {

	}

	@Null(groups = EditAccount.class, message = "Username cannot be changed")
	@NotNull(groups = NewAccount.class, message = "Username is required")
	@Size(min = 1, max = 25, message = "Username must be between 1 and 25 characters")
	private String username;

	@NotNull(message = "Password is required")
	@Size(min = 1, max = 25, groups = NewAccount.class, message = "Password must be between 1 and 25 characters")
	@Size(min = 0, max = 25, groups = EditAccount.class, message = "Password must be between 0 and 25 characters")
	private String password;

	@NotNull(message = "Please confirm your password")
	@Size(min = 1, max = 25, groups = NewAccount.class, message = "Password must be between 1 and 25 characters")
	@Size(min = 0, max = 25, groups = EditAccount.class, message = "Password must be between 0 and 25 characters")
	private String repeatedPassword;

	@NotNull(message = "First name is required")
	@Size(min = 1, max = 80, message = "First name must be between 1 and 80 characters")
	private String firstName;

	@NotNull(message = "Last name is required")
	@Size(min = 1, max = 80, message = "Last name must be between 1 and 80 characters")
	private String lastName;

	@NotNull(message = "Email is required")
	@Size(min = 1, max = 80, message = "Email must be between 1 and 80 characters")
	@Email(message = "Invalid email format")
	private String email;

	@NotNull(message = "Phone number is required")
	@Size(min = 1, max = 80, message = "Phone number must be between 1 and 80 characters")
	private String phone;

	@NotNull(message = "Address is required")
	@Size(min = 1, max = 80, message = "Address must be between 1 and 80 characters")
	private String address1;

	@Size(max = 40, message = "Address 2 must be at most 40 characters")
	private String address2;

	@NotNull(message = "City is required")
	@Size(min = 1, max = 80, message = "City must be between 1 and 80 characters")
	private String city;

	@NotNull(message = "State is required")
	@Size(min = 1, max = 80, message = "State must be between 1 and 80 characters")
	private String state;

	@NotNull(message = "Zip code is required")
	@Size(min = 1, max = 20, message = "Zip code must be between 1 and 20 characters")
	private String zip;

	@NotNull(message = "Country is required")
	@Size(min = 1, max = 20, message = "Country must be between 1 and 20 characters")
	private String country;

	@NotNull(message = "Language preference is required")
	@Size(min = 1, max = 80, message = "Language preference must be between 1 and 80 characters")
	private String languagePreference;

	@NotNull(message = "Favourite category is required")
	@Size(min = 1, max = 30, message = "Favourite category must be between 1 and 30 characters")
	private String favouriteCategoryId;

	private boolean listOption;

	private boolean bannerOption;

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getRepeatedPassword() {
		return repeatedPassword;
	}

	public void setRepeatedPassword(String repeatedPassword) {
		this.repeatedPassword = repeatedPassword;
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

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getAddress1() {
		return address1;
	}

	public void setAddress1(String address1) {
		this.address1 = address1;
	}

	public String getAddress2() {
		return address2;
	}

	public void setAddress2(String address2) {
		this.address2 = address2;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getZip() {
		return zip;
	}

	public void setZip(String zip) {
		this.zip = zip;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getLanguagePreference() {
		return languagePreference;
	}

	public void setLanguagePreference(String languagePreference) {
		this.languagePreference = languagePreference;
	}

	public String getFavouriteCategoryId() {
		return favouriteCategoryId;
	}

	public void setFavouriteCategoryId(String favouriteCategoryId) {
		this.favouriteCategoryId = favouriteCategoryId;
	}

	public boolean isListOption() {
		return listOption;
	}

	public void setListOption(boolean listOption) {
		this.listOption = listOption;
	}

	public boolean isBannerOption() {
		return bannerOption;
	}

	public void setBannerOption(boolean bannerOption) {
		this.bannerOption = bannerOption;
	}

	public void copyFrom(Account account) {
		this.username = account.getUsername();
		this.firstName = account.getFirstName();
		this.lastName = account.getLastName();
		this.email = account.getEmail();
		this.phone = account.getPhone();
		this.address1 = account.getAddress1();
		this.address2 = account.getAddress2();
		this.city = account.getCity();
		this.state = account.getState();
		this.zip = account.getZip();
		this.country = account.getCountry();
		this.languagePreference = account.getLanguagePreference();
		this.favouriteCategoryId = account.getFavouriteCategoryId();
		this.listOption = account.isListOption();
		this.bannerOption = account.isBannerOption();
	}

	public Account.Builder toAccount() {
		return Account.builder()
			.username(this.username)
			.password(this.password)
			.firstName(this.firstName)
			.lastName(this.lastName)
			.email(this.email)
			.phone(this.phone)
			.address1(this.address1)
			.address2(this.address2)
			.city(this.city)
			.state(this.state)
			.zip(this.zip)
			.country(this.country)
			.languagePreference(this.languagePreference)
			.favouriteCategoryId(this.favouriteCategoryId)
			.listOption(this.listOption)
			.bannerOption(this.bannerOption);
	}

}
