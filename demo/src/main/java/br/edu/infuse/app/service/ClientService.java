package br.edu.infuse.app.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import br.edu.infuse.app.exception.BadRequestException;
import br.edu.infuse.app.utils.EventUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.edu.infuse.app.model.Client;
import br.edu.infuse.app.model.EntityDomain;
import br.edu.infuse.app.repository.ClientRepository;

@Service
public class ClientService extends FacadeImpl {
	@Autowired
	private ClientRepository clientRepository;

	@Autowired
	private MessageService messageService;
	
	@Override
	public Client save(EntityDomain ed) {
		try {
			Client client = (Client)ed;
			return this.clientRepository.save(client);
		}catch (Exception e) {
			this.messageService.save(EventUtils.saveException(e));
			throw e;
		}
	}

	@Override
	public List<EntityDomain> listAll() {
		try {
			List<Client> clients = this.clientRepository.findAll();
			List<EntityDomain> entityDomains = new ArrayList<>();
			clients.forEach(c -> entityDomains.add(c));
			return entityDomains;
		}catch (Exception e) {
			this.messageService.save(EventUtils.saveException(e));
			throw e;
		}
	}

	@Override
	public EntityDomain findOne(EntityDomain ed) {
		return null;
	}

	public Optional<Client> findById(Long id) {
		try {
			return this.clientRepository.findById(id);
		}catch (Exception e) {
			this.messageService.save(EventUtils.saveException(e));
			throw e;
		}
	}
}