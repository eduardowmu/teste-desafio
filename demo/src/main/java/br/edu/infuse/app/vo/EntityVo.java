package br.edu.infuse.app.vo;

import javax.persistence.Entity;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@Entity
public class EntityVo {
	private Long id;
	private String controlCode;
	private String orderDate;
	private Double orderValue;
	private String productName;
	private Double productValue;
	private Integer quantity;
	private String customerCode;
}