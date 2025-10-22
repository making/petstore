/*
 *    Copyright 2010-2022 the original author or authors.
 *
 *    Licensed under the Apache License, Version 2.0 (the "License");
 *    you may not use this file except in compliance with the License.
 *    You may obtain a copy of the License at
 *
 *       https://www.apache.org/licenses/LICENSE-2.0
 *
 *    Unless required by applicable law or agreed to in writing, software
 *    distributed under the License is distributed on an "AS IS" BASIS,
 *    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *    See the License for the specific language governing permissions and
 *    limitations under the License.
 */
package com.example.petstore.order;

import com.example.petstore.account.Account;
import com.example.petstore.cart.Cart;
import com.example.petstore.cart.CartItem;
import java.io.Serial;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.Clock;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * The Class Order.
 *
 * @author Eduardo Macarron
 */
public class Order implements Serializable {

	@Serial
	private static final long serialVersionUID = 6321792448424424931L;

	private int orderId;

	private String username;

	private LocalDateTime orderDate;

	private String shipAddress1;

	private String shipAddress2;

	private String shipCity;

	private String shipState;

	private String shipZip;

	private String shipCountry;

	private String billAddress1;

	private String billAddress2;

	private String billCity;

	private String billState;

	private String billZip;

	private String billCountry;

	private String courier;

	private BigDecimal totalPrice;

	private String billToFirstName;

	private String billToLastName;

	private String shipToFirstName;

	private String shipToLastName;

	private String creditCard;

	private String expiryDate;

	private String cardType;

	private String locale;

	private String status;

	private List<LineItem> lineItems = new ArrayList<>();

	public int getOrderId() {
		return orderId;
	}

