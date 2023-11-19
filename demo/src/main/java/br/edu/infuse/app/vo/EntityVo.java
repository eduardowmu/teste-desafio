package br.edu.infuse.app.vo;

import javax.persistence.Entity;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class EntityVo {
	//for both entities
	private Long id;

	//for client
	private String name;

	//for Order
	private String controlCode;
	private String orderDate;
	private Double orderValue;
	private String productName;
	private Double productValue;
	private Integer quantity;
	private Long customerCode;
}