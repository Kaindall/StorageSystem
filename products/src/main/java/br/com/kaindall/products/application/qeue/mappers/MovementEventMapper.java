package br.com.kaindall.products.application.qeue.mappers;

import br.com.kaindall.products.application.qeue.dtos.MovementEvent;
import br.com.kaindall.products.domain.movement.entities.Movement;
import br.com.kaindall.products.domain.movement.utils.builders.MovementBuilder;
import br.com.kaindall.products.domain.product.entities.Product;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
public class MovementEventMapper {
    public Movement toDomain(MovementEvent movement, Product product) {
        return new MovementBuilder()
                .withOrderId(movement.orderId())
                .withProduct(product)
                .withType(movement.flow())
                .withQuantity(movement.quantity())
                .withPrice(product.price().multiply(BigDecimal.valueOf(movement.quantity())))
                .withDate(movement.date())
                .build();
    }
}
