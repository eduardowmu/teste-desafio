package br.edu.infuse.app.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.edu.infuse.app.model.EntityDomain;
import br.edu.infuse.app.repository.OrderRepository;
@Service
public class OrderService extends FacadeImpl {
	@Autowired
	private OrderRepository orderRepository;
	
	@Autowired
	private ClientService clientService;
	
	@Override
	public EntityDomain save(EntityDomain ed) {
		
		return null;
	}

	@Override
	public List<EntityDomain> listAll() {
		return null;
	}
}