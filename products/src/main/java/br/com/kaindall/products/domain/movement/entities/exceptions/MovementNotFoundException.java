package br.com.kaindall.products.domain.movement.entities.exceptions;

import br.com.kaindall.products.domain.exceptions.BusinessException;

public class MovementNotFoundException extends BusinessException {

    public MovementNotFoundException() {
        super("A movimentação não foi encontrada", 700, null);
    }
}
