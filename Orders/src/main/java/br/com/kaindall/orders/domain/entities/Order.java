package br.com.kaindall.orders.domain.entities;

import br.com.kaindall.orders.domain.utils.enums.OrderStatus;

import java.time.LocalDateTime;
import java.util.List;

public record Order(
        Long id,
        Long userId,
        List<Movement> movements,
        OrderStatus status,
        LocalDateTime date
) {
}
