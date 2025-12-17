package com.example.petstore.e2e;

import com.example.petstore.TestcontainersConfiguration;
import com.example.petstore.page.AccountPage;
import com.example.petstore.page.CartPage;
import com.example.petstore.page.OrderPage;
import com.example.petstore.page.OrderPage.OrderFormData;
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
import org.testcontainers.junit.jupiter.Testcontainers;

import static org.assertj.core.api.Assertions.assertThat;

@Import(TestcontainersConfiguration.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT, properties = {})
@Testcontainers(disabledWithoutDocker = true)
class OrderE2ETest {

	static Playwright playwright;

	static Browser browser;

	BrowserContext context;

	Page page;

	OrderPage orderPage;

	CartPage cartPage;

	AccountPage accountPage;

	int maxOrderIdBeforeTest;

	@LocalServerPort
	int serverPort;

	@Autowired
	JdbcClient jdbcClient;

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
		this.context.setDefaultTimeout(5000);
		this.page = context.newPage();
		String baseUrl = "http://localhost:" + this.serverPort;
		this.orderPage = new OrderPage(this.page, baseUrl);
		this.cartPage = new CartPage(this.page, baseUrl);
		this.accountPage = new AccountPage(this.page, baseUrl);
		// Record the max order ID before each test
		this.maxOrderIdBeforeTest = this.jdbcClient.sql("SELECT COALESCE(MAX(orderid), 0) FROM orders")
			.query(Integer.class)
			.single();
	}

	@AfterEach
	void tearDown() {
		this.context.close();
		// Clean up only orders created during this test
		this.jdbcClient.sql("DELETE FROM lineitem WHERE orderid > ?").param(this.maxOrderIdBeforeTest).update();
		this.jdbcClient.sql("DELETE FROM orderstatus WHERE orderid > ?").param(this.maxOrderIdBeforeTest).update();
		this.jdbcClient.sql("DELETE FROM orders WHERE orderid > ?").param(this.maxOrderIdBeforeTest).update();
	}

	@Test
	void placeOrderWhenNotLoggedInShouldRedirectToLoginThenCompleteCheckout() {
		// Add item to cart without logging in
		this.cartPage.addItemToCart("EST-1");
		assertThat(this.cartPage.getCartItemIds()).contains("EST-1");

		// Click checkout - should redirect to login
		this.cartPage.clickCheckout();
		assertThat(this.page.url()).contains("/account/signon");

		// Login
		this.accountPage.fillSignOnForm("j2ee", "j2ee").submitSignOnForm();

		// After login, should be redirected to order form
		assertThat(this.page.url()).contains("/order/new");

		// Fill payment details and submit
		OrderFormData orderData = OrderFormData.builder()
			.cardType("Visa")
			.creditCard("4111111111111111")
			.expiryDate("12/2030")
			.build();
		this.orderPage.fillPaymentForm(orderData);
		this.orderPage.submitOrderForm();

		// Confirm order
		this.orderPage.confirmOrder();

		// Verify order was placed successfully
		assertThat(this.page.url()).contains("/order/orders/");
		assertThat(this.orderPage.getOrderItemIds()).contains("EST-1");
		assertThat(this.orderPage.getOrderStatus()).isEqualTo("P");
	}

	@Test
	void placeOrderWhenAlreadyLoggedInShouldCompleteCheckoutDirectly() {
		// Login first
		this.accountPage.navigateToSignOn();
		this.accountPage.fillSignOnForm("j2ee", "j2ee").submitSignOnForm();

		// Add item to cart
		this.cartPage.addItemToCart("EST-1");
		assertThat(this.cartPage.getCartItemIds()).contains("EST-1");

		// Click checkout - should go directly to order form
		this.cartPage.clickCheckout();
		assertThat(this.page.url()).contains("/order/new");

		// Fill payment details and submit
		OrderFormData orderData = OrderFormData.builder()
			.cardType("MasterCard")
			.creditCard("5111111111111111")
			.expiryDate("06/2028")
			.build();
		this.orderPage.fillPaymentForm(orderData);
		this.orderPage.submitOrderForm();

		// Confirm order
		this.orderPage.confirmOrder();

		// Verify order was placed successfully
		assertThat(this.page.url()).contains("/order/orders/");
		assertThat(this.orderPage.getOrderItemIds()).contains("EST-1");
		assertThat(this.orderPage.getPaymentCardType()).isEqualTo("MasterCard");
	}

	@Test
	void placeOrderWithMultipleItemsShouldShowAllItemsInOrder() {
		// Login
		this.accountPage.navigateToSignOn();
		this.accountPage.fillSignOnForm("j2ee", "j2ee").submitSignOnForm();

		// Add multiple items to cart
		this.cartPage.addItemToCart("EST-1");
		this.cartPage.addItemToCart("EST-10");
		this.cartPage.addItemToCart("EST-14");
		assertThat(this.cartPage.getNumberOfItemsInCart()).isEqualTo(3);

		// Proceed to checkout
		this.cartPage.clickCheckout();

		// Fill payment and submit
		OrderFormData orderData = OrderFormData.builder().build();
		this.orderPage.fillPaymentForm(orderData);
		this.orderPage.submitOrderForm();
		this.orderPage.confirmOrder();

		// Verify all items are in the order
		assertThat(this.orderPage.getOrderItemIds()).containsExactlyInAnyOrder("EST-1", "EST-10", "EST-14");
	}

	@Test
	void orderListShouldShowPlacedOrdersAndAllowViewingDetails() {
		// Login
		this.accountPage.navigateToSignOn();
		this.accountPage.fillSignOnForm("j2ee", "j2ee").submitSignOnForm();

		// Place an order
		this.cartPage.addItemToCart("EST-1");
		this.cartPage.clickCheckout();
		this.orderPage.fillPaymentForm(OrderFormData.builder().build());
		this.orderPage.submitOrderForm();
		this.orderPage.confirmOrder();

		// Get the order ID from the confirmation page
		String orderId = this.orderPage.getOrderId();
		assertThat(orderId).isNotNull();

		// Navigate to order list
		this.orderPage.navigateToOrderList();
		assertThat(this.page.url()).contains("/order/orders");
		assertThat(this.orderPage.getOrderListHeader()).isEqualTo("My Orders");

		// Verify order appears in list with correct total
		assertThat(this.orderPage.getOrderIdsFromList()).contains(orderId);
		assertThat(this.orderPage.getOrderTotalFromList(orderId)).isEqualTo("$16.50");

		// Click on order to view details
		this.orderPage.clickOrderInList(orderId);
		assertThat(this.page.url()).contains("/order/orders/" + orderId);
		assertThat(this.orderPage.getOrderItemIds()).contains("EST-1");
		assertThat(this.orderPage.getOrderStatus()).isEqualTo("P");
	}

	@Test
	void checkoutWithInvalidCreditCardShouldShowValidationError() {
		// Login
		this.accountPage.navigateToSignOn();
		this.accountPage.fillSignOnForm("j2ee", "j2ee").submitSignOnForm();

		// Add item to cart and proceed to checkout
		this.cartPage.addItemToCart("EST-1");
		this.cartPage.clickCheckout();

		// Fill with invalid credit card number (too short)
		OrderFormData orderData = OrderFormData.builder().creditCard("123").expiryDate("12/2030").build();
		this.orderPage.fillPaymentForm(orderData);
		this.orderPage.submitOrderForm();

		// Should stay on the same page with validation error
		assertThat(this.page.url()).contains("/order/new");
		assertThat(this.orderPage.hasValidationError("creditCard")).isTrue();
	}

	@Test
	void checkoutWithInvalidExpiryDateShouldShowValidationError() {
		// Login
		this.accountPage.navigateToSignOn();
		this.accountPage.fillSignOnForm("j2ee", "j2ee").submitSignOnForm();

		// Add item to cart and proceed to checkout
		this.cartPage.addItemToCart("EST-1");
		this.cartPage.clickCheckout();

		// Fill with invalid expiry date format
		OrderFormData orderData = OrderFormData.builder().creditCard("4111111111111111").expiryDate("2030/12").build();
		this.orderPage.fillPaymentForm(orderData);
		this.orderPage.submitOrderForm();

		// Should stay on the same page with validation error
		assertThat(this.page.url()).contains("/order/new");
		assertThat(this.orderPage.hasValidationError("expiryDate")).isTrue();
	}

	@Test
	void checkoutWithOutOfStockItemShouldRedirectToCart() {
		// Login
		this.accountPage.navigateToSignOn();
		this.accountPage.fillSignOnForm("j2ee", "j2ee").submitSignOnForm();

		// Add item to cart and set quantity to exceed stock
		this.cartPage.addItemToCart("EST-1");
		this.cartPage.updateItemQuantity("EST-1", 10001).submitUpdateCart();

		// Verify item is out of stock and checkout button is hidden
		assertThat(this.cartPage.getItemInStock("EST-1")).isFalse();
		assertThat(this.cartPage.hasCheckoutButton()).isFalse();

		// Try to navigate directly to checkout - should redirect to cart
		this.orderPage.navigateToCheckout();
		assertThat(this.page.url()).contains("/cart");
	}

}
