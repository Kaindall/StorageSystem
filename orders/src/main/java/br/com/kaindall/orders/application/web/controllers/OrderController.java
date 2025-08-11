package br.com.kaindall.orders.application.web.controllers;

import br.com.kaindall.orders.application.web.dtos.request.CreateOrderDTO;
import br.com.kaindall.orders.application.web.dtos.response.OrderResponseDTO;
import br.com.kaindall.orders.application.web.mappers.OrderMapper;
import br.com.kaindall.orders.domain.orders.facade.OrderFacade;
import br.com.kaindall.orders.domain.orders.entities.Order;
import br.com.kaindall.orders.domain.orders.services.OrderService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController()
@RequestMapping("/orders")
public class OrderController {
    private final Logger log = LogManager.getLogger();
    private final OrderFacade orderFacade;
    private final OrderMapper orderMapper;
    private final OrderService orderService;

    public OrderController(OrderFacade orderFacade, OrderMapper orderMapper, OrderService orderService) {
        this.orderFacade = orderFacade;
        this.orderMapper = orderMapper;
        this.orderService = orderService;
    }


    @PostMapping("")
    public ResponseEntity<Void> createOrder (@Validated @RequestBody CreateOrderDTO order) {
        Order orderResponse = orderFacade.add(orderMapper.toDomain(order));
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @GetMapping("/{user_id}")
    public ResponseEntity<List<OrderResponseDTO>> retrieveAllByUser(
            @PathVariable(name="user_id") Long userId,
            @RequestParam(defaultValue = "1") int page,
            @RequestParam(defaultValue = "20") int size,
            @RequestParam(defaultValue = "date") String sort,
            @RequestParam(defaultValue = "true") boolean ascending) {
        List<OrderResponseDTO> orders = orderService
                .batchRetrieveByUser(userId, orderMapper.toPagination(page-1, size, sort, ascending))
                .stream()
                .map(orderMapper::toDTO)
                .toList();
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(orders);
    }

}
