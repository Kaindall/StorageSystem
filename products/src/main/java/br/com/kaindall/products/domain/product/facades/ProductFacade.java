package br.com.kaindall.products.domain.product.facades;

import br.com.kaindall.products.domain.exceptions.BusinessException;
import br.com.kaindall.products.domain.movement.MovementInterface;
import br.com.kaindall.products.domain.movement.entities.Movement;
import br.com.kaindall.products.domain.movement.factory.MovementFactory;
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

    public void remove(Long productId) {
        Product product = productService.find(productId);
        productService.delete(productId);
        movementService.decrease(product, product.quantity(), null);
    }

    public void processMovement(Movement movement) {
        try {
            MovementInterface movementInterface = movementFactory.getMovement(movement.type());

            movementInterface.execute(movement);
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

    public void processMovementAndEmitResult(Movement movement) {
        try {
            MovementInterface movementInterface = movementFactory.getMovement(movement.type());

            var createdMovement = movementInterface.execute(movement);
            emitResult(createdMovement);
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
                            int currentQuantity = batchDecrease.getOrDefault(productId, 0);
                            batchDecrease.put(productId, currentQuantity + quantity);
                        }
                        case MovementType.OUT -> {
                            int currentQuantity = batchIncrease.getOrDefault(productId, 0);
                            batchIncrease.put(productId, currentQuantity + quantity);
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
