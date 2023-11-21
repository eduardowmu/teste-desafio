package br.edu.infuse.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.edu.infuse.app.model.Order;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {
	Optional<List<Order>> findByControlCode(String controlCode);
	Optional<List<Order>> findByOrderDate(LocalDateTime orderDate);
	Optional<List<Order>> findByOrderValue(Double orderValue);
	Optional<List<Order>> findByProductName(String productName);
	Optional<List<Order>> findByProductValue(Double productValue);
	Optional<List<Order>> findByQuantity(Integer quantity);
	Optional<List<Order>> findByCustomerCode(Long customerCode);
}