	public void setOrderId(int orderId) {
		this.orderId = orderId;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public LocalDateTime getOrderDate() {
		return orderDate;
	}

	public void setOrderDate(LocalDateTime orderDate) {
		this.orderDate = orderDate;
	}

	public String getShipAddress1() {
		return shipAddress1;
	}

	public void setShipAddress1(String shipAddress1) {
		this.shipAddress1 = shipAddress1;
	}

	public String getShipAddress2() {
		return shipAddress2;
	}

	public void setShipAddress2(String shipAddress2) {
		this.shipAddress2 = shipAddress2;
	}

	public String getShipCity() {
		return shipCity;
	}

	public void setShipCity(String shipCity) {
		this.shipCity = shipCity;
	}

	public String getShipState() {
		return shipState;
	}

	public void setShipState(String shipState) {
		this.shipState = shipState;
	}

	public String getShipZip() {
		return shipZip;
	}

	public void setShipZip(String shipZip) {
		this.shipZip = shipZip;
	}

	public String getShipCountry() {
		return shipCountry;
	}

	public void setShipCountry(String shipCountry) {
		this.shipCountry = shipCountry;
	}

	public String getBillAddress1() {
		return billAddress1;
	}

	public void setBillAddress1(String billAddress1) {
		this.billAddress1 = billAddress1;
	}

	public String getBillAddress2() {
		return billAddress2;
	}

	public void setBillAddress2(String billAddress2) {
		this.billAddress2 = billAddress2;
	}

	public String getBillCity() {
		return billCity;
	}

	public void setBillCity(String billCity) {
		this.billCity = billCity;
	}

	public String getBillState() {
		return billState;
	}

	public void setBillState(String billState) {
		this.billState = billState;
	}

	public String getBillZip() {
		return billZip;
	}

	public void setBillZip(String billZip) {
		this.billZip = billZip;
	}

	public String getBillCountry() {
		return billCountry;
	}

	public void setBillCountry(String billCountry) {
		this.billCountry = billCountry;
	}

	public String getCourier() {
		return courier;
	}

	public void setCourier(String courier) {
		this.courier = courier;
	}

	public BigDecimal getTotalPrice() {
		return totalPrice;
	}

	public void setTotalPrice(BigDecimal totalPrice) {
		this.totalPrice = totalPrice;
	}

	public String getBillToFirstName() {
		return billToFirstName;
	}

	public void setBillToFirstName(String billToFirstName) {
		this.billToFirstName = billToFirstName;
	}

	public String getBillToLastName() {
		return billToLastName;
	}

	public void setBillToLastName(String billToLastName) {
		this.billToLastName = billToLastName;
	}

	public String getShipToFirstName() {
		return shipToFirstName;
	}

	public void setShipToFirstName(String shipFoFirstName) {
		this.shipToFirstName = shipFoFirstName;
	}

	public String getShipToLastName() {
		return shipToLastName;
	}

	public void setShipToLastName(String shipToLastName) {
		this.shipToLastName = shipToLastName;
	}

	public String getCreditCard() {
		return creditCard;
	}

	public void setCreditCard(String creditCard) {
		this.creditCard = creditCard;
	}

	public String getExpiryDate() {
		return expiryDate;
	}

	public void setExpiryDate(String expiryDate) {
		this.expiryDate = expiryDate;
	}

	public String getCardType() {
		return cardType;
	}

	public void setCardType(String cardType) {
		this.cardType = cardType;
	}

	public String getLocale() {
		return locale;
	}

	public void setLocale(String locale) {
		this.locale = locale;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public void setLineItems(List<LineItem> lineItems) {
		this.lineItems = lineItems;
	}

	public List<LineItem> getLineItems() {
		return lineItems;
	}

	/**
	 * Inits the order.
	 * @param account the account
	 * @param cart the cart
	 */
	public Order initOrder(Account account, Cart cart, Clock clock) {

		username = account.getUsername();
		orderDate = LocalDateTime.now(clock);

		shipToFirstName = account.getFirstName();
		shipToLastName = account.getLastName();
		shipAddress1 = account.getAddress1();
		shipAddress2 = account.getAddress2();
		shipCity = account.getCity();
		shipState = account.getState();
		shipZip = account.getZip();
		shipCountry = account.getCountry();

		billToFirstName = account.getFirstName();
		billToLastName = account.getLastName();
		billAddress1 = account.getAddress1();
		billAddress2 = account.getAddress2();
		billCity = account.getCity();
		billState = account.getState();
		billZip = account.getZip();
		billCountry = account.getCountry();

		totalPrice = cart.getSubTotal();

		creditCard = "999 9999 9999 9999";
		expiryDate = "12/03";
		cardType = "Visa";
		courier = "UPS";
		locale = "CA";
		status = "P";

		Iterator<CartItem> i = cart.getAllCartItems();
		while (i.hasNext()) {
			CartItem cartItem = i.next();
			addLineItem(cartItem);
		}

		return this;
	}

	public void addLineItem(CartItem cartItem) {
		LineItem lineItem = new LineItem(lineItems.size() + 1, cartItem);
		addLineItem(lineItem);
	}

	public void addLineItem(LineItem lineItem) {
		lineItems.add(lineItem);
	}

	public void copyFrom(Order source) {
		this.orderId = source.orderId;
		this.username = source.username;
		this.orderDate = source.orderDate;
		this.shipAddress1 = source.shipAddress1;
		this.shipAddress2 = source.shipAddress2;
		this.shipCity = source.shipCity;
		this.shipState = source.shipState;
		this.shipZip = source.shipZip;
		this.shipCountry = source.shipCountry;
		this.billAddress1 = source.billAddress1;
		this.billAddress2 = source.billAddress2;
		this.billCity = source.billCity;
		this.billState = source.billState;
		this.billZip = source.billZip;
		this.billCountry = source.billCountry;
		this.courier = source.courier;
		this.totalPrice = source.totalPrice;
		this.billToFirstName = source.billToFirstName;
		this.billToLastName = source.billToLastName;
		this.shipToFirstName = source.shipToFirstName;
		this.shipToLastName = source.shipToLastName;
		this.creditCard = source.creditCard;
		this.expiryDate = source.expiryDate;
		this.cardType = source.cardType;
		this.locale = source.locale;
		this.status = source.status;
		this.lineItems = new ArrayList<>(source.lineItems);
	}

	public static Builder builder() {
		return new Builder();
	}

	public static Builder from(Order source) {
		return new Builder().orderId(source.orderId)
			.username(source.username)
			.orderDate(source.orderDate)
			.shipAddress1(source.shipAddress1)
			.shipAddress2(source.shipAddress2)
			.shipCity(source.shipCity)
			.shipState(source.shipState)
			.shipZip(source.shipZip)
			.shipCountry(source.shipCountry)
			.billAddress1(source.billAddress1)
			.billAddress2(source.billAddress2)
			.billCity(source.billCity)
			.billState(source.billState)
			.billZip(source.billZip)
			.billCountry(source.billCountry)
			.courier(source.courier)
			.totalPrice(source.totalPrice)
			.billToFirstName(source.billToFirstName)
			.billToLastName(source.billToLastName)
			.shipToFirstName(source.shipToFirstName)
			.shipToLastName(source.shipToLastName)
			.creditCard(source.creditCard)
			.expiryDate(source.expiryDate)
			.cardType(source.cardType)
			.locale(source.locale)
			.status(source.status)
			.lineItems(new ArrayList<>(source.lineItems));
	}

	public static class Builder {

		private int orderId;

		private String username;

		private LocalDateTime orderDate;

		private String shipAddress1;

		private String shipAddress2;

		private String shipCity;

		private String shipState;

		private String shipZip;

		private String shipCountry;

		private String billAddress1;

		private String billAddress2;

		private String billCity;

		private String billState;

		private String billZip;

		private String billCountry;

		private String courier;

		private BigDecimal totalPrice;

		private String billToFirstName;

		private String billToLastName;

		private String shipToFirstName;

		private String shipToLastName;

		private String creditCard;

		private String expiryDate;

		private String cardType;

		private String locale;

		private String status;

		private List<LineItem> lineItems = new ArrayList<>();

		public Builder orderId(int orderId) {
			this.orderId = orderId;
			return this;
		}

		public Builder username(String username) {
			this.username = username;
			return this;
		}

		public Builder orderDate(LocalDateTime orderDate) {
			this.orderDate = orderDate;
			return this;
		}

		public Builder shipAddress1(String shipAddress1) {
			this.shipAddress1 = shipAddress1;
			return this;
		}

		public Builder shipAddress2(String shipAddress2) {
			this.shipAddress2 = shipAddress2;
			return this;
		}

		public Builder shipCity(String shipCity) {
			this.shipCity = shipCity;
			return this;
		}

		public Builder shipState(String shipState) {
			this.shipState = shipState;
			return this;
		}

		public Builder shipZip(String shipZip) {
			this.shipZip = shipZip;
			return this;
		}

		public Builder shipCountry(String shipCountry) {
			this.shipCountry = shipCountry;
			return this;
		}

		public Builder billAddress1(String billAddress1) {
			this.billAddress1 = billAddress1;
			return this;
		}

		public Builder billAddress2(String billAddress2) {
			this.billAddress2 = billAddress2;
			return this;
		}

		public Builder billCity(String billCity) {
			this.billCity = billCity;
			return this;
		}

		public Builder billState(String billState) {
			this.billState = billState;
			return this;
		}

		public Builder billZip(String billZip) {
			this.billZip = billZip;
			return this;
		}

		public Builder billCountry(String billCountry) {
			this.billCountry = billCountry;
			return this;
		}

		public Builder courier(String courier) {
			this.courier = courier;
			return this;
		}

		public Builder totalPrice(BigDecimal totalPrice) {
			this.totalPrice = totalPrice;
			return this;
		}

		public Builder billToFirstName(String billToFirstName) {
			this.billToFirstName = billToFirstName;
			return this;
		}

		public Builder billToLastName(String billToLastName) {
			this.billToLastName = billToLastName;
			return this;
		}

		public Builder shipToFirstName(String shipToFirstName) {
			this.shipToFirstName = shipToFirstName;
			return this;
		}

		public Builder shipToLastName(String shipToLastName) {
			this.shipToLastName = shipToLastName;
			return this;
		}

		public Builder creditCard(String creditCard) {
			this.creditCard = creditCard;
			return this;
		}

		public Builder expiryDate(String expiryDate) {
			this.expiryDate = expiryDate;
			return this;
		}

		public Builder cardType(String cardType) {
			this.cardType = cardType;
			return this;
		}

		public Builder locale(String locale) {
			this.locale = locale;
			return this;
		}

		public Builder status(String status) {
			this.status = status;
			return this;
		}

		public Builder lineItems(List<LineItem> lineItems) {
			this.lineItems = lineItems;
			return this;
		}

		public Order build() {
			Order order = new Order();
			order.orderId = this.orderId;
			order.username = this.username;
			order.orderDate = this.orderDate;
			order.shipAddress1 = this.shipAddress1;
			order.shipAddress2 = this.shipAddress2;
			order.shipCity = this.shipCity;
			order.shipState = this.shipState;
			order.shipZip = this.shipZip;
			order.shipCountry = this.shipCountry;
			order.billAddress1 = this.billAddress1;
			order.billAddress2 = this.billAddress2;
			order.billCity = this.billCity;
			order.billState = this.billState;
			order.billZip = this.billZip;
			order.billCountry = this.billCountry;
			order.courier = this.courier;
			order.totalPrice = this.totalPrice;
			order.billToFirstName = this.billToFirstName;
			order.billToLastName = this.billToLastName;
			order.shipToFirstName = this.shipToFirstName;
			order.shipToLastName = this.shipToLastName;
			order.creditCard = this.creditCard;
			order.expiryDate = this.expiryDate;
			order.cardType = this.cardType;
			order.locale = this.locale;
			order.status = this.status;
			order.lineItems = this.lineItems;
			return order;
		}

	}

}