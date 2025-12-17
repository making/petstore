package com.example.petstore.e2e;

import com.example.petstore.TestcontainersConfiguration;
import com.example.petstore.page.AccountPage;
import com.example.petstore.page.AccountPage.AccountFormData;
import com.microsoft.playwright.Browser;
import com.microsoft.playwright.BrowserContext;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.Playwright;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.context.annotation.Import;
import org.springframework.jdbc.core.simple.JdbcClient;

import static org.assertj.core.api.Assertions.assertThat;

@Import(TestcontainersConfiguration.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT, properties = {})
class AccountE2ETest {

	static Playwright playwright;

	static Browser browser;

	BrowserContext context;

	Page page;

	AccountPage accountPage;

	@Autowired
	JdbcClient jdbcClient;

	@LocalServerPort
	int serverPort;

	@BeforeAll
	static void before() {
		playwright = Playwright.create();
		browser = playwright.chromium().launch();
	}

	@AfterAll
	static void after() {
		playwright.close();
	}

	@BeforeEach
	void setUp() {
		this.context = browser.newContext();
		this.context.setDefaultTimeout(3000);
		this.page = context.newPage();
		this.accountPage = new AccountPage(this.page, "http://localhost:" + this.serverPort);
	}

	@AfterEach
	void tearDown() {
		// Clean up test data created during tests
		this.jdbcClient.sql("DELETE FROM signon WHERE username LIKE 'testuser%'").update();
		this.jdbcClient.sql("DELETE FROM profile WHERE userid LIKE 'testuser%'").update();
		this.jdbcClient.sql("DELETE FROM account WHERE userid LIKE 'testuser%'").update();
		this.context.close();
	}

	@Test
	void loginShouldSucceedWithCorrectCredentials() {
		this.accountPage.navigateToSignOn();
		this.accountPage.fillSignOnForm("j2ee", "j2ee").submitSignOnForm();
		assertThat(this.page.url()).isEqualTo(this.accountPage.getBaseUrl() + "/catalog");
		// Verify welcome message contains user's first name
		assertThat(this.accountPage.getWelcomeMessage()).contains("Welcome ABC!");
	}

	@Test
	void loginShouldFailWithIncorrectCredentials() {
		this.accountPage.navigateToSignOn();
		this.accountPage.fillSignOnForm("invalidUser", "invalidPass").submitSignOnForm();
		assertThat(this.page.url()).isEqualTo(this.accountPage.getBaseUrl() + "/account/signon?form&error=true");
		assertThat(this.accountPage.getAlertErrorHeader()).isEqualTo("Login error!");
	}

	@Test
	void newAccountRegistrationShouldSucceed() {
		String uniqueUsername = "testuser" + System.currentTimeMillis();
		AccountFormData formData = AccountFormData.builder()
			.username(uniqueUsername)
			.password("password123")
			.repeatedPassword("password123")
			.firstName("Test")
			.lastName("User")
			.email("test@example.com")
			.phone("123-456-7890")
			.address1("123 Test Street")
			.address2("Apt 1")
			.city("Test City")
			.state("TS")
			.zip("12345")
			.country("USA")
			.languagePreference("english")
			.favouriteCategoryId("DOGS")
			.listOption(true)
			.bannerOption(true)
			.build();

		this.accountPage.navigateToNewAccount();
		this.accountPage.fillAccountForm(formData).submitAccountForm();

		// After successful registration, should redirect to sign-on page
		assertThat(this.page.url()).isEqualTo(this.accountPage.getBaseUrl() + "/account/signon?form");
		// Verify sign-on page prompt is displayed
		assertThat(this.accountPage.getSignonPagePrompt()).isEqualTo("Please enter your username and password.");

		// Verify the new user can log in
		this.accountPage.fillSignOnForm(uniqueUsername, "password123").submitSignOnForm();
		assertThat(this.page.url()).isEqualTo(this.accountPage.getBaseUrl() + "/catalog");
		// Verify welcome message contains the new user's first name
		assertThat(this.accountPage.getWelcomeMessage()).contains("Welcome Test!");
	}

	@Test
	void newAccountRegistrationShouldFailWithMismatchedPasswords() {
		this.accountPage.navigateToNewAccount();
		AccountFormData formData = AccountFormData.builder()
			.username("testuser_mismatch")
			.password("password123")
			.repeatedPassword("differentPassword")
			.firstName("Test")
			.lastName("User")
			.email("test@example.com")
			.phone("123-456-7890")
			.address1("123 Test Street")
			.city("Test City")
			.state("TS")
			.zip("12345")
			.country("USA")
			.build();

		this.accountPage.fillAccountForm(formData).submitAccountForm();

		// Should stay on the registration form due to validation error
		assertThat(this.page.url()).isEqualTo(this.accountPage.getBaseUrl() + "/account/new");
		// Verify form header is still displayed (indicating we're on the form page)
		assertThat(this.accountPage.getFormHeader()).isEqualTo("User Information");
		// Verify validation error message is displayed for password field
		assertThat(this.accountPage.getValidationError("password")).isEqualTo("password no match");
	}

	@Test
	void editAccountShouldUpdateUserInformation() {
		// First login as existing user
		this.accountPage.navigateToSignOn();
		this.accountPage.fillSignOnForm("j2ee", "j2ee").submitSignOnForm();

		// Navigate to edit account page
		this.accountPage.navigateToEditAccount();
		assertThat(this.page.url()).isEqualTo(this.accountPage.getBaseUrl() + "/account/edit?form");
		// Verify form header is displayed
		assertThat(this.accountPage.getFormHeader()).isEqualTo("User Information");

		// Update some fields (password fields can be left empty to keep current password)
		AccountFormData formData = AccountFormData.builder()
			.password("")
			.repeatedPassword("")
			.firstName("Updated")
			.lastName("Name")
			.email("updated@example.com")
			.phone("999-888-7777")
			.address1("456 Updated Street")
			.address2("Suite 200")
			.city("New City")
			.state("NC")
			.zip("54321")
			.country("Canada")
			.languagePreference("japanese")
			.favouriteCategoryId("CATS")
			.listOption(true)
			.bannerOption(false)
			.build();

		this.accountPage.fillAccountForm(formData).submitAccountForm();

		// Should redirect back to edit form after successful update
		assertThat(this.page.url()).isEqualTo(this.accountPage.getBaseUrl() + "/account/edit?form");

		// Verify the updated values are displayed
		assertThat(this.accountPage.getFieldValue("firstName")).isEqualTo("Updated");
		assertThat(this.accountPage.getFieldValue("lastName")).isEqualTo("Name");
		assertThat(this.accountPage.getFieldValue("email")).isEqualTo("updated@example.com");
		assertThat(this.accountPage.getFieldValue("phone")).isEqualTo("999-888-7777");
		assertThat(this.accountPage.getFieldValue("city")).isEqualTo("New City");
		assertThat(this.accountPage.getSelectedValue("languagePreference")).isEqualTo("japanese");
		assertThat(this.accountPage.getSelectedValue("favouriteCategoryId")).isEqualTo("CATS");
	}

	@Test
	void editAccountShouldRequireAuthentication() {
		// Try to access edit page without logging in
		this.accountPage.navigateToEditAccount();

		// Should redirect to login page
		assertThat(this.page.url()).contains("/account/signon");
		// Verify login prompt is displayed
		assertThat(this.accountPage.getSignonPagePrompt()).isEqualTo("Please enter your username and password.");
	}

}
