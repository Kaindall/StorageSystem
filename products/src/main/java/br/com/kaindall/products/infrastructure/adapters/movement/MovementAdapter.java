package br.com.kaindall.products.infrastructure.adapters.movement;

import br.com.kaindall.products.domain.movement.entities.exceptions.MovementNotFoundException;
import br.com.kaindall.products.domain.movement.gateways.MovementGateway;
import br.com.kaindall.products.domain.movement.entities.Movement;
import br.com.kaindall.products.infrastructure.adapters.movement.mappers.MovementEntityMapper;
import br.com.kaindall.products.infrastructure.jpa.movement.repositories.MovementRepository;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.NoSuchElementException;

@Component
public class MovementAdapter implements MovementGateway {
    private final MovementRepository movementRepository;
    private final MovementEntityMapper movementMapper;

    public MovementAdapter(MovementRepository movementRepository, MovementEntityMapper movementMapper) {
        this.movementRepository = movementRepository;
        this.movementMapper = movementMapper;
    }

    @Override
    public Movement save(Movement movement) {
        return movementMapper.toDomain(movementRepository.save(movementMapper.toEntity(movement)));
    }

    @Override
    public List<Movement> findAll(Long orderId) {
        try {
            return movementRepository
                    .findAllByOrderId(orderId)
                    .stream()
                    .map(movementMapper::toDomain)
                    .toList();
        } catch (NoSuchElementException e) {
            throw new MovementNotFoundException();
        }
    }

    @Override
    public void saveAll(List<Movement> movements) {
        movementRepository.saveAll(movements.stream().map(movementMapper::toEntity).toList());
    }
}
