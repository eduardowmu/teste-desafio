package br.edu.infuse.app.service;

import java.util.List;
import java.util.Map;

import br.edu.infuse.app.model.EntityDomain;
import br.edu.infuse.app.model.Message;
import br.edu.infuse.app.strategy.Validator;

public abstract class FacadeImpl implements Facade {
	protected Map<String, Map<String, List<Validator>>> rules;
	//this method returns an Entity or an Exception, according to some business rules
	protected EntityDomain getEntityFromRules(EntityDomain ed, String event) {
		Map<String, List<Validator>> operationRules = this.rules.get(ed.getClass().getSimpleName());
		EntityDomain entity = null;
		if(operationRules != null) {
			List<Validator> rulesList = operationRules.get(event);
			if(rulesList != null) {
				for(Validator rule : rulesList) {
					entity = rule.process(ed);
					if(entity instanceof Message) {
						return (Message)entity;
					}
				}
			}
		}
		return entity;
	}
}