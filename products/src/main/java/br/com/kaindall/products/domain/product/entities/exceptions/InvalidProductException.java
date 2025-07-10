package br.com.kaindall.products.domain.product.entities.exceptions;

import br.com.kaindall.products.domain.exceptions.BusinessException;

public class InvalidProductException extends BusinessException {
    public InvalidProductException() {
        super("Operação inválida nos produtos disponiveis", 904, null);
    }
}
