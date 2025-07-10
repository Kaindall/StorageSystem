package br.com.kaindall.products.domain.category.entities.exceptions;

import br.com.kaindall.products.domain.exceptions.BusinessException;

public class InvalidCategoryException extends BusinessException {
    public InvalidCategoryException() {
        super("Operação inválida ao modificar informações das categorias de produtos disponiveis", 804, null);
    }
}
