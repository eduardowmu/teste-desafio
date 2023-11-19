package br.edu.infuse.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.edu.infuse.app.model.Order;
@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {
	Order findByControlCode(String controlCode);
}