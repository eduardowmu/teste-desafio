package br.edu.infuse.app.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.edu.infuse.app.model.EntityDomain;

@Service
public class ClientService extends FacadeImpl {
	@Autowired
	private ClientService clientService;
	
	@Override
	public EntityDomain save(EntityDomain ed) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<EntityDomain> listAll() {
		// TODO Auto-generated method stub
		return null;
	}

}