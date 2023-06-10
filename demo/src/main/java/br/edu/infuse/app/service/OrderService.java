package br.edu.infuse.app.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import br.edu.infuse.app.exception.NotFoundException;
import br.edu.infuse.app.model.Client;
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
	@Autowired
	private OrderRepository orderRepository;
	
	@Autowired
	private ClientService clientService;
	
	@Autowired
	private MessageService messageService;
	
	
	public OrderService() {
		this.rules = new HashMap<>();
		
		//list of Validators to create Order
		ValidateDiscount vd = new ValidateDiscount();
		ValidateControlNumber vc = new ValidateControlNumber();
		
		//Add validates to rules on creating order
		List<Validator> brSaveOrder = new ArrayList<>();
		brSaveOrder.add(vc);
		brSaveOrder.add(vd);
		
		//Add validates to rules on creating order
		List<Validator> brListStudent = new ArrayList<>();
		
		//Creating Maps for each events about students
		Map<String, List<Validator>> orderRules = new HashMap<>();
		orderRules.put(EventUtils.SAVE, brSaveOrder);
		orderRules.put(EventUtils.LIST, brListStudent);
	}
	
	@Override
	public Order save(EntityDomain ed) {
		try {
			Order order = (Order)this.getEntityFromRules(ed, EventUtils.SAVE);
			this.checkOthersExceptions(order);
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

	private void checkOthersExceptions(Order order) throws BadRequestException, NotFoundException {
		Order existedOrder = this.orderRepository.findByControlCode(order.getControlCode());
		if(existedOrder != null) {
			throw new BadRequestException("Numero controle já existente!");
		}
		Client client = this.clientService.findById(order.getCustomerCode())
				.orElseThrow(() -> new NotFoundException("Cliente não encontrado!"));
	}
}