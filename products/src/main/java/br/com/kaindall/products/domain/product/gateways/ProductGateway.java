package br.com.kaindall.products.domain.product.gateways;

import br.com.kaindall.products.domain.product.entities.Product;
import br.com.kaindall.products.domain.product.entities.ProductsPage;

import java.util.List;

public interface ProductGateway {
    Product increase(Long id, int quantity);

    Product decrease(Long id, int quantity);

    Product findById(Long id);

    boolean deleteById(Long id);

    List<Product> findAll(ProductsPage productsPage);

    Product save(Product product);
}
