package com.example.petstore.page;

import com.microsoft.playwright.Page;

public abstract class BasePage {

	protected final Page page;

	protected final String baseUrl;

	protected BasePage(Page page, String baseUrl) {
		this.page = page;
		this.baseUrl = baseUrl;
	}

}
