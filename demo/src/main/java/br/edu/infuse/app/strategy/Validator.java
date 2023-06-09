package br.edu.infuse.app.strategy;

import br.edu.infuse.app.model.EntityDomain;

public interface Validator {
	EntityDomain process(EntityDomain ed);
}