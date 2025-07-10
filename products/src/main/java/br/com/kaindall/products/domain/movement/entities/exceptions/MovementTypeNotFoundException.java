package br.com.kaindall.products.domain.movement.entities.exceptions;

import br.com.kaindall.products.domain.exceptions.BusinessException;

public class MovementTypeNotFoundException extends BusinessException {
    public MovementTypeNotFoundException() {
        super("O tipo da movimentação não foi encontrado", 702, null);
    }
}
