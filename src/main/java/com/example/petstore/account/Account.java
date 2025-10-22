/*
 *    Copyright 2010-2022 the original author or authors.
 *
 *    Licensed under the Apache License, Version 2.0 (the "License");
 *    you may not use this file except in compliance with the License.
 *    You may obtain a copy of the License at
 *
 *       https://www.apache.org/licenses/LICENSE-2.0
 *
 *    Unless required by applicable law or agreed to in writing, software
 *    distributed under the License is distributed on an "AS IS" BASIS,
 *    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *    See the License for the specific language governing permissions and
 *    limitations under the License.
 */
package com.example.petstore.account;

import java.io.Serial;
import java.io.Serializable;

/**
 * The Class Account.
 *
 * @author Eduardo Macarron
 */
public class Account implements Serializable {

	@Serial
	private static final long serialVersionUID = 8751282105532159742L;

	private String username;

	private String password;

	private String email;

	private String firstName;

	private String lastName;

	private String status;

	private String address1;

	private String address2;

	private String city;

	private String state;

	private String zip;

	private String country;

	private String phone;

	private String favouriteCategoryId;

	private String languagePreference;

	private boolean listOption;

	private boolean bannerOption;

	private String bannerName;

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

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
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

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
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

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getFavouriteCategoryId() {
		return favouriteCategoryId;
	}

	public void setFavouriteCategoryId(String favouriteCategoryId) {
		this.favouriteCategoryId = favouriteCategoryId;
	}

	public String getLanguagePreference() {
		return languagePreference;
	}

	public void setLanguagePreference(String languagePreference) {
		this.languagePreference = languagePreference;
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

	public String getBannerName() {
		return bannerName;
	}

	public void setBannerName(String bannerName) {
		this.bannerName = bannerName;
	}

	@Override
	public String toString() {
		return "Account{" + "username='" + username + '\'' + ", password='" + password + '\'' + ", email='" + email
				+ '\'' + ", firstName='" + firstName + '\'' + ", lastName='" + lastName + '\'' + ", status='" + status
				+ '\'' + ", address1='" + address1 + '\'' + ", address2='" + address2 + '\'' + ", city='" + city + '\''
				+ ", state='" + state + '\'' + ", zip='" + zip + '\'' + ", country='" + country + '\'' + ", phone='"
				+ phone + '\'' + ", favouriteCategoryId='" + favouriteCategoryId + '\'' + ", languagePreference='"
				+ languagePreference + '\'' + ", listOption=" + listOption + ", bannerOption=" + bannerOption
				+ ", bannerName='" + bannerName + '\'' + '}';
	}

	public void copyFrom(Account source) {
		this.username = source.username;
		this.password = source.password;
		this.email = source.email;
		this.firstName = source.firstName;
		this.lastName = source.lastName;
		this.status = source.status;
		this.address1 = source.address1;
		this.address2 = source.address2;
		this.city = source.city;
		this.state = source.state;
		this.zip = source.zip;
		this.country = source.country;
		this.phone = source.phone;
		this.favouriteCategoryId = source.favouriteCategoryId;
		this.languagePreference = source.languagePreference;
		this.listOption = source.listOption;
		this.bannerOption = source.bannerOption;
		this.bannerName = source.bannerName;
	}

	public static Builder builder() {
		return new Builder();
	}

	public static Builder from(Account source) {
		return new Builder().username(source.username)
			.password(source.password)
			.email(source.email)
			.firstName(source.firstName)
			.lastName(source.lastName)
			.status(source.status)
			.address1(source.address1)
			.address2(source.address2)
			.city(source.city)
			.state(source.state)
			.zip(source.zip)
			.country(source.country)
			.phone(source.phone)
			.favouriteCategoryId(source.favouriteCategoryId)
			.languagePreference(source.languagePreference)
			.listOption(source.listOption)
			.bannerOption(source.bannerOption)
			.bannerName(source.bannerName);
	}

	public static class Builder {

		private String username;

		private String password;

		private String email;

		private String firstName;

		private String lastName;

		private String status;

		private String address1;

		private String address2;

		private String city;

		private String state;

		private String zip;

		private String country;

		private String phone;

		private String favouriteCategoryId;

		private String languagePreference;

		private boolean listOption;

		private boolean bannerOption;

		private String bannerName;

		public Builder username(String username) {
			this.username = username;
			return this;
		}

		public Builder password(String password) {
			this.password = password;
			return this;
		}

		public Builder email(String email) {
			this.email = email;
			return this;
		}

		public Builder firstName(String firstName) {
			this.firstName = firstName;
			return this;
		}

		public Builder lastName(String lastName) {
			this.lastName = lastName;
			return this;
		}

		public Builder status(String status) {
			this.status = status;
			return this;
		}

		public Builder address1(String address1) {
			this.address1 = address1;
			return this;
		}

		public Builder address2(String address2) {
			this.address2 = address2;
			return this;
		}

		public Builder city(String city) {
			this.city = city;
			return this;
		}

		public Builder state(String state) {
			this.state = state;
			return this;
		}

		public Builder zip(String zip) {
			this.zip = zip;
			return this;
		}

		public Builder country(String country) {
			this.country = country;
			return this;
		}

		public Builder phone(String phone) {
			this.phone = phone;
			return this;
		}

		public Builder favouriteCategoryId(String favouriteCategoryId) {
			this.favouriteCategoryId = favouriteCategoryId;
			return this;
		}

		public Builder languagePreference(String languagePreference) {
			this.languagePreference = languagePreference;
			return this;
		}

		public Builder listOption(boolean listOption) {
			this.listOption = listOption;
			return this;
		}

		public Builder bannerOption(boolean bannerOption) {
			this.bannerOption = bannerOption;
			return this;
		}

		public Builder bannerName(String bannerName) {
			this.bannerName = bannerName;
			return this;
		}

		public Account build() {
			Account account = new Account();
			account.username = this.username;
			account.password = this.password;
			account.email = this.email;
			account.firstName = this.firstName;
			account.lastName = this.lastName;
			account.status = this.status;
			account.address1 = this.address1;
			account.address2 = this.address2;
			account.city = this.city;
			account.state = this.state;
			account.zip = this.zip;
			account.country = this.country;
			account.phone = this.phone;
			account.favouriteCategoryId = this.favouriteCategoryId;
			account.languagePreference = this.languagePreference;
			account.listOption = this.listOption;
			account.bannerOption = this.bannerOption;
			account.bannerName = this.bannerName;
			return account;
		}

	}

}
