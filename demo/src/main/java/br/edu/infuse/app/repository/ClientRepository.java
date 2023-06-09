package br.edu.infuse.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.edu.infuse.app.model.Client;

public interface ClientRepository extends JpaRepository<Client, Long> {

}
