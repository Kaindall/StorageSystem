package br.com.kaindall.orders.infrastructure.jpa.repositories;

import br.com.kaindall.orders.infrastructure.jpa.entities.OrderEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface OrderRepository extends JpaRepository<OrderEntity, Long> {
    Page<OrderEntity> findAllByUserId(Long userId, Pageable pageable);
}
