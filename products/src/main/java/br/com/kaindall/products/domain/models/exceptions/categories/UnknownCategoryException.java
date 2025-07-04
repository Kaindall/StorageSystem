package br.com.kaindall.products.domain.models.exceptions.categories;

import br.com.kaindall.products.domain.models.exceptions.BusinessException;

public class UnknownCategoryException extends BusinessException {
    public UnknownCategoryException() {
        super("Erro desconhecido ao realizar a operação nas categorias de produtos disponiveis", 803);
    }
}
