package br.com.kaindall.products.domain.product.entities.exceptions;

import br.com.kaindall.products.domain.exceptions.BusinessException;

import java.util.Map;

public class ProductNotFoundException extends BusinessException {

    public ProductNotFoundException(Long productId) {
        super("O produto " + productId + " não foi encontrado", 900, Map.of("productId", productId));
    }

    public ProductNotFoundException(Long productId, Map<String, Object> metadata) {
        super("O produto " + productId + " não foi encontrado", 900, metadata);
    }
}
