package br.com.kaindall.products.infrastructure.jpa.movement.entities;

import br.com.kaindall.products.domain.movement.utils.enums.MovementType;
import br.com.kaindall.products.infrastructure.jpa.product.entities.ProductEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="registries")
public class MovementEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="id_movement")
    private Long movementId;

    @ManyToOne
    @JoinColumn(name="id_product")
    private ProductEntity product;

    @Column(name="id_order")
    private Long orderId;

    private MovementType type;

    private int quantity;

    private LocalDateTime date;
}
