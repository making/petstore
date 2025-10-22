package com.example.petstore.cart.web;

import com.example.petstore.cart.Cart;
import com.example.petstore.catalog.CatalogService;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class CartController {

	private final CatalogService catalogService;

	private final Cart cart;

	public CartController(CatalogService catalogService, Cart cart) {
		this.catalogService = catalogService;
		this.cart = cart;
	}

	@GetMapping(path = "/cart/viewCart")
	public String viewCart(@ModelAttribute CartForm cartForm) {
		return "cart/cart";
	}

	@GetMapping(path = "/cart/addItemToCart")
	public String addItemToCart(@RequestParam String workingItemId) {
		this.cart.addItemOrIncrementQuantity(workingItemId, this.catalogService::getInventoryQuantity,
				this.catalogService::getItem);
		return "redirect:/cart/viewCart";
	}

	@PostMapping(path = "/cart/updateCartQuantities")
	public String updateCartQuantities(@ModelAttribute CartForm cartForm) {
		this.cart.batchUpdate(cartForm.getQuantity(), this.catalogService::getInventoryQuantity);
		return "redirect:/cart/viewCart";
	}

	@GetMapping(path = "/cart/removeItemFromCart")
	public String removeItemFromCart(@RequestParam("cartItem") String cartItem) {
		this.cart.removeItemById(cartItem);
		return "redirect:/cart/viewCart";
	}

}
