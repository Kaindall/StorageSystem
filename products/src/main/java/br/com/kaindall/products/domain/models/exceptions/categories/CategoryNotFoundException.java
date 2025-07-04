package br.com.kaindall.products.domain.models.exceptions.categories;

import br.com.kaindall.products.domain.models.exceptions.BusinessException;

public class CategoryNotFoundException extends BusinessException {
    public CategoryNotFoundException() {
        super("A categoria não foi encontrada", 800);
    }
    public CategoryNotFoundException(String categoryName) {
        super("A categoria " + categoryName + " não foi encontrada", 801);
    }
}
