package com.example.petstore.catalog.web;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public class ProductSearchForm {

	@NotNull(message = "Please enter a keyword to search for, then press the search button.")
	@Size(min = 1, message = "Please enter a keyword to search for, then press the search button.")
	private String keyword;

	public void setKeyword(String keyword) {
		this.keyword = keyword;
	}

	public String getKeyword() {
		return keyword;
	}

}
