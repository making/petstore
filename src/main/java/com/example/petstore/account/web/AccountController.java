package com.example.petstore.account.web;

import com.example.petstore.account.Account;
import com.example.petstore.account.AccountService;
import com.example.petstore.catalog.CatalogService;
import com.example.petstore.catalog.Category;
import com.example.petstore.catalog.Product;
import com.example.petstore.security.AccountUserDetails;
import jakarta.validation.groups.Default;
import java.util.List;
import java.util.Map;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import static java.util.stream.Collectors.toUnmodifiableMap;

@Controller
public class AccountController {

	private final AccountService accountService;

	private final CatalogService catalogService;

	private final PasswordEqualsValidator passwordEqualsValidator;

	private final List<String> languageList = List.of("english", "japanese");

	private final Map<String, String> categoryList;

	public AccountController(AccountService accountService, CatalogService catalogService,
			PasswordEqualsValidator passwordEqualsValidator) {
		this.accountService = accountService;
		this.catalogService = catalogService;
		this.passwordEqualsValidator = passwordEqualsValidator;
		this.categoryList = catalogService.getCategoryList()
			.stream()
			.collect(toUnmodifiableMap(Category::getCategoryId, Category::getName));
	}

	@InitBinder("accountForm")
	public void initBinder(WebDataBinder webDataBinder) {
		webDataBinder.addValidators(passwordEqualsValidator);
	}

	@ModelAttribute("languageList")
	public List<String> getLanguageList() {
		return this.languageList;
	}

	@ModelAttribute("categoryList")
	public Map<String, String> getCategoryList() {
		return this.categoryList;
	}

	@GetMapping(path = "/account/signonForm")
	public String signonForm() {
		return "account/signonForm";
	}

	@GetMapping(path = "/account/signoffForm")
	public String signoffForm() {
		return "account/signoffForm";
	}

	@GetMapping(path = "/account/newAccountForm")
	public String newAccountForm(@ModelAttribute AccountForm form) {
		return "account/newAccountForm";
	}

	@PostMapping(path = "/account/newAccount")
	public String newAccount(@Validated({ AccountForm.NewAccount.class, Default.class }) AccountForm form,
			BindingResult result) {
		if (result.hasErrors()) {
			return "account/newAccountForm";
		}
		Account account = form.toAccount().build();
		this.accountService.insertAccount(account);
		return "redirect:/account/signonForm";
	}

	@GetMapping(path = "/account/editAccountForm")
	public String editAccountForm(@ModelAttribute AccountForm form,
			@AuthenticationPrincipal AccountUserDetails userDetails) {
		Account account = userDetails.account();
		form.copyFrom(account);
		form.setPassword("");
		form.setRepeatedPassword("");
		return "account/editAccountForm";
	}

	@PostMapping(path = "/account/editAccount")
	public String editAccount(@Validated({ AccountForm.EditAccount.class, Default.class }) AccountForm form,
			BindingResult result, @AuthenticationPrincipal AccountUserDetails userDetails) {
		form.setUsername(userDetails.getUsername());
		if (result.hasErrors()) {
			return "account/editAccountForm";
		}
		Account account = form.toAccount().build();
		this.accountService.updateAccount(account);
		// reflect new values to the session object
		userDetails.account().copyFrom(account);
		List<Product> myList = this.catalogService.getProductListByCategory(account.getFavouriteCategoryId());
		userDetails.myList().clear();
		userDetails.myList().addAll(myList);
		return "redirect:/account/editAccountForm";
	}

}
