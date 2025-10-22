package com.example.petstore.account.web;

import com.example.petstore.account.Account;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Null;
import jakarta.validation.constraints.Size;
import org.hibernate.validator.constraints.Email;

public class AccountForm {

	public interface NewAccount {

	}

	public interface EditAccount {

	}

	@Null(groups = EditAccount.class)
	@NotNull(groups = NewAccount.class)
	@Size(min = 1, max = 25)
	private String username;

	@NotNull
	@Size(min = 1, max = 25, groups = NewAccount.class)
	@Size(min = 0, max = 25, groups = EditAccount.class)
	private String password;

	@NotNull
	@Size(min = 1, max = 25, groups = NewAccount.class)
	@Size(min = 0, max = 25, groups = EditAccount.class)
	private String repeatedPassword;

	@NotNull
	@Size(min = 1, max = 80)
	private String firstName;

	@NotNull
	@Size(min = 1, max = 80)
	private String lastName;

	@NotNull
	@Size(min = 1, max = 80)
	@Email
	private String email;

	@NotNull
	@Size(min = 1, max = 80)
	private String phone;

	@NotNull
	@Size(min = 1, max = 80)
	private String address1;

	@NotNull
	@Size(min = 1, max = 40)
	private String address2;

	@NotNull
	@Size(min = 1, max = 80)
	private String city;

	@NotNull
	@Size(min = 1, max = 80)
	private String state;

	@NotNull
	@Size(min = 1, max = 20)
	private String zip;

	@NotNull
	@Size(min = 1, max = 20)
	private String country;

	@NotNull
	@Size(min = 1, max = 80)
	private String languagePreference;

	@NotNull
	@Size(min = 1, max = 30)
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
