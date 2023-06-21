package br.edu.infuse.app.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import br.edu.infuse.app.exception.NotFoundException;
import br.edu.infuse.app.model.Client;
import br.edu.infuse.app.strategy.ValidateClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.edu.infuse.app.exception.BadRequestException;
import br.edu.infuse.app.model.EntityDomain;
import br.edu.infuse.app.model.Order;
import br.edu.infuse.app.repository.OrderRepository;
import br.edu.infuse.app.strategy.ValidateControlNumber;
import br.edu.infuse.app.strategy.ValidateDiscount;
import br.edu.infuse.app.strategy.Validator;
import br.edu.infuse.app.utils.EventUtils;

@Service
public class OrderService extends FacadeImpl {
	private final OrderRepository orderRepository;
	
	private final ClientService clientService;
	
	@Autowired
	private MessageService messageService;


	@Autowired
	public OrderService(OrderRepository orderRepository, ClientService clientService) {
		this.orderRepository = orderRepository;
		this.clientService = clientService;
		this.rules = new HashMap<>();
		
		//list of Validators to create Order
		ValidateDiscount vd = new ValidateDiscount();
		ValidateControlNumber vc = new ValidateControlNumber(this.orderRepository);
		ValidateClient vcl = new ValidateClient(this.clientService);
		
		//Add validates to rules on creating order
		List<Validator> brSaveOrder = new ArrayList<>();
		brSaveOrder.add(vc);
		brSaveOrder.add(vd);
		brSaveOrder.add(vcl);
		
		//Add validates to rules on creating order
		List<Validator> brListStudent = new ArrayList<>();
		
		//Creating Maps for each events about students
		Map<String, List<Validator>> orderRules = new HashMap<>();
		orderRules.put(EventUtils.SAVE, brSaveOrder);
		orderRules.put(EventUtils.LIST, brListStudent);

        this.rules.put(Order.class.getSimpleName(), orderRules);
	}
	
	@Override
	public Order save(EntityDomain ed) {
		try {
			Order order = (Order)this.getEntityFromRules(ed, EventUtils.SAVE);
			return this.orderRepository.save(order);
		} catch(Exception e) {
			this.messageService.save(EventUtils.saveException(e));
			throw e;
		}
	}

	@Override
	public List<EntityDomain> listAll() {
		List<Order> orders = this.orderRepository.findAll();
		List<EntityDomain> entities = new ArrayList<>();
		int i = 0;
		for(Order o : orders) {
			if(i == 9) break;
			entities.add(o);
			i++;
		}
		return entities;
	}
}