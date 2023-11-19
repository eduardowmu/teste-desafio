package br.edu.infuse.app.utils;

import java.time.LocalDateTime;

import br.edu.infuse.app.model.Client;
import br.edu.infuse.app.model.Order;
import br.edu.infuse.app.vo.EntityVo;

public class EntityUtils {
	public static final String ORDER = "order";
	public static final String CLIENT = "client";
	public static final String ENTITY = "entity";
	
	public static Order getOrderFromEntityVo(EntityVo vo) {
		return Order.builder()
					.controlCode(vo.getControlCode() != null ? vo.getControlCode() : "")
					.orderDate(vo.getOrderDate() != null || vo.getOrderDate().isEmpty() ?
							LocalDateTime.parse(vo.getOrderDate()) : LocalDateTime.now())
					.productName(vo.getProductName() != null ? vo.getProductName() : "")
					.productValue(vo.getProductValue() != null ? vo.getProductValue() : 0.00)
					.quantity(vo.getQuantity() != null && vo.getQuantity() != 0 ? vo.getQuantity() : 1)
					.orderValue(vo.getOrderValue() != null ? vo.getOrderValue() : vo.getProductValue() * vo.getQuantity())
					.customerCode(vo.getCustomerCode() != null ? vo.getCustomerCode() : 0)
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
					.quantity(getValue(order.getQuantity()))
					.orderValue(order.getOrderValue() != null ? order.getOrderValue() : 
						order.getProductValue() * getValue(order.getQuantity()))
					.customerCode(order.getCustomerCode() != null ? order.getCustomerCode() : 0)
					.build();
	}

	public static Client getClientFromEntityVo(EntityVo vo) {
		return Client.builder()
				.clientName(vo.getName() != null ? vo.getName() : "")
				.build();
	}

	public static EntityVo getEntityVoFromClient(Client client) {
		return EntityVo.builder()
				.id(client.getId() != null ? client.getId() : null)
				.name(client.getClientName() != null ? client.getClientName() : "")
				.build();
	}

	private static Integer getValue(Integer value) {
		return value != null && value != 0 ? value : 1;
	}

}