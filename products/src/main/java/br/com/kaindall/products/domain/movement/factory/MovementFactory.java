package br.com.kaindall.products.domain.movement.factory;

import br.com.kaindall.products.domain.movement.MovementCancel;
import br.com.kaindall.products.domain.movement.MovementDecrease;
import br.com.kaindall.products.domain.movement.MovementIncrease;
import br.com.kaindall.products.domain.movement.MovementInterface;
import br.com.kaindall.products.domain.movement.utils.enums.MovementType;

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

    public MovementInterface getMovement(MovementType movementType) {
        return switch (movementType) {
            case MovementType.IN -> this.movementIncrease;
            case MovementType.OUT -> this.movementDecrease;
            case CANCELED -> this.movementCancel;
        };
    }
}
