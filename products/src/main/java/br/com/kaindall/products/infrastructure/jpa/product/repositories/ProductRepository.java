package br.com.kaindall.products.infrastructure.jpa.product.repositories;

import br.com.kaindall.products.domain.product.entities.Product;
import br.com.kaindall.products.infrastructure.jpa.product.entities.ProductEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends JpaRepository<ProductEntity, Long> {
    ProductEntity save(Product product);
}
