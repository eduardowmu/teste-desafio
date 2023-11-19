package br.edu.infuse.app.model;

import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Builder
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "orders")
public class Order extends EntityDomain {
	private String controlCode;
	private LocalDateTime orderDate;
	private Double orderValue;
	private String productName;
	private Double productValue;
	private Integer quantity;
	private Long customerCode;
}