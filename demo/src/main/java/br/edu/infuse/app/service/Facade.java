package br.edu.infuse.app.service;

import java.util.List;

import br.edu.infuse.app.model.EntityDomain;

public interface Facade {
	EntityDomain save(EntityDomain ed);
	List<EntityDomain> listAll();
}