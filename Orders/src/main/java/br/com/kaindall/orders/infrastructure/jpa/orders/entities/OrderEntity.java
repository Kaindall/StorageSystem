package br.com.kaindall.orders.infrastructure.jpa.orders.entities;

import br.com.kaindall.orders.domain.orders.entities.OrderStatus;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="pedidos")
public class OrderEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="order_id")
    private Long orderId;

    @Column(name="user_id")
    private Long userId;

    @Column(name="status")
    private OrderStatus status;

    @Column(name="order_dt")
    private LocalDateTime date;
}
