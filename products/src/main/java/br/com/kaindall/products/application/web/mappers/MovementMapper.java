package br.com.kaindall.products.application.web.mappers;

import br.com.kaindall.products.application.web.dtos.responses.MovementDTO;
import br.com.kaindall.products.domain.movement.entities.Movement;
import br.com.kaindall.products.domain.movement.utils.builders.MovementBuilder;
import br.com.kaindall.products.domain.movement.utils.enums.MovementType;
import br.com.kaindall.products.domain.product.entities.Product;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Component
public class MovementMapper {
    public MovementDTO toDTO(Movement movement) {
        return new MovementDTO(
                movement.movementId(),
                movement.orderId(),
                movement.product().id(),
                movement.movementType(),
                movement.quantity(),
                movement.price(),
                movement.date()
        );
    }

    public Movement toDomain(Product product, int quantity, MovementType movementType) {
        return new MovementBuilder()
                .withProduct(product)
                .withType(movementType)
                .withQuantity(quantity)
                .withPrice(product.price().multiply(BigDecimal.valueOf(quantity)))
                .withDate(LocalDateTime.now())
                .build();
    }
}
