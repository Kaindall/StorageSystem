package br.com.kaindall.products.domain.product.facades;

import br.com.kaindall.products.domain.category.entities.Category;
import br.com.kaindall.products.domain.exceptions.BusinessException;
import br.com.kaindall.products.domain.movement.entities.Movement;
import br.com.kaindall.products.domain.movement.utils.enums.MovementType;
import br.com.kaindall.products.domain.product.entities.Product;
import br.com.kaindall.products.domain.product.entities.ProductsPage;
import br.com.kaindall.products.domain.category.services.CategoryService;
import br.com.kaindall.products.domain.product.services.ProductService;
import br.com.kaindall.products.domain.movement.services.MovementService;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Component
public class ProductFacade {
    private final CategoryService categoryService;
    private final ProductService productService;
    private final MovementService movementService;

    public ProductFacade(CategoryService categoryService, ProductService productService,
                         MovementService movementService) {
        this.categoryService = categoryService;
        this.productService = productService;
        this.movementService = movementService;
    }

    public List<Product> batchRetrieve(ProductsPage productsPage) {return productService.findAll(productsPage);}

    public Product find(Long productId) {return productService.find(productId);}

    public Category findCategory(String name) {
        return categoryService.retrieveByName(name);
    }

    public Movement increment(Long productId, int quantity, Long orderId) {
        Product product = productService.add(productId, quantity);
        return movementService.add(product, quantity, orderId);
    }

    public Movement decrease(Long productId, int quantity, Long orderId) {
        Product product = productService.decrease(productId, quantity);
        return movementService.decrease(product, quantity, orderId);
    }

    public void remove(Long productId) {
        Product product = productService.find(productId);
        productService.delete(productId);
        movementService.decrease(product, product.quantity(), null);
    }

    public Product save(Product product) {
        return productService.save(product);
    }

    public List<Movement> findMovements(Long id) {
        return movementService.findAll(id);
    }

    public Movement processMovement(Movement movement) {
        return switch (movement.type()) {
            case MovementType.IN -> increment(movement.product().id(), movement.quantity(), movement.orderId());
            case MovementType.OUT -> decrease(movement.product().id(), movement.quantity(), movement.orderId());
            case CANCELED -> null;
        };
    }

    public void emitResult(Movement movement) {
        movementService.result(movement);
    }

    public void emitResult(Long orderId, BusinessException exception) {
        Map<Long, Integer> batchDecrease = new HashMap<>();
        Map<Long, Integer> batchIncrease = new HashMap<>();
        List<Movement> toCancel = new ArrayList<>();

        movementService.findAll(orderId)
                .forEach(movement -> {
                    Long productId = movement.product().id();
                    int quantity = movement.quantity();
                    toCancel.add(movement);

                    switch (movement.type()) {
                        case MovementType.IN -> {
                            int current = batchDecrease.getOrDefault(productId, 0);
                            batchDecrease.put(productId, current + quantity);
                        }
                        case MovementType.OUT -> {
                            int current = batchIncrease.getOrDefault(productId, 0);
                            batchIncrease.put(productId, current + quantity);
                        }
                    }
                });
        batchDecrease.forEach(this::silentDecrease);
        batchIncrease.forEach(this::silentIncrement);
        movementService.cancelAll(toCancel);
        movementService.invalidate(exception);
    }

    private void silentIncrement(Long productId, int quantity) {productService.add(productId, quantity);}

    private void silentDecrease(Long productId, int quantity) {productService.decrease(productId, quantity);}

}
