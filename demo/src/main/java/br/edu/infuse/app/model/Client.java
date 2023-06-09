package br.edu.infuse.app.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
@Entity
public class Client extends EntityDomain {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
}