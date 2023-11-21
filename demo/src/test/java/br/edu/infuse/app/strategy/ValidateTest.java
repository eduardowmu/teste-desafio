package br.edu.infuse.app.strategy;

import br.edu.infuse.app.exception.BadRequestException;
import br.edu.infuse.app.model.Client;
import br.edu.infuse.app.model.EntityDomain;
import br.edu.infuse.app.model.Order;
import br.edu.infuse.app.repository.OrderRepository;
import br.edu.infuse.app.service.ClientService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
public class ValidateTest {
    @Mock
    private ClientService clientService;
    @Mock
    private OrderRepository orderRepository;
    @InjectMocks
    private ValidateClient validateClient;
    @InjectMocks
    private ValidateControlNumber validateControlNumber;
    @InjectMocks
    private ValidateDiscount validateDiscount;
    @InjectMocks
    private ValidateOrderExists validateOrderExists;
    private Order order;
    private Optional<List<Order>> ordersOptional;
    private Optional<Client> clientOptional;
    private List<Order> orders;


    @BeforeEach
    void setup() {
        order = new Order();
        ordersOptional = Optional.ofNullable(null);
        clientOptional = Optional.ofNullable(null);
        orders = new ArrayList<>();
    }

    @Test
    void ValidateClientThrowTest() {
        when(this.clientService.findById(anyLong())).thenReturn(clientOptional);
        Assertions.assertThrows(RuntimeException.class, () -> this.validateClient.process(order));
    }

    @Test
    void ValidateControlNumberInvalidTest() {
        Assertions.assertThrows(BadRequestException.class, () -> this.validateControlNumber.process(order));
    }

    @Test
    void ValidateControlNumberExistTest() {
        order.setControlCode("1234");
        Optional<List<Order>> orderOptional = Optional.of(orders);
        when(this.orderRepository.findByControlCode(anyString())).thenReturn(orderOptional);
        Assertions.assertThrows(RuntimeException.class, () -> this.validateControlNumber.process(order));
    }

    @Test
    void ValidateControlNumberSuccessTest() {
        order.setControlCode("1234");
        Optional<List<Order>> orderOptional = Optional.ofNullable(null);
        when(this.orderRepository.findByControlCode(anyString())).thenReturn(orderOptional);
        Assertions.assertNotNull(this.validateControlNumber.process(order));
    }

    @Test
    void ValidateDiscountTest() {
        order.setOrderValue(10.0);
        order.setQuantity(11);
        Order newOrder = (Order) this.validateDiscount.process(order);
        Assertions.assertEquals(newOrder.getOrderValue(), 9.0);
    }

    @Test
    void ValidateOrderExistsByCtrlCodeTest() {
        order.setControlCode("1234");
        orders.add(order);
        Optional<List<Order>> optionalOrder = Optional.of(orders);
        when(this.orderRepository.findByControlCode(anyString())).thenReturn(optionalOrder);
        Assertions.assertNotNull(this.validateOrderExists.process(order));
    }

    @Test
    void ValidateOrderExistsByOrderDateTest() {
        order.setOrderDate(LocalDateTime.now());
        orders.add(order);
        Optional<List<Order>> optionalOrder = Optional.of(orders);
        when(this.orderRepository.findByOrderDate(any(LocalDateTime.class))).thenReturn(optionalOrder);
        Assertions.assertNotNull(this.validateOrderExists.process(order));
    }

    @Test
    void ValidateOrderExistsByOrderValueTest() {
        order.setOrderValue(10.0);
        orders.add(order);
        Optional<List<Order>> optionalOrder = Optional.of(orders);
        when(this.orderRepository.findByOrderValue(anyDouble())).thenReturn(optionalOrder);
        Assertions.assertNotNull(this.validateOrderExists.process(order));
    }

    @Test
    void ValidateOrderExistsByProductNameTest() {
        order.setProductName("Test");
        orders.add(order);
        Optional<List<Order>> optionalOrder = Optional.of(orders);
        when(this.orderRepository.findByProductName(anyString())).thenReturn(optionalOrder);
        Assertions.assertNotNull(this.validateOrderExists.process(order));
    }

    @Test
    void ValidateOrderExistsByProductValueTest() {
        order.setProductValue(10.0);
        orders.add(order);
        Optional<List<Order>> optionalOrder = Optional.of(orders);
        when(this.orderRepository.findByProductValue(anyDouble())).thenReturn(optionalOrder);
        Assertions.assertNotNull(this.validateOrderExists.process(order));
    }

    @Test
    void ValidateOrderExistsByQuantityTest() {
        order.setQuantity(1);
        orders.add(order);
        Optional<List<Order>> optionalOrder = Optional.of(orders);
        when(this.orderRepository.findByQuantity(anyInt())).thenReturn(optionalOrder);
        Assertions.assertNotNull(this.validateOrderExists.process(order));
    }

    @Test
    void ValidateOrderExistsByCustomerCodeTest() {
        order.setCustomerCode(1L);
        orders.add(order);
        Optional<List<Order>> optionalOrder = Optional.of(orders);
        when(this.orderRepository.findByCustomerCode(anyLong())).thenReturn(optionalOrder);
        Assertions.assertNotNull(this.validateOrderExists.process(order));
    }
}