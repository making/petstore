package com.example.petstore.page;

import java.util.List;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;

public class CatalogPage extends BasePage {

	public CatalogPage(Page page, String baseUrl) {
		super(page, baseUrl);
	}

	public void navigateToMain() {
		this.page.navigate(baseUrl + "/catalog");
	}

	public void navigateToCategory(String categoryId) {
		this.page.navigate(baseUrl + "/catalog/categories/" + categoryId);
	}

	public void navigateToProduct(String productId) {
		this.page.navigate(baseUrl + "/catalog/products/" + productId);
	}

	public void navigateToItem(String itemId) {
		this.page.navigate(baseUrl + "/catalog/items/" + itemId);
	}

	public CatalogPage fillSearchForm(String keyword) {
		this.page.locator("input[name='keyword']").fill(keyword);
		return this;
	}

	public CatalogPage submitSearchForm() {
		this.page.locator("input[name='keyword']").press("Enter");
		return this;
	}

	public void clickCategoryLink(String categoryName) {
		this.page.locator("#Sidebar a").filter(new Locator.FilterOptions().setHasText(categoryName)).click();
	}

	public void clickProductLink(String productId) {
		this.page.locator("#Catalog table a").filter(new Locator.FilterOptions().setHasText(productId)).click();
	}

	public void clickItemLink(String itemId) {
		this.page.locator("#Catalog table a").filter(new Locator.FilterOptions().setHasText(itemId)).click();
	}

	public String getCategoryHeader() {
		return this.page.locator("#Catalog h2").innerText();
	}

	public String getProductHeader() {
		return this.page.locator("#Catalog h2").innerText();
	}

	public String getItemTitle() {
		return this.page.locator("#Catalog .item-title").innerText().trim();
	}

	public String getItemId() {
		return this.page.locator("#Catalog .item-id").innerText().replace("Item ID:", "").trim();
	}

	public String getItemPrice() {
		return this.page.locator("#Catalog .item-price").innerText().trim();
	}

	public String getItemStock() {
		Locator inStock = this.page.locator("#Catalog .in-stock");
		if (inStock.count() > 0) {
			return inStock.innerText().trim();
		}
		Locator outOfStock = this.page.locator("#Catalog .out-of-stock");
		if (outOfStock.count() > 0) {
			return outOfStock.innerText().trim();
		}
		return "";
	}

	public List<String> getProductIds() {
		return this.page.locator("#Catalog table td:first-child a").allInnerTexts();
	}

	public List<String> getProductNames() {
		return this.page.locator("#Catalog table td:nth-child(2)").allInnerTexts();
	}

	public List<String> getItemIds() {
		return this.page.locator("#Catalog table .item-id-cell a").allInnerTexts();
	}

	public List<String> getSearchResultProductIds() {
		return this.page.locator("#Catalog table .product-id-cell a").allInnerTexts();
	}

	public List<String> getSearchResultProductNames() {
		return this.page.locator("#Catalog table .product-name-cell").allInnerTexts();
	}

	public boolean hasAddToCartButton() {
		return this.page.locator(".add-to-cart-btn").count() > 0;
	}

}
