package br.com.kaindall.orders.domain.orders.entities;

import br.com.kaindall.orders.domain.movements.entities.Movement;

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
