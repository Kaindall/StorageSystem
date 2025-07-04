package br.com.kaindall.products.domain.models.exceptions.products;

import br.com.kaindall.products.domain.models.exceptions.BusinessException;

public class ProductNotFoundException extends BusinessException {
    public ProductNotFoundException() {
        super("O produto não foi encontrado", 900);
    }
}
