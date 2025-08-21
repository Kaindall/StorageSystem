package br.com.kaindall.products.domain.movement;

import br.com.kaindall.products.domain.movement.entities.Movement;
import br.com.kaindall.products.domain.movement.services.MovementService;
import br.com.kaindall.products.domain.product.entities.Product;
import br.com.kaindall.products.domain.product.services.ProductService;
import org.springframework.stereotype.Component;

@Component
public class MovementDecrease implements MovementInterface {

    private final ProductService productService;
    private final MovementService movementService;

    public MovementDecrease(ProductService productService, MovementService movementService) {
        this.productService = productService;
        this.movementService = movementService;
    }

    @Override
    public Movement execute(Movement movement) {
        Product product = productService.decrease(movement.product().id(), movement.quantity());
        return movementService.decrease(product, movement.quantity(), movement.orderId());
    }
}
