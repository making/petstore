package com.example.petstore.page;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import java.util.List;

public class OrderPage extends BasePage {

	private final Locator continueButton;

	private final Locator confirmButton;

	public OrderPage(Page page, String baseUrl) {
		super(page, baseUrl);
		this.continueButton = page.locator("input[value='Continue']");
		this.confirmButton = page.locator("input.order-confirm-btn");
	}

	public void navigateToCheckout() {
		this.page.navigate(baseUrl + "/order/new?form");
	}

	public void navigateToOrderList() {
		this.page.navigate(baseUrl + "/order/orders");
	}

	public OrderPage fillPaymentForm(OrderFormData data) {
		this.page.locator("#cardType").selectOption(data.cardType());
		this.page.locator("#creditCard").fill(data.creditCard());
		this.page.locator("#expiryDate").fill(data.expiryDate());
		return this;
	}

	public OrderPage fillBillingAddress(OrderFormData data) {
		this.page.locator("#billToFirstName").fill(data.billToFirstName());
		this.page.locator("#billToLastName").fill(data.billToLastName());
		this.page.locator("#billAddress1").fill(data.billAddress1());
		if (data.billAddress2() != null) {
			this.page.locator("#billAddress2").fill(data.billAddress2());
		}
		this.page.locator("#billCity").fill(data.billCity());
		this.page.locator("#billState").fill(data.billState());
		this.page.locator("#billZip").fill(data.billZip());
		this.page.locator("#billCountry").fill(data.billCountry());
		return this;
	}

	public OrderPage fillOrderForm(OrderFormData data) {
		fillPaymentForm(data);
		fillBillingAddress(data);
		return this;
	}

	public OrderPage submitOrderForm() {
		this.continueButton.click();
		return this;
	}

	public OrderPage confirmOrder() {
		this.confirmButton.click();
		return this;
	}

	public String getOrderConfirmationHeader() {
		return this.page.locator("#Catalog table th").first().innerText();
	}

	public String getOrderId() {
		// First try to get from URL (after order confirmation redirect)
		String url = this.page.url();
		java.util.regex.Matcher urlMatcher = java.util.regex.Pattern.compile("/order/orders/(\\d+)").matcher(url);
		if (urlMatcher.find()) {
			return urlMatcher.group(1);
		}
		// Fallback: try to get from page header
		String header = getOrderConfirmationHeader();
		java.util.regex.Matcher headerMatcher = java.util.regex.Pattern.compile("Order #(\\d+)").matcher(header);
		if (headerMatcher.find()) {
			return headerMatcher.group(1);
		}
		return null;
	}

	public String getOrderTotal() {
		return this.page.locator("#Catalog table table th:has-text('Total:')").innerText().replace("Total: ", "");
	}

	public List<String> getOrderItemIds() {
		return this.page.locator("#Catalog table table a[href*='/catalog/items/']")
			.allInnerTexts()
			.stream()
			.map(String::trim)
			.toList();
	}

	public String getPaymentCardType() {
		Locator row = this.page.locator("#Catalog table tr:has(td:text-is('Card Type:'))");
		return row.locator("td").last().innerText();
	}

	public String getBillingFirstName() {
		Locator section = this.page.locator("#Catalog table tr:has(th:text-is('Billing Address'))");
		Locator row = section.locator("~ tr:has(td:text-is('First name:'))").first();
		return row.locator("td").last().innerText();
	}

	public String getShippingFirstName() {
		Locator section = this.page.locator("#Catalog table tr:has(th:text-is('Shipping Address'))");
		Locator row = section.locator("~ tr:has(td:text-is('First name:'))").first();
		return row.locator("td").last().innerText();
	}

	public String getOrderStatus() {
		return this.page.locator("#Catalog table td:has-text('Status:')").innerText().replace("Status: ", "");
	}

	public boolean hasConfirmationMessage() {
		return this.page.locator("text=Thank you, your order has been submitted").isVisible();
	}

	public boolean hasValidationError(String fieldName) {
		return this.page.locator("#" + fieldName + "\\.errors").count() > 0;
	}

	// Order list page methods
	public String getOrderListHeader() {
		return this.page.locator("#Catalog h2").innerText();
	}

	public List<String> getOrderIdsFromList() {
		return this.page.locator("#Catalog table a[href*='/order/orders/']")
			.allInnerTexts()
			.stream()
			.map(String::trim)
			.toList();
	}

	public String getOrderTotalFromList(String orderId) {
		Locator row = this.page.locator("#Catalog table tr:has(a:text-is('" + orderId + "'))");
		return row.locator("td:nth-child(3)").innerText().trim();
	}

	public void clickOrderInList(String orderId) {
		this.page.locator("#Catalog table a:text-is('" + orderId + "')").click();
	}

	public record OrderFormData(String cardType, String creditCard, String expiryDate, String billToFirstName,
			String billToLastName, String billAddress1, String billAddress2, String billCity, String billState,
			String billZip, String billCountry) {

		public static Builder builder() {
			return new Builder();
		}

		public static class Builder {

			private String cardType = "Visa";

			private String creditCard = "4111111111111111";

			private String expiryDate = "12/2030";

			private String billToFirstName;

			private String billToLastName;

			private String billAddress1;

			private String billAddress2;

			private String billCity;

			private String billState;

			private String billZip;

			private String billCountry;

			public Builder cardType(String cardType) {
				this.cardType = cardType;
				return this;
			}

			public Builder creditCard(String creditCard) {
				this.creditCard = creditCard;
				return this;
			}

			public Builder expiryDate(String expiryDate) {
				this.expiryDate = expiryDate;
				return this;
			}

			public Builder billToFirstName(String billToFirstName) {
				this.billToFirstName = billToFirstName;
				return this;
			}

			public Builder billToLastName(String billToLastName) {
				this.billToLastName = billToLastName;
				return this;
			}

			public Builder billAddress1(String billAddress1) {
				this.billAddress1 = billAddress1;
				return this;
			}

			public Builder billAddress2(String billAddress2) {
				this.billAddress2 = billAddress2;
				return this;
			}

			public Builder billCity(String billCity) {
				this.billCity = billCity;
				return this;
			}

			public Builder billState(String billState) {
				this.billState = billState;
				return this;
			}

			public Builder billZip(String billZip) {
				this.billZip = billZip;
				return this;
			}

			public Builder billCountry(String billCountry) {
				this.billCountry = billCountry;
				return this;
			}

			public OrderFormData build() {
				return new OrderFormData(cardType, creditCard, expiryDate, billToFirstName, billToLastName,
						billAddress1, billAddress2, billCity, billState, billZip, billCountry);
			}

		}

	}

}
