package br.edu.infuse.app.strategy;

import br.edu.infuse.app.model.EntityDomain;
import br.edu.infuse.app.model.Order;

public class ValidateDiscount implements Validator {
	@Override
	public Order process(EntityDomain ed) {
		Order order = (Order)ed;
		order.setOrderValue(this.setOrderValueWithDiscount(order.getQuantity(), order.getOrderValue()));
		return order;
	}
	
	private Double setOrderValueWithDiscount(Integer quantity, Double value) {
		return quantity > 5 ? value * 0.95 : 
				quantity >= 10 ? value * 0.90 : value;
	}
}