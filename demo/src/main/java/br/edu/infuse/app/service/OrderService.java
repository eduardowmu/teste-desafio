package br.edu.infuse.app.service;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import br.edu.infuse.app.strategy.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.edu.infuse.app.model.EntityDomain;
import br.edu.infuse.app.model.Order;
import br.edu.infuse.app.repository.OrderRepository;
import br.edu.infuse.app.utils.EventUtils;

@Service
public class OrderService extends FacadeImpl {
	private final OrderRepository orderRepository;

	private final ClientService clientService;

	private final MessageService messageService;
//	@Autowired
//	private OrderRepository orderRepository;
//
//	@Autowired
//	private ClientService clientService;
//
//	@Autowired
//	private MessageService messageService;

	@Autowired
	public OrderService(OrderRepository orderRepository, ClientService clientService,
						MessageService messageService) {
		this.orderRepository = orderRepository;
		this.clientService = clientService;
		this.messageService = messageService;

		this.rules = new HashMap<>();
		
		//list of Strategy Validators to create Order
		ValidateDiscount vd = new ValidateDiscount();
		ValidateControlNumber vc = new ValidateControlNumber(this.orderRepository);
		ValidateClient vcl = new ValidateClient(this.clientService);

		//strategy validator to find an Order
		ValidateOrderExists voe = new ValidateOrderExists(this.orderRepository);

		//Add validates to business rules on creating order
		List<Validator> brSaveOrder = new ArrayList<>();
		brSaveOrder.add(vc);
		brSaveOrder.add(vd);
		brSaveOrder.add(vcl);

		//Add validates to business rules on find order
		List<Validator> brFindOrder = new ArrayList<>();
		brFindOrder.add(voe);

		//Creating Maps for each events about students
		Map<String, List<Validator>> orderRules = new HashMap<>();
		orderRules.put(EventUtils.SAVE, brSaveOrder);
		orderRules.put(EventUtils.FIND, brFindOrder);

		this.rules.put(Order.class.getSimpleName(), orderRules);
	}
	
	@Override
	public Order save(EntityDomain ed) {
		try {
			Order order = (Order)this.getEntityFromRules(ed, EventUtils.SAVE);
			return this.orderRepository.save(order);
		} catch(RuntimeException e) {
			this.messageService.save(EventUtils.saveException(e));
			throw e;
		}
	}

	@Override
	public List<EntityDomain> listAll() {
		try {
			List<Order> orders = this.orderRepository.findAll();
			List<EntityDomain> entities = new ArrayList<>();
			orders.forEach(o -> entities.add(o));
			return entities;
		} catch (RuntimeException e) {
			this.messageService.save(EventUtils.saveException(e));
			throw e;
		}
	}

	@Override
	public Order findOne(EntityDomain ed) {
		try {
			Order order = (Order)this.getEntityFromRules(ed, EventUtils.FIND);
			return order;//(Order)this.getEntityFromRules(ed, EventUtils.FIND);
		} catch (RuntimeException e) {
			this.messageService.save(EventUtils.saveException(e));
			throw e;
		}
	}
}