package com.example.petstore.page;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;

public abstract class BasePage {

	protected final Page page;

	protected final String baseUrl;

	protected BasePage(Page page, String baseUrl) {
		this.page = page;
		this.baseUrl = baseUrl;
	}

	public String getFieldValue(String fieldId) {
		return this.page.locator("#" + fieldId).inputValue();
	}

	public String getSelectedValue(String selectId) {
		return this.page.locator("#" + selectId).inputValue();
	}

	public boolean isChecked(String checkboxId) {
		return this.page.locator("#" + checkboxId).isChecked();
	}

	public String getValidationError(String field) {
		Locator errorSpan = this.page.locator("#" + field + "\\.errors");
		if (errorSpan.count() > 0) {
			return errorSpan.first().innerText();
		}
		return "";
	}

}
