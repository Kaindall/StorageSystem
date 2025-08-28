package br.com.kaindall.products.domain.product.facades;

import br.com.kaindall.products.domain.exceptions.BusinessException;
import br.com.kaindall.products.domain.movement.strategies.MovementStrategy;
import br.com.kaindall.products.domain.movement.entities.Movement;
import br.com.kaindall.products.domain.movement.factories.MovementFactory;
import br.com.kaindall.products.domain.movement.utils.enums.MovementType;
import br.com.kaindall.products.domain.product.entities.Product;
import br.com.kaindall.products.domain.product.entities.exceptions.ProductNotFoundException;
import br.com.kaindall.products.domain.product.entities.exceptions.UnavailableProductQuantityException;
import br.com.kaindall.products.domain.product.services.ProductService;
import br.com.kaindall.products.domain.movement.services.MovementService;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Component
public class ProductFacade {
    private final ProductService productService;
    private final MovementService movementService;
    private final MovementFactory movementFactory;

    public ProductFacade(
            ProductService productService,
            MovementService movementService,
            MovementFactory movementFactory
            ) {
        this.productService = productService;
        this.movementService = movementService;
        this.movementFactory = movementFactory;
    }

    public Movement increase(Movement movement) {
        Product product = productService.add(movement.product().id(), movement.quantity());
        return movementService.decrease(product, movement.quantity(), movement.orderId());
    }

    public Movement decrease(Movement movement) {
        Product product = productService.decrease(movement.product().id(), movement.quantity());
        return movementService.decrease(product, movement.quantity(), movement.orderId());
    }

    public void remove(Long productId) {
        Product product = productService.find(productId);
        productService.delete(productId);
        movementService.decrease(product, product.quantity(), null);
    }

    public void processMovement(Movement movement) {
        try {
            MovementStrategy movementStrategy = movementFactory.getMovement(movement.type());
            movementStrategy.execute(movement);
        }  catch (ProductNotFoundException exception) {
            emitResult(
                    movement.orderId(),
                    new ProductNotFoundException(movement.product().id(), Map.of(
                            "orderId", movement.orderId(),
                            "productId", movement.product().id()
                    )));
        } catch (UnavailableProductQuantityException exception){
            emitResult(
                    movement.orderId(),
                    new UnavailableProductQuantityException(Map.of(
                            "orderId", movement.orderId(),
                            "productId", movement.product().id()
                    )));
        }
    }

    public void emitResult(Movement movement) {
        movementService.result(movement);
    }

    public void emitResult(Long orderId, BusinessException exception) {
        Map<Long, Integer> productListToDecrease = new HashMap<>();
        Map<Long, Integer> productListToIncrease = new HashMap<>();
        List<Movement> movementListToCancel = new ArrayList<>();

        movementService.findAll(orderId)
                .forEach(movement -> {
                    Long productId = movement.product().id();
                    int quantity = movement.quantity();
                    movementListToCancel.add(movement);

                    switch (movement.type()) {
                        case MovementType.IN -> {
                            int currentQuantity = productListToDecrease.getOrDefault(productId, 0);
                            productListToDecrease.put(productId, currentQuantity + quantity);
                        }
                        case MovementType.OUT -> {
                            int currentQuantity = productListToIncrease.getOrDefault(productId, 0);
                            productListToIncrease.put(productId, currentQuantity + quantity);
                        }
                    }
                });
        productListToDecrease.forEach(this::silentDecrease);
        productListToIncrease.forEach(this::silentIncrement);
        movementService.cancelAll(movementListToCancel);
        movementService.invalidate(exception);
    }

    private void silentIncrement(Long productId, int quantity) {productService.add(productId, quantity);}

    private void silentDecrease(Long productId, int quantity) {productService.decrease(productId, quantity);}

}
