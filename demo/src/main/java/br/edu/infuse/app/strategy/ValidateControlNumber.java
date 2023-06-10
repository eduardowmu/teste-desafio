package br.edu.infuse.app.strategy;

import br.edu.infuse.app.exception.BadRequestException;
import br.edu.infuse.app.model.EntityDomain;
import br.edu.infuse.app.model.Order;

public class ValidateControlNumber implements Validator {
	@Override
	public Order process(EntityDomain ed) throws BadRequestException {
		Order order = (Order)ed;
		if(order.getControlCode() == null || order.getCustomerCode().equals("")) {
			throw new BadRequestException("Numero de controle inválido!");
		}
		return order;
	}
}