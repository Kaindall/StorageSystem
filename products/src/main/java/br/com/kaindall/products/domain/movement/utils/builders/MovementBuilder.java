package br.com.kaindall.products.domain.movement.utils.builders;

import br.com.kaindall.products.domain.product.entities.Product;
import br.com.kaindall.products.domain.movement.utils.enums.MovementType;
import br.com.kaindall.products.domain.movement.entities.Movement;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class MovementBuilder {
    private Long movementId;
    private Long orderId;
    private Product product;
    private MovementType type;
    private int quantity;
    private BigDecimal price;
    private LocalDateTime date;

    public MovementBuilder withMovementId(Long movementId) {
        this.movementId = movementId;
        return this;
    }

    public MovementBuilder withOrderId(Long orderId) {
        this.orderId = orderId;
        return this;
    }

    public MovementBuilder withProduct(Product product) {
        this.product = product;
        return this;
    }

    public MovementBuilder withType(MovementType type) {
        this.type = type;
        return this;
    }

    public MovementBuilder withQuantity(int quantity) {
        this.quantity = quantity;
        return this;
    }

    public MovementBuilder withDate(LocalDateTime date) {
        this.date = date;
        return this;
    }

    public MovementBuilder withPrice(BigDecimal price) {
        this.price = price;
        return this;
    }

    public Movement build() {
        return new Movement(
                this.movementId,
                this.orderId,
                this.product,
                this.type,
                this.quantity,
                this.price,
                this.date);
    }
}
