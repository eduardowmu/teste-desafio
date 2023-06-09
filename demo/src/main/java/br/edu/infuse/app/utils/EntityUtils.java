package br.edu.infuse.app.utils;

import java.time.LocalDateTime;

import br.edu.infuse.app.model.Order;
import br.edu.infuse.app.vo.EntityVo;

public class EntityUtils {
	public static final String ORDER = "order";
	public static final String CLIENT = "client";
	public static final String ENTITY = "entity";
	
	public static Order getOrderFromEntityVo(EntityVo vo) {
		return Order.builder()
					.id(vo.getId() != null ? vo.getId() : null)
					.controlCode(vo.getControlCode() != null ? vo.getControlCode() : "")
					.orderDate(vo.getOrderDate() != null ? LocalDateTime.parse(vo.getOrderDate()) : LocalDateTime.now())
					.productName(vo.getProductName() != null ? vo.getProductName() : "")
					.productValue(vo.getProductValue() != null ? vo.getProductValue() : 0.00)
					.quantity(vo.getQuantity() != null ? vo.getQuantity() : 1)
					.orderValue(vo.getOrderValue() != null ? vo.getOrderValue() : 
						vo.getProductValue() * (vo.getQuantity() != null ? vo.getQuantity() : 1))
					.customerCode(vo.getCustomerCode() != null ? vo.getCustomerCode() : "")
					.build();
	}
	
	public static EntityVo getEntityVoFromOrder(Order order) {
		return EntityVo.builder()
					.id(order.getId() != null ? order.getId() : null)
					.controlCode(order.getControlCode() != null ? order.getControlCode() : "")
					.orderDate(order.getOrderDate() != null ? FormatUtils.dateFormat(order.getOrderDate()) : 
						FormatUtils.dateFormat(LocalDateTime.now()))
					.productName(order.getProductName() != null ? order.getProductName() : "")
					.productValue(order.getProductValue() != null ? order.getProductValue() : 0.00)
					.quantity(order.getQuantity() != null ? order.getQuantity() : 1)
					.orderValue(order.getOrderValue() != null ? order.getOrderValue() : 
						order.getProductValue() * (order.getQuantity() != null ? order.getQuantity() : 1))
					.customerCode(order.getCustomerCode() != null ? order.getCustomerCode() : "")
					.build();
	}
}