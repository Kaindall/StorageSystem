package br.com.kaindall.products.domain.movement.entities.exceptions;

import br.com.kaindall.products.domain.exceptions.BusinessException;
import br.com.kaindall.products.domain.movement.entities.Movement;

import java.util.Map;

public class InvalidMovementException extends BusinessException {
    public InvalidMovementException(Map<String, Object> metadata) {
        super("A movimentação tentada é inválida", 701, metadata);
    }
}
