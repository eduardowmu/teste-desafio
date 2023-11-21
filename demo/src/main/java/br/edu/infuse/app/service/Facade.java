package br.edu.infuse.app.service;

import java.util.List;

import br.edu.infuse.app.model.EntityDomain;
import br.edu.infuse.app.model.Order;

public interface Facade {
	EntityDomain save(EntityDomain ed);
	List<EntityDomain> listAll();

	EntityDomain findOne(EntityDomain ed);
}