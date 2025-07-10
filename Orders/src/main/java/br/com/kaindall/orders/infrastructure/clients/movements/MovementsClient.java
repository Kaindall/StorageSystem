package br.com.kaindall.orders.infrastructure.clients.movements;

import br.com.kaindall.orders.infrastructure.clients.movements.dtos.MovementClientResponseDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@FeignClient(name="movementClient", url="${clients.movements.url}")
public interface MovementsClient {

    @GetMapping("/movements/{id_order}")
    List<MovementClientResponseDTO> getAllMovementsByOrderId(@PathVariable("id_order") Long orderId);
}
