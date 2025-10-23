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
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Function;
import java.util.function.ToIntFunction;

import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.SessionScope;

/**
 * The Class Cart.
 *
 * @author Eduardo Macarron
 */
@SessionScope
@Component
public class Cart implements Serializable {

	@Serial
	private static final long serialVersionUID = 8329559983943337176L;

	private final Map<String, CartItem> itemMap = new ConcurrentHashMap<>();

	private final List<CartItem> itemList = Collections.synchronizedList(new ArrayList<>());

	public List<CartItem> getCartItems() {
		return itemList;
	}

	public int getNumberOfItems() {
		return itemList.size();
	}

	public boolean containsItemId(String itemId) {
		return itemMap.containsKey(itemId);
	}

	/**
	 * Adds the item.
	 * @param item the item
	 * @param getInventoryQuantity the get inventory quantity
	 */
	public void addItem(Item item, ToIntFunction<String> getInventoryQuantity) {
		CartItem cartItem = itemMap.get(item.getItemId());
		if (cartItem == null) {
			cartItem = new CartItem();
			cartItem.setItem(item);
			cartItem.setQuantity(0, getInventoryQuantity);
			itemMap.put(item.getItemId(), cartItem);
			itemList.add(cartItem);
		}
		cartItem.incrementQuantity(getInventoryQuantity);
	}

	/**
	 * Removes the item by id.
	 * @param itemId the item id
	 * @return the item
	 */
	public Item removeItemById(String itemId) {
		CartItem cartItem = itemMap.remove(itemId);
		if (cartItem == null) {
			return null;
		}
		else {
			itemList.remove(cartItem);
			return cartItem.getItem();
		}
	}

	/**
	 * Increment quantity by item id.
	 * @param itemId the item id
	 */
	public void incrementQuantityByItemId(String itemId, ToIntFunction<String> getInventoryQuantity) {
		CartItem cartItem = itemMap.get(itemId);
		cartItem.incrementQuantity(getInventoryQuantity);
	}

	public void addItemOrIncrementQuantity(String itemId, ToIntFunction<String> getInventoryQuantity,
			Function<String, Item> getItem) {
		if (this.containsItemId(itemId)) {
			this.incrementQuantityByItemId(itemId, getInventoryQuantity);
		}
		else {
			Item item = getItem.apply(itemId);
			this.addItem(item, getInventoryQuantity);
		}
	}

	/**
	 * Gets the total.
	 * @return the total
	 */
	public BigDecimal getTotal() {
		return itemList.stream()
			.map(cartItem -> cartItem.getItem().getListPrice().multiply(new BigDecimal(cartItem.getQuantity())))
			.reduce(BigDecimal.ZERO, BigDecimal::add);
	}

	public void batchUpdate(Map<String, Integer> quantities, ToIntFunction<String> getInventoryQuantity) {
		Iterator<CartItem> iterator = this.itemList.iterator();
		while (iterator.hasNext()) {
			CartItem cartItem = iterator.next();
			String itemId = cartItem.getItem().getItemId();
			int quantity = quantities.get(itemId);
			cartItem.setQuantity(quantity, getInventoryQuantity);
			if (quantity < 1) {
				iterator.remove();
			}
		}
	}

	public void clear() {
		this.itemList.clear();
		this.itemMap.clear();
	}

	public boolean isAllInStock() {
		return itemList.stream().allMatch(CartItem::isInStock);
	}

}