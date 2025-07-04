package br.com.kaindall.orders.application.controllers;

import br.com.kaindall.orders.application.dtos.request.CreateOrderDTO;
import br.com.kaindall.orders.application.dtos.response.OrderResponseDTO;
import br.com.kaindall.orders.application.mappers.OrderMapper;
import br.com.kaindall.orders.domain.entities.Order;
import br.com.kaindall.orders.domain.services.OrderService;
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
    private final OrderService orderService;
    private final OrderMapper orderMapper;

    public OrderController(OrderService orderService, OrderMapper orderMapper) {
        this.orderService = orderService;
        this.orderMapper = orderMapper;
    }


    @PostMapping("")
    public ResponseEntity<OrderResponseDTO> createOrder (@Validated @RequestBody CreateOrderDTO order) {
        Order orderResponse = orderService.addOrder(orderMapper.toDomain(order));
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(orderMapper.toDto(orderResponse));
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
                .map(orderMapper::toDto)
                .toList();
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(orders);
    }

}
