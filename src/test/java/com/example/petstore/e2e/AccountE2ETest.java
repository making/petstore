package com.example.petstore.e2e;

import com.example.petstore.TestcontainersConfiguration;
import com.example.petstore.page.AccountPage;
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
		// Clean up test data
		// this.jdbcClient.sql("-- do something").update();
		this.context.close();
	}

	@Test
	void loginShouldSucceedWithCorrectCredentials() {
		this.accountPage.navigate();
		this.accountPage.signIn("j2ee", "j2ee");
		assertThat(this.page.url()).isEqualTo("http://localhost:" + this.serverPort + "/catalog");
	}

	@Test
	void loginShouldFailWithIncorrectCredentials() {
		this.accountPage.navigate();
		this.accountPage.signIn("invalidUser", "invalidPass");
		assertThat(this.page.url())
			.isEqualTo("http://localhost:" + this.serverPort + "/account/signon?form&error=true");
		assertThat(this.accountPage.alertErrorHeader()).isEqualTo("Login error!");
	}

}
