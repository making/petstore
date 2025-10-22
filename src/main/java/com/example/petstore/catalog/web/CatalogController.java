package com.example.petstore.catalog.web;

import com.example.petstore.catalog.CatalogService;
import com.example.petstore.catalog.Category;
import com.example.petstore.catalog.Item;
import com.example.petstore.catalog.Product;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class CatalogController {

	private final CatalogService catalogService;

	public CatalogController(CatalogService catalogService) {
		this.catalogService = catalogService;
	}

	@GetMapping(path = "/")
	public String index() {
		return "redirect:/catalog";
	}

	@GetMapping(path = "/catalog")
	public String main() {
		return "catalog/main";
	}

	@GetMapping(path = "/catalog/categories/{categoryId}")
	public String viewCategory(@PathVariable String categoryId, Model model) {
		List<Product> productList = catalogService.getProductListByCategory(categoryId);
		Category category = catalogService.getCategory(categoryId);
		model.addAttribute("productList", productList);
		model.addAttribute("category", category);
		return "catalog/category";
	}

	@GetMapping(path = "/catalog/products/{productId}")
	public String viewProduct(@PathVariable String productId, Model model) {
		List<Item> itemList = catalogService.getItemListByProduct(productId);
		Product product = catalogService.getProduct(productId);
		model.addAttribute("itemList", itemList);
		model.addAttribute("product", product);
		return "catalog/product";
	}

	@GetMapping(path = "/catalog/items/{itemId}")
	public String viewItem(@PathVariable String itemId, Model model) {
		Item item = catalogService.getItem(itemId);
		Product product = item.getProduct();
		model.addAttribute("item", item);
		model.addAttribute("product", product);
		return "catalog/item";
	}

	@GetMapping(path = "/catalog/products", params = "keyword")
	public String searchProducts(@Validated ProductSearchForm form, BindingResult result, Model model) {
		if (result.hasErrors()) {
			return "catalog/searchError";
		}
		String keyword = form.getKeyword().toLowerCase();
		List<Product> productList = catalogService.searchProductList(keyword);
		model.addAttribute("productList", productList);
		return "catalog/search";
	}

}
