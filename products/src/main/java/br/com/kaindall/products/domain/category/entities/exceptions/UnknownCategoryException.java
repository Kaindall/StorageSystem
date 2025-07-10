package br.com.kaindall.products.domain.category.entities.exceptions;

import br.com.kaindall.products.domain.exceptions.BusinessException;

public class UnknownCategoryException extends BusinessException {
    public UnknownCategoryException() {
        super("Erro desconhecido ao realizar a operação nas categorias de produtos disponiveis", 803, null);
    }
}
