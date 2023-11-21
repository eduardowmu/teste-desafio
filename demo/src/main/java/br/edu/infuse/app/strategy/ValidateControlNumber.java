package br.edu.infuse.app.strategy;

import br.edu.infuse.app.exception.BadRequestException;
import br.edu.infuse.app.model.EntityDomain;
import br.edu.infuse.app.model.Order;
import br.edu.infuse.app.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Optional;

public class ValidateControlNumber implements Validator {
	private final OrderRepository orderRepository;

	@Autowired
	public ValidateControlNumber(OrderRepository orderRepository) {
		this.orderRepository = orderRepository;
	}

	@Override
	public Order process(EntityDomain ed) throws BadRequestException {
		Order order = (Order)ed;
		if(order.getControlCode() == null || order.getControlCode().equals("")) {
			throw new BadRequestException("Numero de controle inválido!");
		}
		Optional<List<Order>> existedOrder = this.orderRepository.findByControlCode(order.getControlCode());
		if(existedOrder.isPresent()) {
			throw new BadRequestException("Numero controle já existente!");
		}
		return order;
	}
}