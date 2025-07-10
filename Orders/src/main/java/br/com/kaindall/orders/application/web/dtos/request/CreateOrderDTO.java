package br.com.kaindall.orders.application.web.dtos.request;

import java.util.List;

public record CreateOrderDTO(
        Long userId,
        List<MovementRequestDTO> items
) {
}
