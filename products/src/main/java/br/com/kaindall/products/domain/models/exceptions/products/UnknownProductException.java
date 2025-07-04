package br.com.kaindall.products.domain.models.exceptions.products;

import br.com.kaindall.products.domain.models.exceptions.BusinessException;

public class UnknownProductException extends BusinessException {
    public UnknownProductException() {
        super("Erro desconhecido ao realizar a operação nas categorias de produtos disponiveis", 903);
    }
}
