package br.edu.infuse.app.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import br.edu.infuse.app.vh.ClientVh;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.edu.infuse.app.model.EntityDomain;
import br.edu.infuse.app.service.ClientService;
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
	private final ClientService clientService;
	private OrderVh orderVh;
	private ClientVh clientVh;
	
	private Map<String, Facade> service;
	private Map<String, EntityVh> viewHelper;
	
	@Autowired
	public AppController(OrderService orderService,ClientService clientService) {
		this.orderService = orderService;
		this.clientService = clientService;
		this.service = new HashMap<>();
		this.service.put(EntityUtils.ORDER, this.orderService);
		this.service.put(EntityUtils.CLIENT, this.clientService);
		
		this.orderVh = new OrderVh();
		this.clientVh = new ClientVh();
		this.viewHelper = new HashMap<>();
		this.viewHelper.put(EntityUtils.ORDER, this.orderVh);
		this.viewHelper.put(EntityUtils.CLIENT, this.clientVh);
	}
	
	@GetMapping(value = PathUtils.LIST, produces = {"application/json", 
			"application/xml"})
	public List<EntityVo> listAll(@PathVariable(EntityUtils.ENTITY) String entity) {
		List<EntityDomain> entities = this.service.get(entity).listAll();
		List<EntityVo> listVo = new ArrayList<>();
		entities.stream().forEach(e -> listVo.add(this.orderVh.getEntityVo(e)));
		return listVo;
	}
	
	@PostMapping(value = PathUtils.SAVE, produces = {"application/json", "application/xml"}, 
				consumes = {"application/json", "application/xml"})
	public EntityVo save(@PathVariable(EntityUtils.ENTITY) String entity, @RequestBody EntityVo vo) {
		EntityDomain request = this.viewHelper.get(entity).getEntity(vo);
		EntityDomain response = this.service.get(entity).save(request);
		EntityVo responseVo = this.viewHelper.get(entity).getEntityVo(response);
		return responseVo;
	}
}