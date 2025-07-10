package br.com.kaindall.products.domain.product.entities;

public record ProductsPage(
        int pageNumber,
        int pageSize,
        String sort,
        boolean ascending
) {
}
