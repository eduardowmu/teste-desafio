package br.edu.infuse.app.vh;

import br.edu.infuse.app.model.EntityDomain;
import br.edu.infuse.app.vo.EntityVo;

//View Helper Patterns
public interface EntityVh {
	EntityDomain getEntity(EntityVo vo);
	EntityVo getEntityVo(EntityDomain ed);
}