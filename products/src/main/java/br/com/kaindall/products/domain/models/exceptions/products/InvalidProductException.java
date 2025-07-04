package br.com.kaindall.products.domain.models.exceptions.products;

import br.com.kaindall.products.domain.models.exceptions.BusinessException;

public class InvalidProductException extends BusinessException {
    public InvalidProductException() {
        super("Operação inválida para realizar operações nos produtos disponiveis", 904);
    }
}
