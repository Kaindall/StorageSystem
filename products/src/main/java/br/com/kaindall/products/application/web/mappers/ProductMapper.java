package br.com.kaindall.products.application.web.mappers;

import br.com.kaindall.products.application.web.dtos.requests.CreateProductDTO;
import br.com.kaindall.products.application.web.dtos.requests.UpdateProductDTO;
import br.com.kaindall.products.application.web.dtos.responses.ProductDTO;
import br.com.kaindall.products.domain.category.entities.Category;
import br.com.kaindall.products.domain.product.entities.Product;
import br.com.kaindall.products.domain.product.entities.ProductsPage;
import org.springframework.stereotype.Component;

@Component
public class ProductMapper {
    public Product toDomain (UpdateProductDTO product, Long id, Category category) {
        return new Product(
                id,
                product.name(),
                product.description(),
                category,
                product.price(),
                null
        );
    }

    public Product toDomain (CreateProductDTO product, Category category) {
        return new Product(
                null,
                product.name(),
                product.description(),
                category,
                product.price(),
                product.quantity()
        );
    }

    public ProductsPage toPagination(int page, int size, String sort, boolean ascending) {
        return new ProductsPage(
                page,
                size,
                sort,
                ascending
        );
    }

    public ProductDTO toDTO (Product product) {
        return new ProductDTO(
                product.id(),
                product.name(),
                product.description(),
                product.category().name(),
                product.price(),
                product.quantity()
        );
    }
}
