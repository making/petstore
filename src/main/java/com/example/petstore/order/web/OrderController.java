package com.example.petstore.order.web;

import com.example.petstore.account.Account;
import com.example.petstore.cart.Cart;
import com.example.petstore.order.Order;
import com.example.petstore.order.OrderService;
import com.example.petstore.security.AccountUserDetails;
import java.time.Clock;
import java.util.List;
import java.util.Objects;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class OrderController {

	private final OrderService orderService;

	private final Cart cart;

	private final Clock clock;

	private final List<String> cardTypeList = List.of("Visa", "MasterCard", "American Express");

	public OrderController(OrderService orderService, Cart cart, Clock clock) {
		this.orderService = orderService;
		this.cart = cart;
		this.clock = clock;
	}

	@ModelAttribute("creditCardTypes")
	public List<String> getCardTypeList() {
		return this.cardTypeList;
	}

	@GetMapping(path = "/order/new", params = "form")
	public String newOrderForm(@ModelAttribute OrderForm orderForm, Model model,
			@AuthenticationPrincipal AccountUserDetails userDetails) {
		if (!this.cart.isAllInStock()) {
			return "redirect:/cart";
		}
		Account account = userDetails.account();
		Order order = new Order().initOrder(account, this.cart, this.clock);
		orderForm.copyFrom(order);
		return "order/newOrderForm";
	}

	@PostMapping(path = "/order/new")
	public String confirmOrder(@Validated OrderForm orderForm, BindingResult bindingResult) {
		if (bindingResult.hasErrors()) {
			return "order/newOrderForm";
		}
		return "order/confirmOrder";
	}

	@PostMapping(path = "/order/new", params = "shippingAddressRequired=true")
	public String shippingForm(OrderForm orderForm, Order order) {
		return "order/shippingForm";
	}

	@PostMapping(path = "/order/new", params = "confirmed")
	public String newOrder(@Validated OrderForm orderForm, BindingResult bindingResult,
			@AuthenticationPrincipal AccountUserDetails userDetails, RedirectAttributes attributes) {
		if (bindingResult.hasErrors()) {
			return "order/newOrderForm";
		}
		if (!this.cart.isAllInStock()) {
			return "redirect:/cart";
		}
		Account account = userDetails.account();
		Order.Builder builder = Order.from(new Order().initOrder(account, this.cart, this.clock));
		Order order = orderForm.copyTo(builder).build();
		this.orderService.placeOrder(order);
		attributes.addAttribute("orderId", order.getOrderId());
		attributes.addFlashAttribute("message", "Thank you, your order has been submitted.");
		this.cart.clear();
		return "redirect:/order/orders/{orderId}";
	}

	@GetMapping(path = "/order/orders/{orderId}")
	public String viewOrder(@PathVariable int orderId, Model model,
			@AuthenticationPrincipal AccountUserDetails userDetails) {
		Order order = this.orderService.getOrder(orderId);
		Account account = userDetails.account();
		if (Objects.equals(account.getUsername(), order.getUsername())) {
			model.addAttribute(order);
			return "order/viewOrder";
		}
		else {
			return "error/404";
		}
	}

	@GetMapping(path = "/order/orders")
	public String listOrders(Model model, @AuthenticationPrincipal AccountUserDetails userDetails) {
		String username = userDetails.getUsername();
		List<Order> orderList = this.orderService.getOrdersByUsername(username);
		model.addAttribute("orderList", orderList);
		return "order/listOrders";
	}

}
