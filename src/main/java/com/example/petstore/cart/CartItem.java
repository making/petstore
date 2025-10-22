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
package com.example.petstore.cart;

import com.example.petstore.catalog.Item;
import java.io.Serial;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Optional;
import java.util.function.ToIntFunction;

/**
 * The Class CartItem.
 *
 * @author Eduardo Macarron
 */
public class CartItem implements Serializable {

	@Serial
	private static final long serialVersionUID = 6620528781626504362L;

	private Item item;

	private int quantity;

	private boolean inStock;

	private BigDecimal total;

	public boolean isInStock() {
		return inStock;
	}

	public BigDecimal getTotal() {
		return total;
	}

	public Item getItem() {
		return item;
	}

	public void setItem(Item item) {
		this.item = item;
		calculateTotal();
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity, ToIntFunction<String> getInventoryQuantity) {
		this.quantity = quantity;
		if (quantity > 0) {
			this.inStock = quantity <= getInventoryQuantity.applyAsInt(item.getItemId());
		}
		else {
			this.inStock = true;
		}
		calculateTotal();
	}

	public void incrementQuantity(ToIntFunction<String> getInventoryQuantity) {
		this.setQuantity(this.quantity + 1, getInventoryQuantity);
	}

	private void calculateTotal() {
		total = Optional.ofNullable(item)
			.map(Item::getListPrice)
			.map(v -> v.multiply(new BigDecimal(quantity)))
			.orElse(null);
	}

}