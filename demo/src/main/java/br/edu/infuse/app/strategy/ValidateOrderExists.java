package br.edu.infuse.app.strategy;

import br.edu.infuse.app.exception.BadRequestException;
import br.edu.infuse.app.model.EntityDomain;
import br.edu.infuse.app.model.Order;
import br.edu.infuse.app.repository.OrderRepository;
import br.edu.infuse.app.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDateTime;
import java.util.Optional;

public class ValidateOrderExists implements Validator {
    private final OrderRepository orderRepository;
    @Autowired
    public ValidateOrderExists(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }
    @Override
    public Order process(EntityDomain ed) {
        Order order = (Order)ed;
        //inicializando a response
        Optional<Order> response = Optional.ofNullable(null);
        if(order.getControlCode() != null && !order.getControlCode().isEmpty()) {
            response = this.orderRepository.findByControlCode(order.getControlCode());
        } else if(order.getOrderDate() != null && !order.getOrderDate().isAfter(LocalDateTime.now())) {
            response = this.orderRepository.findByOrderDate(order.getOrderDate());
        } else if(order.getOrderValue() != null) {
            response = this.orderRepository.findByOrderValue(order.getOrderValue());
        } else if(order.getProductName() != null && !order.getProductName().isEmpty()) {
            response = this.orderRepository.findByProductName(order.getProductName());
        } else if(order.getProductValue() != null) {
            response = this.orderRepository.findByProductValue(order.getProductValue());
        } else if(order.getQuantity() != null && order.getQuantity() > 0) {
            response = this.orderRepository.findByQuantity(order.getQuantity());
        } else if(order.getCustomerCode() != null) {
            response = this.orderRepository.findByCustomerCode(order.getCustomerCode());
        }
        return response.orElseThrow(() -> new BadRequestException("Não foi possível encontrar resultados para " +
                "esta pesquisa: " + order.toString()));
    }
}