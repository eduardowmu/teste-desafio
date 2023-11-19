package br.edu.infuse.app.service;

import java.util.List;
import java.util.Optional;

import br.edu.infuse.app.exception.BadRequestException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.edu.infuse.app.model.Client;
import br.edu.infuse.app.model.EntityDomain;
import br.edu.infuse.app.repository.ClientRepository;

@Service
public class ClientService extends FacadeImpl {
	@Autowired
	private ClientRepository clientRepository;
	
	@Override
	public Client save(EntityDomain ed) {
		Client client = (Client)ed;
		return this.clientRepository.save(client);
	}

	@Override
	public List<EntityDomain> listAll() {
		return null;
	}

	public Optional<Client> findById(Long id) {
		return this.clientRepository.findById(id);
	}
}