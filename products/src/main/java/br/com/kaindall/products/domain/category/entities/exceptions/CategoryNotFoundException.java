package br.com.kaindall.products.domain.category.entities.exceptions;

import br.com.kaindall.products.domain.exceptions.BusinessException;

import java.util.HashMap;
import java.util.Map;

public class CategoryNotFoundException extends BusinessException {
    public CategoryNotFoundException() {
        super("A categoria não foi encontrada", 800, null);
    }
    public CategoryNotFoundException(String categoryName) {
        super("A categoria " + categoryName + " não foi encontrada", 801, Map.of("categoryName", categoryName));
    }
}
