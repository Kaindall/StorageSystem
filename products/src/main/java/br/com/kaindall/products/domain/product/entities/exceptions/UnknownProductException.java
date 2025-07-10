package br.com.kaindall.products.domain.product.entities.exceptions;

import br.com.kaindall.products.domain.exceptions.BusinessException;

public class UnknownProductException extends BusinessException {
    public UnknownProductException() {
        super("Erro desconhecido ao realizar a operação nos produtos disponiveis", 903, null);
    }
}
