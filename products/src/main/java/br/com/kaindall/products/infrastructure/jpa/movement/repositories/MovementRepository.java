package br.com.kaindall.products.infrastructure.jpa.movement.repositories;

import br.com.kaindall.products.infrastructure.jpa.movement.entities.MovementEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Set;

@Repository
public interface MovementRepository extends CrudRepository<MovementEntity, Long> {
    List<MovementEntity> findAllByOrderId(Long orderId);

}
