package br.edu.infuse.app.model;

import javax.persistence.Entity;

import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Client extends EntityDomain {
	private String clientName;
}