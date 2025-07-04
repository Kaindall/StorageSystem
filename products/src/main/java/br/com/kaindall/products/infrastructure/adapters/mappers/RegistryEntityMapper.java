package br.com.kaindall.products.infrastructure.adapters.mappers;

import br.com.kaindall.products.domain.models.Registry;
import br.com.kaindall.products.infrastructure.jpa.entities.RegistryEntity;
import org.springframework.stereotype.Component;

@Component
public class RegistryEntityMapper {
    private final ProductEntityMapper productMapper;

    public RegistryEntityMapper(ProductEntityMapper productMapper) {
        this.productMapper = productMapper;
    }

    public RegistryEntity toEntity(Registry registry) {
        return new RegistryEntity(
                registry.id(),
                productMapper.toEntity(registry.product()),
                registry.type(),
                registry.quantity(),
                registry.date()
        );
    }
}
