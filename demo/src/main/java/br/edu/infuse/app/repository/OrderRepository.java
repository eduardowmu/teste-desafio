package br.edu.infuse.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.edu.infuse.app.model.Order;

import java.time.LocalDateTime;
import java.util.Optional;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {
	Optional<Order> findByControlCode(String controlCode);
	Optional<Order> findByOrderDate(LocalDateTime orderDate);
	Optional<Order> findByOrderValue(Double orderValue);
	Optional<Order> findByProductName(String productName);
	Optional<Order> findByProductValue(Double productValue);
	Optional<Order> findByQuantity(Integer quantity);
	Optional<Order> findByCustomerCode(Long customerCode);
}