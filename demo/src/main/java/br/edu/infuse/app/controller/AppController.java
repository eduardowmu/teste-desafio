package br.edu.infuse.app.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import br.edu.infuse.app.exception.BadRequestException;
import br.edu.infuse.app.model.Order;
import br.edu.infuse.app.vh.ClientVh;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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
	//services
	private final OrderService orderService;
	private final ClientService clientService;

	//View Helper pattern
	private OrderVh orderVh;
	private ClientVh clientVh;
	
	private Map<String, Facade> service;
	private Map<String, EntityVh> viewHelper;
	
	@Autowired
	public AppController(OrderService orderService, ClientService clientService) {
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

	@Operation(summary = "Listagem de, pedidos (entity = order) ou clientes (entity = client), cadastrados na base")
	@GetMapping(value = PathUtils.LIST, produces = {"application/json","application/xml"})
	public List<EntityVo> listAll(@PathVariable(EntityUtils.ENTITY) String entity) {
		List<EntityDomain> entities = this.service.get(entity).listAll();
		List<EntityVo> listVo = new ArrayList<>();
		entities.stream().forEach(e -> listVo.add(this.viewHelper.get(entity).getEntityVo(e)));
		return listVo;
	}

	@Operation(summary = "Pesquisa de pedido através de filtro. ATENÇÃO: orderDate -> dd/MM/yyyy HH:mm:ss")
	@GetMapping(produces = {"application/json", "application/xml"})
	public EntityVo findEntity(@RequestParam("controlCode") String controlCode, @RequestParam("orderDate") String orderDate,
							   @RequestParam("orderValue") Double orderValue, @RequestParam("productName") String productName,
							   @RequestParam("productValue") Double productValue, @RequestParam("quantity") Integer quantity,
							   @RequestParam("customerCode") Long customerCode) {
		EntityVo vo = EntityVo.builder()
				.controlCode(controlCode)
				.orderDate(orderDate)
				.productName(productName)
				.productValue(productValue)
				.quantity(quantity)
				.orderValue(orderValue)
				.customerCode(customerCode)
				.build();

		Order request = (Order) this.viewHelper.get(EntityUtils.ORDER).getEntity(vo);
		Order response = (Order) this.service.get(EntityUtils.ORDER).findOne(request);
		return this.viewHelper.get(EntityUtils.ORDER).getEntityVo(response);
	}

	@Operation(summary = "Criação de uma lista de pedidos (entity = order) ou de clientes (entity = client). " +
			"ATENÇÃO: orderDate -> dd/MM/yyyy HH:mm:ss")
	@PostMapping(value = PathUtils.SAVE, produces = {"application/json", "application/xml"},
			consumes = {"application/json", "application/xml"})
	public List<EntityVo> save(@PathVariable(EntityUtils.ENTITY) String entity, @RequestBody List<EntityVo> entityVoList) {
		List<EntityVo> response = new ArrayList<>();
		if(entityVoList.size() > 10) {
			throw new BadRequestException("Quantidade acima do permitido: " + entityVoList.size());
		}
		entityVoList.forEach(vo -> response.add(this.saveEntity(entity, vo)));
		return response;
	}

	private EntityVo saveEntity(String entity, EntityVo vo) {
		EntityDomain request = this.viewHelper.get(entity).getEntity(vo);
		EntityDomain response = this.service.get(entity).save(request);
		EntityVo responseVo = this.viewHelper.get(entity).getEntityVo(response);
		return responseVo;
	}
}