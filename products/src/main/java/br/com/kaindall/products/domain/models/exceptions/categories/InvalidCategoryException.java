package br.com.kaindall.products.domain.models.exceptions.categories;

import br.com.kaindall.products.domain.models.exceptions.BusinessException;

public class InvalidCategoryException extends BusinessException {
    public InvalidCategoryException() {
        super("Operação inválida ao modificar informações das categorias de produtos disponiveis", 804);
    }
}
