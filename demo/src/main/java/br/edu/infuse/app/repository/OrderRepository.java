package br.edu.infuse.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import br.edu.infuse.app.model.Order;

public interface OrderRepository extends JpaRepository<Order, Long> {

}