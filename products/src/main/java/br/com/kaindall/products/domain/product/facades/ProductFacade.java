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

import java.util.List;
import java.util.Map;
import java.util.function.BiConsumer;


@Component
public class ProductFacade {
    private static final String ORDER_ID = "orderId";
    private static final String PRODUCT_ID = "productId";

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
            MovementStrategy movementStrategy = movementFactory.getMovementStrategy(movement.movementType());
            movementStrategy.execute(movement);
        }  catch (ProductNotFoundException exception) {
            cancelAllMovementsInOrder(
                    movement.orderId(),
                    new ProductNotFoundException(
                            movement.product().id(),
                            Map.of(
                                    ORDER_ID, movement.orderId(),
                                    PRODUCT_ID, movement.product().id()
                    )));
        } catch (UnavailableProductQuantityException exception){
            cancelAllMovementsInOrder(
                    movement.orderId(),
                    new UnavailableProductQuantityException(Map.of(
                            ORDER_ID, movement.orderId(),
                            PRODUCT_ID, movement.product().id()
                    )));
        }
    }

    public void cancelAllMovementsInOrder(Long orderId, BusinessException exception) {
        List<Movement> movements = movementService.findAllByOrderId(orderId);
        revertProductsQuantities(movements);
        movementService.cancelAll(movements);
        movementService.publishResultFailed(exception);
    }

    private void revertProductsQuantities(List<Movement> movements) {
        Map<MovementType, BiConsumer<Long, Integer>> revertActions = Map.of(
                MovementType.IN, this::silentDecrease,
                MovementType.OUT, this::silentIncrease
        );

        movements
                .stream()
                .filter(movement -> revertActions.containsKey(movement.movementType()))
                .forEach(movement -> revertActions
                        .get(movement.movementType())
                        .accept(movement.product().id(), movement.quantity()));
    }

    private void silentIncrease(Long productId, int quantity) {productService.increase(productId, quantity);}

    private void silentDecrease(Long productId, int quantity) {productService.decrease(productId, quantity);}

}
