package com.example.petstore.page;

import java.util.List;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;

public class CartPage extends BasePage {

	public CartPage(Page page, String baseUrl) {
		super(page, baseUrl);
	}

	public void navigateToCart() {
		this.page.navigate(baseUrl + "/cart");
	}

	public void addItemToCart(String itemId) {
		this.page.navigate(baseUrl + "/cart?add&workingItemId=" + itemId);
	}

	public String getCartHeader() {
		return this.page.locator("#Cart h2").innerText();
	}

	public boolean isCartEmpty() {
		return this.page.locator("#Cart table td:has-text('Your cart is empty.')").count() > 0;
	}

	public List<String> getCartItemIds() {
		return this.page.locator("#Cart table tr td:first-child a").allInnerTexts();
	}

	public String getCartTotal() {
		return this.page.locator("#Cart .cart-total-amount").innerText().trim();
	}

	public int getItemQuantity(String itemId) {
		String value = this.page.locator("input[name='quantity[" + itemId + "]']").inputValue();
		return Integer.parseInt(value);
	}

	public CartPage updateItemQuantity(String itemId, int quantity) {
		this.page.locator("input[name='quantity[" + itemId + "]']").fill(String.valueOf(quantity));
		return this;
	}

	public CartPage submitUpdateCart() {
		this.page.locator("input[value='Update Cart']").click();
		return this;
	}

	public CartPage removeItem(String itemId) {
		this.page.locator("#Cart table a:has-text('Remove')")
			.filter(new Locator.FilterOptions()
				.setHas(this.page.locator("xpath=ancestor::tr//a[text()='" + itemId + "']")))
			.first()
			.click();
		return this;
	}

	public void clickRemoveLinkForItem(String itemId) {
		this.page.locator("#Cart table tr:has(a:text-is('" + itemId + "')) a:text-is('Remove')").click();
	}

	public boolean hasCheckoutButton() {
		return this.page.locator(".checkout-btn").count() > 0;
	}

	public void clickCheckout() {
		this.page.locator(".checkout-btn").click();
	}

	public String getItemListPrice(String itemId) {
		Locator row = this.page.locator("#Cart table tr:has(a:text-is('" + itemId + "'))");
		return row.locator("td:nth-child(6)").innerText().trim();
	}

	public String getItemTotalCost(String itemId) {
		Locator row = this.page.locator("#Cart table tr:has(a:text-is('" + itemId + "'))");
		return row.locator("td:nth-child(7)").innerText().trim();
	}

	public int getNumberOfItemsInCart() {
		return this.page.locator("#Cart table tr:has(a[href*='/catalog/items/'])").count();
	}

}
