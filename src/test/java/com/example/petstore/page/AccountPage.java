package com.example.petstore.page;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;

public class AccountPage extends BasePage {

	private final Locator usernameInput;

	private final Locator passwordInput;

	private final Locator signInButton;

	public AccountPage(Page page, String baseUrl) {
		super(page, baseUrl);
		this.usernameInput = page.locator("#username");
		this.passwordInput = page.locator("#password");
		this.signInButton = page.locator("input[value='Sign in']");
	}

	public void navigate() {
		this.page.navigate(baseUrl + "/account/signon?form");
	}

	public String alertErrorHeader() {
		return this.page.locator("#Catalog div.alert.alert-error h4").innerText();
	}

	public void signIn(String username, String password) {
		this.usernameInput.fill(username);
		this.passwordInput.fill(password);
		this.signInButton.click();
	}

}
