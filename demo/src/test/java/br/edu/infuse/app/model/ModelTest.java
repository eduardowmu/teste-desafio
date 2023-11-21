package br.edu.infuse.app.model;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.time.LocalDateTime;

@ExtendWith(SpringExtension.class)
public class ModelTest {
    private Order order;
    private Message message;

    @BeforeEach
    void setup() {
        this.order = Order.builder()
                .quantity(1)
                .productValue(10.0)
                .productName("")
                .controlCode("123")
                .orderDate(LocalDateTime.now())
                .orderValue(10.0)
                .customerCode(1L)
                .build();

        this.message = new Message();
    }

    @Test
    void orderTest() {
        this.order.toString();
        Assertions.assertNotNull(order.getCustomerCode());
    }

    @Test
    void messageTest() {
        this.message.setId(1L);
        this.message.setMsg("");
        this.message.setErrorDate(LocalDateTime.now());
        this.message.getErrorDate();
        this.message.getId();
        Assertions.assertTrue(this.message.getMsg().isEmpty());
    }
}