package br.edu.infuse.app.vh;

import br.edu.infuse.app.model.EntityDomain;
import br.edu.infuse.app.vo.EntityVo;

public interface EntityVh {
	EntityDomain getEntity(EntityVo vo);
	EntityVo getEntityVo(EntityDomain ed);
}