package br.com.kaindall.products.infrastructure.jpa.entities;


import br.com.kaindall.products.domain.models.Category;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Where;

import java.math.BigDecimal;
import java.math.BigInteger;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="products")
@Where(clause = "deleted = false")
public class ProductEntity {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Long id;

    @Column(nullable = false, unique = true)
    private String name;

    private String description;

    @ManyToOne
    @JoinColumn(name="id_category")
    private CategoryEntity category;

    private BigDecimal price;

    private int quantity;

    @Column(nullable = false)
    private boolean deleted = false;
}
