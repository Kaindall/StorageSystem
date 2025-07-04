package br.com.kaindall.products.infrastructure.adapters;

import br.com.kaindall.products.domain.gateways.ProductGateway;
import br.com.kaindall.products.domain.models.Product;
import br.com.kaindall.products.domain.models.ProductsPage;
import br.com.kaindall.products.domain.models.exceptions.categories.CategoryNotFoundException;
import br.com.kaindall.products.domain.models.exceptions.categories.InvalidCategoryException;
import br.com.kaindall.products.domain.models.exceptions.products.InvalidProductException;
import br.com.kaindall.products.domain.models.exceptions.products.ProductNotFoundException;
import br.com.kaindall.products.domain.models.exceptions.products.UnavailableProductQuantityException;
import br.com.kaindall.products.infrastructure.adapters.mappers.ProductEntityMapper;
import br.com.kaindall.products.infrastructure.jpa.entities.CategoryEntity;
import br.com.kaindall.products.infrastructure.jpa.entities.ProductEntity;
import br.com.kaindall.products.infrastructure.jpa.repositories.ProductRepository;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Component;

import java.time.LocalTime;
import java.util.List;
import java.util.NoSuchElementException;

@Component
public class ProductAdapter implements ProductGateway {
    private final ProductRepository productRepository;
    private final ProductEntityMapper productMapper;

    public ProductAdapter(ProductRepository productRepository, ProductEntityMapper productMapper) {
        this.productRepository = productRepository;
        this.productMapper = productMapper;
    }

    @Override
    public Product save(Product product) {
        try {
            if (product.id() == null) {
                return productMapper.toDomain((productRepository.save(productMapper.toEntity(product))));
            }
            ProductEntity currentProduct = productRepository
                    .findById(product.id())
                    .orElseThrow(ProductNotFoundException::new);
            ProductEntity updatedProduct = productMapper.toEntity(currentProduct, product);
            return productMapper.toDomain((productRepository.save(updatedProduct)));
        } catch (DataIntegrityViolationException e) {
            throw new InvalidProductException();
        }
    }

    @Override
    public Product add(Long id, int quantity) {
        try {
            ProductEntity product = productRepository.findById(id).orElseThrow(ProductNotFoundException::new);
            product.setQuantity(product.getQuantity()+quantity);
            return productMapper.toDomain((productRepository.save(product)));
        } catch (DataIntegrityViolationException e) {
            throw new InvalidCategoryException();
        }
    }

    @Override
    public Product decrease(Long id, int quantity) {
        try {
            ProductEntity product = productRepository.findById(id).orElseThrow(ProductNotFoundException::new);
            if (product.getQuantity() < quantity) {
                throw new UnavailableProductQuantityException();
            }
            product.setQuantity(product.getQuantity() - quantity);
            return productMapper.toDomain(productRepository.save(product));
        } catch (DataIntegrityViolationException e) {
            throw new InvalidCategoryException();
        }
    }

    @Override
    public Product findById(Long id) {
        return productMapper.toDomain(productRepository.findById(id).orElseThrow(ProductNotFoundException::new));
    }

    @Override
    public List<Product> findAll(ProductsPage productsPage) {
        Sort.Direction direction = productsPage.ascending() ? Sort.Direction.ASC : Sort.Direction.DESC;
        Pageable pageableProduct = PageRequest.of(
                productsPage.pageNumber(),
                productsPage.pageSize()
        ).withSort(direction, String.valueOf(productsPage.sort()));
        return productRepository
                .findAll(pageableProduct)
                .map(productMapper::toDomain)
                .toList();
    }

    @Override
    public boolean deleteById(Long id) {
        ProductEntity product = productRepository.findById(id).orElseThrow(ProductNotFoundException::new);
        product.setName(product.getName() + "_deleted" + LocalTime.now().toString());
        product.setDeleted(true);
        productRepository.save(product);
        return true;
    }
}
