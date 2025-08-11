package br.com.kaindall.products.application.web.controllers;

import br.com.kaindall.products.application.web.dtos.responses.MovementDTO;
import br.com.kaindall.products.application.web.mappers.MovementMapper;
import br.com.kaindall.products.application.web.mappers.ProductMapper;
import br.com.kaindall.products.domain.movement.services.MovementService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/movements")
@Tag(name = "Movimentos", description = "Operações para consultar as movimentações")
public class MovementsController {
    private final MovementService movementService;
    private final MovementMapper movementMapper;

    public MovementsController(MovementService movementService, ProductMapper productMapper, MovementMapper movementMapper) {
        this.movementService = movementService;
        this.movementMapper = movementMapper;
    }


    @Operation(
            operationId = "buscarMovimentações",
            summary = "Retorna informações de um pedido específico"
    )
    @GetMapping("/{id_order}")
    public ResponseEntity<List<MovementDTO>> retrieveProduct(
            @Parameter(description="Identificador da movimentação a ser consultada")
            @PathVariable(name="id_order") Long id) {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(
                        movementService.findAll(id)
                                .stream()
                                .map(movementMapper::toDTO)
                                .toList()
                );
    }
}
