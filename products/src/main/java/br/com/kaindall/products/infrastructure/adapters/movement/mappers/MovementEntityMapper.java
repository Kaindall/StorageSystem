package br.com.kaindall.products.infrastructure.adapters.movement.mappers;

import br.com.kaindall.products.domain.movement.entities.Movement;
import br.com.kaindall.products.domain.movement.utils.builders.MovementBuilder;
import br.com.kaindall.products.infrastructure.adapters.product.mappers.ProductEntityMapper;
import br.com.kaindall.products.infrastructure.jpa.movement.entities.MovementEntity;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
public class MovementEntityMapper {
    private final ProductEntityMapper productMapper;

    public MovementEntityMapper(ProductEntityMapper productMapper) {
        this.productMapper = productMapper;
    }

    public MovementEntity toEntity(Movement movement) {
        return new MovementEntity(
                movement.movementId(),
                productMapper.toEntity(movement.product()),
                movement.orderId(),
                movement.type(),
                movement.quantity(),
                movement.date()
        );
    }

    public Movement toDomain(MovementEntity movementEntity) {
        return new MovementBuilder()
                .withMovementId(movementEntity.getMovementId())
                .withOrderId(movementEntity.getOrderId())
                .withProduct(productMapper.toDomain(movementEntity.getProduct()))
                .withType(movementEntity.getType())
                .withQuantity(movementEntity.getQuantity())
                .withPrice(movementEntity
                        .getProduct()
                        .getPrice()
                        .multiply(BigDecimal.valueOf(movementEntity.getQuantity())))
                .withDate(movementEntity.getDate())
                .build();

    }
}
