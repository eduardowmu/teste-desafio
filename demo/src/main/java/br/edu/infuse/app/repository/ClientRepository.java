package br.edu.infuse.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.edu.infuse.app.model.Client;
@Repository
public interface ClientRepository extends JpaRepository<Client, Long> {}