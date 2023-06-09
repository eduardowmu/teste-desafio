package br.edu.infuse.app.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.edu.infuse.app.model.EntityDomain;
import br.edu.infuse.app.service.Facade;
import br.edu.infuse.app.service.OrderService;
import br.edu.infuse.app.utils.EntityUtils;
import br.edu.infuse.app.utils.PathUtils;
import br.edu.infuse.app.vh.EntityVh;
import br.edu.infuse.app.vh.OrderVh;
import br.edu.infuse.app.vo.EntityVo;

@RestController
@RequestMapping(PathUtils.INFUSE + PathUtils.ENTITY)
public class AppController {
	private final OrderService orderService;
	private OrderVh orderVh;
	
	private Map<String, Facade> service;
	private Map<String, EntityVh> viewHelper;
	
	@Autowired
	public AppController(OrderService orderService) {
		this.orderService = orderService;
		this.service = new HashMap<>();
		this.service.put(EntityUtils.ORDER, this.orderService);
		
		this.orderVh = new OrderVh();
		this.viewHelper = new HashMap<>();
		this.viewHelper.put(EntityUtils.ORDER, orderVh);
	}
	
	@GetMapping(value = PathUtils.LIST, produces = {"application/json", 
			"application/xml"})
	public List<EntityVo> listAll(@PathVariable(EntityUtils.ENTITY) String entity) {
		List<EntityDomain> entities = this.service.get(entity).listAll();
		List<EntityVo> listVo = new ArrayList<>();
		entities.stream().forEach(e -> this.orderVh.getEntityVo(e));
		return listVo;
	}
}