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

import com.example.petstore.catalog.Item;
import com.example.petstore.catalog.ItemMapper;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * The Class OrderService.
 *
 * @author Eduardo Macarron
 */
@Service
public class OrderService {

	private final ItemMapper itemMapper;

	private final OrderMapper orderMapper;

	private final LineItemMapper lineItemMapper;

	public OrderService(ItemMapper itemMapper, OrderMapper orderMapper, LineItemMapper lineItemMapper) {
		this.itemMapper = itemMapper;
		this.orderMapper = orderMapper;
		this.lineItemMapper = lineItemMapper;
	}

	/**
	 * place order.
	 * @param order the order
	 */
	@Transactional
	public void placeOrder(Order order) {
		order.getLineItems().forEach(lineItem -> {
			String itemId = lineItem.getItemId();
			Integer increment = lineItem.getQuantity();
			Map<String, Object> param = Map.of("itemId", itemId, "increment", increment);
			itemMapper.updateInventoryQuantity(param);
		});
		orderMapper.insertOrder(order);
		orderMapper.insertOrderStatus(order);
		order.getLineItems().forEach(lineItem -> {
			lineItem.setOrderId(order.getOrderId());
			lineItemMapper.insertLineItem(lineItem);
		});
	}

	/**
	 * Gets the order.
	 * @param orderId the order id
	 * @return the order
	 */
	@Transactional
	public Order getOrder(int orderId) {
		Order order = orderMapper.getOrder(orderId);
		order.setLineItems(lineItemMapper.getLineItemsByOrderId(orderId));
		order.getLineItems().forEach(lineItem -> {
			Item item = itemMapper.getItem(lineItem.getItemId());
			item.setQuantity(itemMapper.getInventoryQuantity(lineItem.getItemId()));
			lineItem.setItem(item);
		});
		return order;
	}

	/**
	 * Gets the orders by username.
	 * @param username the username
	 * @return the orders by username
	 */
	public List<Order> getOrdersByUsername(String username) {
		return orderMapper.getOrdersByUsername(username);
	}

}