package br.com.kaindall.orders.domain.entities;

public record OrdersPage(
        int pageNumber,
        int pageSize,
        String sort,
        boolean ascending
) {
}
