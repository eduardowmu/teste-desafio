package br.edu.infuse.app.service;

import br.edu.infuse.app.model.EntityDomain;
import br.edu.infuse.app.model.Message;
import br.edu.infuse.app.model.Order;
import br.edu.infuse.app.repository.OrderRepository;
import br.edu.infuse.app.utils.EventUtils;
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

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
public class OrderServiceTest {
    @Mock
    private OrderRepository orderRepository;
    @Mock
    private ClientService clientService;
    @Mock
    private MessageService messageService;
    @InjectMocks
    private OrderService orderService;
    private Order order;
    private List<Order> orders;
    private Message message;

    @BeforeEach
    void setup() {
        order = new Order();
        orders = new ArrayList<>();
        message = new Message();
        when(this.messageService.save(any(Message.class))).thenReturn(message);
        when(this.orderRepository.findAll()).thenReturn(orders);
    }

    @Test
    void saveTestThrowException() {
        when(this.orderRepository.save(any(Order.class))).thenThrow(new RuntimeException());
        Assertions.assertThrows(RuntimeException.class, () -> this.orderService.save(order));
    }

    @Test
    void listAllTest() {
        Assertions.assertNotNull(this.orderService.listAll());
    }

    @Test
    void listAllWithThrowTest() {
        when(this.orderRepository.findAll()).thenThrow(new RuntimeException());
        Assertions.assertThrows(RuntimeException.class, () -> this.orderService.listAll());
    }

    @Test
    void findOneTest() {
        Assertions.assertThrows(RuntimeException.class, () -> this.orderService.findOne(order));
    }
}