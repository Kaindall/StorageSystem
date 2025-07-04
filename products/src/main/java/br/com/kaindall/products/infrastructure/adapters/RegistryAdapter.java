package br.com.kaindall.products.infrastructure.adapters;

import br.com.kaindall.products.domain.gateways.RegistryGateway;
import br.com.kaindall.products.domain.models.Registry;
import br.com.kaindall.products.infrastructure.adapters.mappers.RegistryEntityMapper;
import br.com.kaindall.products.infrastructure.jpa.repositories.RegistryRepository;
import org.springframework.stereotype.Component;

@Component
public class RegistryAdapter implements RegistryGateway {
    private final RegistryRepository registryRepository;
    private final RegistryEntityMapper registryMapper;

    public RegistryAdapter(RegistryRepository registryRepository, RegistryEntityMapper registryMapper) {
        this.registryRepository = registryRepository;
        this.registryMapper = registryMapper;
    }

    @Override
    public boolean save(Registry registry) {
        registryRepository.save(registryMapper.toEntity(registry));
        return true;
    }
}
