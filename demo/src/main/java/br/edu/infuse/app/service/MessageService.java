package br.edu.infuse.app.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.edu.infuse.app.model.EntityDomain;
import br.edu.infuse.app.model.Message;
import br.edu.infuse.app.repository.MessageRepository;

@Service
public class MessageService extends FacadeImpl {
	@Autowired
	private MessageRepository messageRepository;
	
	@Override
	public EntityDomain save(EntityDomain ed) {
		Message message = (Message)ed;
		return this.messageRepository.save(message);
	}

	@Override
	public List<EntityDomain> listAll() {
		List<Message> messages = this.messageRepository.findAll();
		List<EntityDomain> entities = new ArrayList<>();
		messages.stream().forEach(m -> entities.add(m));
		return entities;
	}

	@Override
	public EntityDomain findOne(EntityDomain ed) {
		return null;
	}
}