package br.edu.infuse.app.model;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

import lombok.Getter;
import lombok.Setter;

@SuppressWarnings("serial")
@Getter
@Setter
@MappedSuperclass
public abstract class EntityDomain {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
}