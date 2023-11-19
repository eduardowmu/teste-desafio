package br.edu.infuse.app.vh;

import br.edu.infuse.app.mapper.OrderMapper;
import br.edu.infuse.app.model.EntityDomain;
import br.edu.infuse.app.model.Order;
import br.edu.infuse.app.utils.EntityUtils;
import br.edu.infuse.app.vo.EntityVo;
import org.springframework.beans.factory.annotation.Autowired;

//View Helper Patterns
public class OrderVh implements EntityVh {

	@Override
	public Order getEntity(EntityVo vo) {
		return EntityUtils.getOrderFromEntityVo(vo);
	}

	@Override
	public EntityVo getEntityVo(EntityDomain ed) {
		Order order = (Order)ed;
		return EntityUtils.getEntityVoFromOrder(order);
	}
}