package com.example.petstore.e2e;

import com.example.petstore.TestcontainersConfiguration;
import com.example.petstore.page.CatalogPage;
import com.microsoft.playwright.Browser;
import com.microsoft.playwright.BrowserContext;
import com.microsoft.playwright.BrowserType;
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

import static org.assertj.core.api.Assertions.assertThat;

@Import(TestcontainersConfiguration.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT, properties = {})
class CatalogE2ETest {

	static Playwright playwright;

	static Browser browser;

	BrowserContext context;

	Page page;

	CatalogPage catalogPage;

	@LocalServerPort
	int serverPort;

	@BeforeAll
	static void before() {
		playwright = Playwright.create();
		browser = playwright.chromium().launch(new BrowserType.LaunchOptions().setHeadless(false));
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
		this.catalogPage = new CatalogPage(this.page, "http://localhost:" + this.serverPort);
	}

	@AfterEach
	void tearDown() {
		this.context.close();
	}

	@Test
	void viewCategoryShouldDisplayProductList() {
		this.catalogPage.navigateToCategory("FISH");

		assertThat(this.page.url()).isEqualTo(this.catalogPage.getBaseUrl() + "/catalog/categories/FISH");
		// Verify category header
		assertThat(this.catalogPage.getCategoryHeader()).isEqualTo("Fish");
		// Verify product list is displayed
		assertThat(this.catalogPage.getProductIds()).contains("FI-SW-01", "FI-SW-02", "FI-FW-01", "FI-FW-02");
	}

	@Test
	void viewCategoryViaSidebarNavigation() {
		this.catalogPage.navigateToMain();
		this.catalogPage.clickCategoryLink("Dogs");

		assertThat(this.page.url()).isEqualTo(this.catalogPage.getBaseUrl() + "/catalog/categories/DOGS");
		assertThat(this.catalogPage.getCategoryHeader()).isEqualTo("Dogs");
		assertThat(this.catalogPage.getProductIds()).isNotEmpty();
	}

	@Test
	void viewProductShouldDisplayItemList() {
		this.catalogPage.navigateToProduct("FI-SW-01");

		assertThat(this.page.url()).isEqualTo(this.catalogPage.getBaseUrl() + "/catalog/products/FI-SW-01");
		// Verify product header
		assertThat(this.catalogPage.getProductHeader()).isEqualTo("Angelfish");
		// Verify item list is displayed
		assertThat(this.catalogPage.getItemIds()).contains("EST-1", "EST-2");
		// Verify Add to Cart button is available
		assertThat(this.catalogPage.hasAddToCartButton()).isTrue();
	}

	@Test
	void viewProductViaClickFromCategory() {
		this.catalogPage.navigateToCategory("DOGS");
		this.catalogPage.clickProductLink("K9-BD-01");

		assertThat(this.page.url()).isEqualTo(this.catalogPage.getBaseUrl() + "/catalog/products/K9-BD-01");
		assertThat(this.catalogPage.getProductHeader()).isEqualTo("Bulldog");
		assertThat(this.catalogPage.getItemIds()).isNotEmpty();
	}

	@Test
	void viewItemShouldDisplayItemDetails() {
		this.catalogPage.navigateToItem("EST-1");

		assertThat(this.page.url()).isEqualTo(this.catalogPage.getBaseUrl() + "/catalog/items/EST-1");
		// Verify item details
		assertThat(this.catalogPage.getItemId()).isEqualTo("EST-1");
		assertThat(this.catalogPage.getItemTitle()).contains("Angelfish");
		assertThat(this.catalogPage.getItemPrice()).isEqualTo("$16.50");
		assertThat(this.catalogPage.getItemStock()).isNotEmpty();
		// Verify Add to Cart button is available
		assertThat(this.catalogPage.hasAddToCartButton()).isTrue();
	}

	@Test
	void viewItemViaClickFromProduct() {
		this.catalogPage.navigateToProduct("K9-DL-01");
		this.catalogPage.clickItemLink("EST-10");

		assertThat(this.page.url()).isEqualTo(this.catalogPage.getBaseUrl() + "/catalog/items/EST-10");
		assertThat(this.catalogPage.getItemId()).isEqualTo("EST-10");
		assertThat(this.catalogPage.getItemTitle()).contains("Dalmation");
	}

	@Test
	void searchProductsShouldReturnMatchingResults() {
		this.catalogPage.navigateToMain();
		this.catalogPage.fillSearchForm("fish").submitSearchForm();

		assertThat(this.page.url()).contains("/catalog/products?keyword=fish");
		// Verify search results contain fish-related products
		assertThat(this.catalogPage.getSearchResultProductNames()).anyMatch(name -> name.toLowerCase().contains("fish")
				|| name.toLowerCase().contains("angelfish") || name.toLowerCase().contains("goldfish"));
	}

	@Test
	void searchProductsWithNoResultsShouldShowEmptyTable() {
		this.catalogPage.navigateToMain();
		this.catalogPage.fillSearchForm("nonexistent_xyz").submitSearchForm();

		assertThat(this.page.url()).contains("/catalog/products?keyword=nonexistent_xyz");
		// Verify no results
		assertThat(this.catalogPage.getSearchResultProductIds()).isEmpty();
	}

	@Test
	void fullBrowsingJourneyCategoryToProductToItem() {
		// Start from main page
		this.catalogPage.navigateToMain();

		// Navigate to Cats category via sidebar
		this.catalogPage.clickCategoryLink("Cats");
		assertThat(this.catalogPage.getCategoryHeader()).isEqualTo("Cats");

		// Click on a product
		this.catalogPage.clickProductLink("FL-DSH-01");
		assertThat(this.catalogPage.getProductHeader()).isEqualTo("Manx");
		assertThat(this.catalogPage.getItemIds()).isNotEmpty();

		// Click on an item
		this.catalogPage.clickItemLink("EST-14");
		assertThat(this.catalogPage.getItemId()).isEqualTo("EST-14");
		assertThat(this.catalogPage.getItemTitle()).contains("Manx");
		assertThat(this.catalogPage.hasAddToCartButton()).isTrue();
	}

}
