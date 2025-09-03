package br.com.kaindall.products.domain.movement.factories;

import br.com.kaindall.products.domain.movement.strategies.impl.MovementCancel;
import br.com.kaindall.products.domain.movement.strategies.impl.MovementDecrease;
import br.com.kaindall.products.domain.movement.strategies.impl.MovementIncrease;
import br.com.kaindall.products.domain.movement.strategies.MovementStrategy;
import br.com.kaindall.products.domain.movement.utils.enums.MovementType;
import org.springframework.stereotype.Component;

@Component
public class MovementFactory {

    private final MovementIncrease movementIncrease;
    private final MovementDecrease movementDecrease;
    private final MovementCancel movementCancel;

    public MovementFactory(
            MovementIncrease movementIncrease,
            MovementDecrease movementDecrease,
            MovementCancel movementCancel
    ) {
        this.movementIncrease = movementIncrease;
        this.movementDecrease = movementDecrease;
        this.movementCancel = movementCancel;
    }

    public MovementStrategy getMovementStrategy(MovementType movementType) {
        return switch (movementType) {
            case MovementType.IN -> this.movementIncrease;
            case MovementType.OUT -> this.movementDecrease;
            case CANCELED -> this.movementCancel;
        };
    }
}
