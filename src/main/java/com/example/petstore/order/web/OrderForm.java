package com.example.petstore.order.web;

import com.example.petstore.order.Order;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

public class OrderForm {

	@NotBlank(message = "Card type is required")
	private String cardType;

	@NotBlank(message = "Credit card number is required")
	@Pattern(regexp = "\\d{13,16}", message = "Invalid credit card number (13-16 digits)")
	private String creditCard;

	@NotBlank(message = "Expiry date is required")
	@Pattern(regexp = "(0[1-9]|1[0-2])/\\d{4}", message = "Invalid expiry date format (MM/YYYY)")
	private String expiryDate;

	@NotBlank(message = "First name is required")
	private String billToFirstName;

	@NotBlank(message = "Last name is required")
	private String billToLastName;

	@NotBlank(message = "Address is required")
	private String billAddress1;

	private String billAddress2;

	@NotBlank(message = "City is required")
	private String billCity;

	@NotBlank(message = "State is required")
	private String billState;

	@NotBlank(message = "Zip code is required")
	private String billZip;

	@NotBlank(message = "Country is required")
	private String billCountry;

	@NotBlank(message = "First name is required")
	private String shipToFirstName;

	@NotBlank(message = "Last name is required")
	private String shipToLastName;

	@NotBlank(message = "Address is required")
	private String shipAddress1;

	private String shipAddress2;

	@NotBlank(message = "City is required")
	private String shipCity;

	@NotBlank(message = "State is required")
	private String shipState;

	@NotBlank(message = "Zip code is required")
	private String shipZip;

	@NotBlank(message = "Country is required")
	private String shipCountry;

	private boolean shippingAddressRequired;

	public String getCardType() {
		return cardType;
	}

	public void setCardType(String cardType) {
		this.cardType = cardType;
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

	public String getShipToFirstName() {
		return shipToFirstName;
	}

	public void setShipToFirstName(String shipToFirstName) {
		this.shipToFirstName = shipToFirstName;
	}

	public String getShipToLastName() {
		return shipToLastName;
	}

	public void setShipToLastName(String shipToLastName) {
		this.shipToLastName = shipToLastName;
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

	public boolean isShippingAddressRequired() {
		return shippingAddressRequired;
	}

	public void setShippingAddressRequired(boolean shippingAddressRequired) {
		this.shippingAddressRequired = shippingAddressRequired;
	}

	public void copyFrom(Order order) {
		this.cardType = order.getCardType();
		this.creditCard = order.getCreditCard();
		this.expiryDate = order.getExpiryDate();
		this.billToFirstName = order.getBillToFirstName();
		this.billToLastName = order.getBillToLastName();
		this.billAddress1 = order.getBillAddress1();
		this.billAddress2 = order.getBillAddress2();
		this.billCity = order.getBillCity();
		this.billState = order.getBillState();
		this.billZip = order.getBillZip();
		this.billCountry = order.getBillCountry();
		this.shipToFirstName = order.getShipToFirstName();
		this.shipToLastName = order.getShipToLastName();
		this.shipAddress1 = order.getShipAddress1();
		this.shipAddress2 = order.getShipAddress2();
		this.shipCity = order.getShipCity();
		this.shipState = order.getShipState();
		this.shipZip = order.getShipZip();
		this.shipCountry = order.getShipCountry();
	}

	public Order.Builder toOrder() {
		return Order.builder()
			.cardType(this.cardType)
			.creditCard(this.creditCard)
			.expiryDate(this.expiryDate)
			.billToFirstName(this.billToFirstName)
			.billToLastName(this.billToLastName)
			.billAddress1(this.billAddress1)
			.billAddress2(this.billAddress2)
			.billCity(this.billCity)
			.billState(this.billState)
			.billZip(this.billZip)
			.billCountry(this.billCountry)
			.shipToFirstName(this.shipToFirstName)
			.shipToLastName(this.shipToLastName)
			.shipAddress1(this.shipAddress1)
			.shipAddress2(this.shipAddress2)
			.shipCity(this.shipCity)
			.shipState(this.shipState)
			.shipZip(this.shipZip)
			.shipCountry(this.shipCountry);
	}

	public Order.Builder copyTo(Order.Builder builder) {
		return builder.cardType(this.cardType)
			.creditCard(this.creditCard)
			.expiryDate(this.expiryDate)
			.billToFirstName(this.billToFirstName)
			.billToLastName(this.billToLastName)
			.billAddress1(this.billAddress1)
			.billAddress2(this.billAddress2)
			.billCity(this.billCity)
			.billState(this.billState)
			.billZip(this.billZip)
			.billCountry(this.billCountry)
			.shipToFirstName(this.shipToFirstName)
			.shipToLastName(this.shipToLastName)
			.shipAddress1(this.shipAddress1)
			.shipAddress2(this.shipAddress2)
			.shipCity(this.shipCity)
			.shipState(this.shipState)
			.shipZip(this.shipZip)
			.shipCountry(this.shipCountry);
	}

}
