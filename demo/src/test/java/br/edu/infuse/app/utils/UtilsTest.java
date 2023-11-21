package br.edu.infuse.app.utils;

import br.edu.infuse.app.model.Client;
import br.edu.infuse.app.model.Order;
import br.edu.infuse.app.vo.EntityVo;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
public class UtilsTest {
    private Order order;
    private EntityVo entityVo;
    private Client client;
    @BeforeEach
    void setup() {
        this.order = new Order();
        this.entityVo = new EntityVo();
        this.client = new Client();
    }

    @Test
    void getEntityVoFromOrderTest() {
        this.order.setControlCode("123");
        this.order.setCustomerCode(1L);
        this.order.setProductName("");
        this.order.setProductValue(10.0);
        this.order.setQuantity(1);

        Assertions.assertNotNull(EntityUtils.getEntityVoFromOrder(this.order));
    }

    @Test
    void getClientFromEntityVo() {
        this.entityVo.setName("");
        Assertions.assertNotNull(EntityUtils.getClientFromEntityVo(this.entityVo));
    }

    @Test
    void getEntityVoFromClientTest() {
        this.client.setId(1L);
        this.client.setClientName("");
        Assertions.assertNotNull(EntityUtils.getEntityVoFromClient(this.client));
    }
}