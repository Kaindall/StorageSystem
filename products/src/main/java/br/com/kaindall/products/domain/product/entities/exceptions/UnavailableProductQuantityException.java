package br.com.kaindall.products.domain.product.entities.exceptions;

import br.com.kaindall.products.domain.exceptions.BusinessException;

import java.util.Map;

public class UnavailableProductQuantityException extends BusinessException {
    public UnavailableProductQuantityException() {
        super("Quantidade insuficiente para ser substraída", 905, null);
    }

    public UnavailableProductQuantityException(Map<String, Object> metadata) {
        super("Quantidade insuficiente para ser substraída", 905, metadata);
    }
}
