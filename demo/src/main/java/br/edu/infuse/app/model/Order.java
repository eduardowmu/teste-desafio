package br.edu.infuse.app.model;

import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@Entity
public class Order extends EntityDomain {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String controlCode;
	private LocalDateTime orderDate;
	private Double orderValue;
	private String productName;
	private Double productValue;
	private Integer quantity;
	private String customerCode;
}