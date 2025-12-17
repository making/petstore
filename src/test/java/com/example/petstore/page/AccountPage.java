package com.example.petstore.page;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;

public class AccountPage extends BasePage {

	private final Locator usernameInput;

	private final Locator passwordInput;

	private final Locator signInButton;

	private final Locator saveAccountButton;

	public AccountPage(Page page, String baseUrl) {
		super(page, baseUrl);
		this.usernameInput = page.locator("#username");
		this.passwordInput = page.locator("#password");
		this.signInButton = page.locator("input[value='Sign in']");
		this.saveAccountButton = page.locator("input[value='Save Account Information']");
	}

	public void navigateToSignOn() {
		this.page.navigate(baseUrl + "/account/signon?form");
	}

	public void navigateToNewAccount() {
		this.page.navigate(baseUrl + "/account/new?form");
	}

	public void navigateToEditAccount() {
		this.page.navigate(baseUrl + "/account/edit?form");
	}

	public AccountPage fillSignOnForm(String username, String password) {
		this.usernameInput.fill(username);
		this.passwordInput.fill(password);
		return this;
	}

	public AccountPage submitSignOnForm() {
		this.signInButton.click();
		return this;
	}

	public AccountPage fillAccountForm(AccountFormData data) {
		if (data.username() != null) {
			this.page.locator("#username").fill(data.username());
		}
		if (data.password() != null) {
			this.page.locator("#password").fill(data.password());
		}
		if (data.repeatedPassword() != null) {
			this.page.locator("#repeatedPassword").fill(data.repeatedPassword());
		}
		this.page.locator("#firstName").fill(data.firstName());
		this.page.locator("#lastName").fill(data.lastName());
		this.page.locator("#email").fill(data.email());
		this.page.locator("#phone").fill(data.phone());
		this.page.locator("#address1").fill(data.address1());
		if (data.address2() != null) {
			this.page.locator("#address2").fill(data.address2());
		}
		this.page.locator("#city").fill(data.city());
		this.page.locator("#state").fill(data.state());
		this.page.locator("#zip").fill(data.zip());
		this.page.locator("#country").fill(data.country());
		this.page.locator("#languagePreference").selectOption(data.languagePreference());
		this.page.locator("#favouriteCategoryId").selectOption(data.favouriteCategoryId());
		if (data.listOption()) {
			this.page.locator("#listOption").check();
		}
		else {
			this.page.locator("#listOption").uncheck();
		}
		if (data.bannerOption()) {
			this.page.locator("#bannerOption").check();
		}
		else {
			this.page.locator("#bannerOption").uncheck();
		}
		return this;
	}

	public AccountPage submitAccountForm() {
		this.saveAccountButton.click();
		return this;
	}

	public String getSignonPagePrompt() {
		return this.page.locator("#Catalog p").innerText();
	}

	public String getWelcomeMessage() {
		return this.page.locator("#WelcomeContent").innerText();
	}

	public String getFormHeader() {
		return this.page.locator("#Catalog h3").first().innerText();
	}

	public String getAlertErrorHeader() {
		return this.page.locator("#Catalog div.alert.alert-error h4").innerText();
	}

	public record AccountFormData(String username, String password, String repeatedPassword, String firstName,
			String lastName, String email, String phone, String address1, String address2, String city, String state,
			String zip, String country, String languagePreference, String favouriteCategoryId, boolean listOption,
			boolean bannerOption) {

		public static Builder builder() {
			return new Builder();
		}

		public static class Builder {

			private String username;

			private String password;

			private String repeatedPassword;

			private String firstName;

			private String lastName;

			private String email;

			private String phone;

			private String address1;

			private String address2;

			private String city;

			private String state;

			private String zip;

			private String country;

			private String languagePreference = "english";

			private String favouriteCategoryId = "DOGS";

			private boolean listOption;

			private boolean bannerOption;

			public Builder username(String username) {
				this.username = username;
				return this;
			}

			public Builder password(String password) {
				this.password = password;
				return this;
			}

			public Builder repeatedPassword(String repeatedPassword) {
				this.repeatedPassword = repeatedPassword;
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

			public Builder email(String email) {
				this.email = email;
				return this;
			}

			public Builder phone(String phone) {
				this.phone = phone;
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

			public Builder languagePreference(String languagePreference) {
				this.languagePreference = languagePreference;
				return this;
			}

			public Builder favouriteCategoryId(String favouriteCategoryId) {
				this.favouriteCategoryId = favouriteCategoryId;
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

			public AccountFormData build() {
				return new AccountFormData(username, password, repeatedPassword, firstName, lastName, email, phone,
						address1, address2, city, state, zip, country, languagePreference, favouriteCategoryId,
						listOption, bannerOption);
			}

		}

	}

}
