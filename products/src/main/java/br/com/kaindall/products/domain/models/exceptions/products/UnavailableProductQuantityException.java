package br.com.kaindall.products.domain.models.exceptions.products;

import br.com.kaindall.products.domain.models.exceptions.BusinessException;

public class UnavailableProductQuantityException extends BusinessException {
    public UnavailableProductQuantityException() {
        super("Quantidade insuficiente para ser substraída", 905);
    }
}
