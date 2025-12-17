package com.example.petstore.e2e;

import com.example.petstore.TestcontainersConfiguration;
import com.example.petstore.page.AccountPage;
import com.example.petstore.page.CartPage;
import com.microsoft.playwright.Browser;
import com.microsoft.playwright.BrowserContext;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.Playwright;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.context.annotation.Import;
import org.testcontainers.junit.jupiter.Testcontainers;

import static org.assertj.core.api.Assertions.assertThat;

@Import(TestcontainersConfiguration.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT, properties = {})
@Testcontainers(disabledWithoutDocker = true)
class CartE2ETest {

	static Playwright playwright;

	static Browser browser;

	BrowserContext context;

	Page page;

	CartPage cartPage;

	AccountPage accountPage;

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
		this.cartPage = new CartPage(this.page, "http://localhost:" + this.serverPort);
		this.accountPage = new AccountPage(this.page, "http://localhost:" + this.serverPort);
	}

	@AfterEach
	void tearDown() {
		this.context.close();
	}

	@Test
	void viewEmptyCartShouldDisplayEmptyMessage() {
		this.cartPage.navigateToCart();

		assertThat(this.page.url()).isEqualTo(this.cartPage.getBaseUrl() + "/cart");
		assertThat(this.cartPage.getCartHeader()).isEqualTo("Shopping Cart");
		assertThat(this.cartPage.isCartEmpty()).isTrue();
		assertThat(this.cartPage.hasCheckoutButton()).isFalse();
	}

	@Test
	void addItemToCartShouldDisplayItem() {
		this.cartPage.addItemToCart("EST-1");

		assertThat(this.page.url()).isEqualTo(this.cartPage.getBaseUrl() + "/cart");
		assertThat(this.cartPage.isCartEmpty()).isFalse();
		assertThat(this.cartPage.getCartItemIds()).contains("EST-1");
		assertThat(this.cartPage.getItemQuantity("EST-1")).isEqualTo(1);
		assertThat(this.cartPage.getItemListPrice("EST-1")).isEqualTo("$16.50");
		assertThat(this.cartPage.getItemTotalCost("EST-1")).isEqualTo("$16.50");
		assertThat(this.cartPage.hasCheckoutButton()).isTrue();
	}

	@Test
	void addSameItemTwiceShouldIncrementQuantity() {
		this.cartPage.addItemToCart("EST-1");
		this.cartPage.addItemToCart("EST-1");

		assertThat(this.cartPage.getCartItemIds()).containsOnlyOnce("EST-1");
		assertThat(this.cartPage.getItemQuantity("EST-1")).isEqualTo(2);
		assertThat(this.cartPage.getItemTotalCost("EST-1")).isEqualTo("$33.00");
	}

	@Test
	void addMultipleItemsShouldDisplayAllItems() {
		this.cartPage.addItemToCart("EST-1");
		this.cartPage.addItemToCart("EST-10");

		assertThat(this.cartPage.getCartItemIds()).contains("EST-1", "EST-10");
		assertThat(this.cartPage.getNumberOfItemsInCart()).isEqualTo(2);
	}

	@Test
	void updateCartQuantityShouldRecalculateTotal() {
		this.cartPage.addItemToCart("EST-1");
		assertThat(this.cartPage.getItemQuantity("EST-1")).isEqualTo(1);

		this.cartPage.updateItemQuantity("EST-1", 3).submitUpdateCart();

		assertThat(this.page.url()).isEqualTo(this.cartPage.getBaseUrl() + "/cart");
		assertThat(this.cartPage.getItemQuantity("EST-1")).isEqualTo(3);
		assertThat(this.cartPage.getItemTotalCost("EST-1")).isEqualTo("$49.50");
	}

	@Test
	void updateCartQuantityToZeroShouldRemoveItem() {
		this.cartPage.addItemToCart("EST-1");
		assertThat(this.cartPage.isCartEmpty()).isFalse();

		this.cartPage.updateItemQuantity("EST-1", 0).submitUpdateCart();

		assertThat(this.cartPage.isCartEmpty()).isTrue();
	}

	@Test
	void removeItemFromCartShouldRemoveItem() {
		this.cartPage.addItemToCart("EST-1");
		this.cartPage.addItemToCart("EST-10");
		assertThat(this.cartPage.getNumberOfItemsInCart()).isEqualTo(2);

		this.cartPage.clickRemoveLinkForItem("EST-1");

		assertThat(this.page.url()).isEqualTo(this.cartPage.getBaseUrl() + "/cart");
		assertThat(this.cartPage.getCartItemIds()).doesNotContain("EST-1");
		assertThat(this.cartPage.getCartItemIds()).contains("EST-10");
		assertThat(this.cartPage.getNumberOfItemsInCart()).isEqualTo(1);
	}

	@Test
	void removeLastItemFromCartShouldShowEmptyCart() {
		this.cartPage.addItemToCart("EST-1");
		assertThat(this.cartPage.isCartEmpty()).isFalse();

		this.cartPage.clickRemoveLinkForItem("EST-1");

		assertThat(this.cartPage.isCartEmpty()).isTrue();
		assertThat(this.cartPage.hasCheckoutButton()).isFalse();
	}

	@Test
	void cartTotalShouldSumAllItemTotals() {
		this.cartPage.addItemToCart("EST-1");
		this.cartPage.addItemToCart("EST-10");

		// EST-1: $16.50, EST-10: $18.50
		assertThat(this.cartPage.getCartTotal()).isEqualTo("$35.00");

		this.cartPage.updateItemQuantity("EST-1", 2).submitUpdateCart();

		// EST-1: $16.50 x 2 = $33.00, EST-10: $18.50
		assertThat(this.cartPage.getCartTotal()).isEqualTo("$51.50");
	}

	@Test
	void cartContentsShouldPersistAfterLogin() {
		// Add items to cart before login
		this.cartPage.addItemToCart("EST-1");
		this.cartPage.addItemToCart("EST-10");
		assertThat(this.cartPage.getCartItemIds()).contains("EST-1", "EST-10");
		assertThat(this.cartPage.getCartTotal()).isEqualTo("$35.00");

		// Login
		this.accountPage.navigateToSignOn();
		this.accountPage.fillSignOnForm("j2ee", "j2ee").submitSignOnForm();

		// Verify cart contents are preserved after login
		this.cartPage.navigateToCart();
		assertThat(this.cartPage.getCartItemIds()).contains("EST-1", "EST-10");
		assertThat(this.cartPage.getNumberOfItemsInCart()).isEqualTo(2);
		assertThat(this.cartPage.getCartTotal()).isEqualTo("$35.00");
	}

	@Test
	void updateQuantityExceedingStockShouldHideCheckoutButton() {
		// Add item to cart - should be in stock initially
		this.cartPage.addItemToCart("EST-1");
		assertThat(this.cartPage.getItemInStock("EST-1")).isTrue();
		assertThat(this.cartPage.hasCheckoutButton()).isTrue();

		// Update quantity to exceed stock (inventory is 10000 for all items)
		this.cartPage.updateItemQuantity("EST-1", 10001).submitUpdateCart();

		// Verify item is marked as out of stock
		assertThat(this.cartPage.getItemInStock("EST-1")).isFalse();
		// Checkout button should be hidden when items are out of stock
		assertThat(this.cartPage.hasCheckoutButton()).isFalse();
	}

}
