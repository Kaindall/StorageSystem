package br.com.kaindall.orders.infrastructure.jpa.entities;

import br.com.kaindall.orders.domain.utils.enums.OrderStatus;
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
    private Long id;

    @Column(name="user_id")
    private Long userId;

    @Column(name="status")
    private OrderStatus status;

    @Column(name="order_dt")
    private LocalDateTime date;
}
