package br.edu.infuse.app.controller;

import br.edu.infuse.app.model.EntityDomain;
import br.edu.infuse.app.model.Order;
import br.edu.infuse.app.service.ClientService;
import br.edu.infuse.app.service.Facade;
import br.edu.infuse.app.service.OrderService;
import br.edu.infuse.app.vh.ClientVh;
import br.edu.infuse.app.vh.EntityVh;
import br.edu.infuse.app.vh.OrderVh;
import br.edu.infuse.app.vo.EntityVo;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
public class AppControllerTest {
    @Mock
    private OrderService orderService;
    @Mock
    private ClientService clientService;

    @Mock
    private OrderVh orderVh;
    @Mock
    private ClientVh clientVh;
    @Mock
    private Map<String, Facade> service;
    @Mock
    private Map<String, EntityVh> viewHelper;
    @Mock
    private List<EntityDomain> entities;
    @InjectMocks
    private AppController appController;

    private Order order;
    private EntityVo entityVo;
    private List<EntityVo> entityVoList;

    @BeforeEach
    void setup() {
        this.order = new Order();
        this.entityVo = new EntityVo();
        this.entities = new ArrayList<>();
        this.entityVoList = new ArrayList<>();
        this.service = new HashMap<>();
        this.service.put("order", this.orderService);
        this.service.put("client", this.clientService);
        this.viewHelper = new HashMap<>();
        this.viewHelper.put("order", this.orderVh);
        this.viewHelper.put("client", this.clientVh);
    }

    @Test
    void listAllTest() {
        when(this.orderService.listAll()).thenReturn(this.entities);
        Assertions.assertNotNull(this.appController.listAll("order"));
    }

    @Test
    void findEntityTest() {
        order.setControlCode("1234");
        order.setOrderDate(LocalDateTime.now());
        order.setCustomerCode(1L);
        order.setQuantity(1);
        order.setOrderValue(10.0);
        order.setProductName("");
        order.setProductValue(10.0);
        when(this.orderVh.getEntity(any(EntityVo.class))).thenReturn(this.order);
        when(this.orderService.findOne(any(Order.class))).thenReturn(this.order);
        when(this.orderVh.getEntityVo(any(Order.class))).thenReturn(this.entityVo);
        Assertions.assertNotNull(this.appController.findEntity("1234",
                LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss")),
                10.0, "", 10.0, 1, 1L));
    }

    @Test
    void saveTest() {
        this.entityVo.setControlCode("123");
        this.entityVo.setCustomerCode(1L);
        this.entityVo.setProductName("");
        this.entityVo.setProductValue(10.0);
        this.entityVo.setOrderValue(10.0);
        this.entityVo.setQuantity(1);
        this.entityVo.setOrderDate("01/01/2023 00:00:00");
        this.entityVoList.add(this.entityVo);

        this.order.setControlCode("123");
        this.order.setCustomerCode(1L);
        this.order.setProductName("");
        this.order.setProductValue(10.0);
        this.order.setOrderValue(10.0);
        this.order.setQuantity(1);
        this.order.setOrderDate(LocalDateTime.now());

        when(this.orderVh.getEntity(any(EntityVo.class))).thenReturn(this.order);
        when(this.orderService.save(any(Order.class))).thenReturn(this.order);
        when(this.orderVh.getEntityVo(any(Order.class))).thenReturn(this.entityVo);
        Assertions.assertNotNull(this.appController.save("order", this.entityVoList));
    }
}