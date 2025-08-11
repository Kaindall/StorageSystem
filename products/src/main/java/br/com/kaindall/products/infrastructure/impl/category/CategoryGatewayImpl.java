package br.com.kaindall.products.infrastructure.impl.category;

import br.com.kaindall.products.domain.category.gateways.CategoryGateway;
import br.com.kaindall.products.domain.category.entities.Category;
import br.com.kaindall.products.domain.category.entities.exceptions.CategoryNotFoundException;
import br.com.kaindall.products.domain.category.entities.exceptions.InvalidCategoryException;
import br.com.kaindall.products.infrastructure.impl.category.mappers.CategoryEntityMapper;
import br.com.kaindall.products.infrastructure.jpa.category.entities.CategoryEntity;
import br.com.kaindall.products.infrastructure.jpa.category.repositories.CategoryRepository;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class CategoryGatewayImpl implements CategoryGateway {
    private final CategoryRepository categoryRepository;
    private final CategoryEntityMapper categoryMapper;

    public CategoryGatewayImpl(CategoryRepository categoryRepository, CategoryEntityMapper categoryMapper) {
        this.categoryRepository = categoryRepository;
        this.categoryMapper = categoryMapper;
    }

    @Override
    public Category save(Category category) {
        try {
            if (category.id() == null) {
                return categoryMapper.toDomain((categoryRepository.save(categoryMapper.toEntity(category))));
            }
            CategoryEntity currentCategory = categoryRepository
                    .findById(category.id())
                    .orElseThrow(CategoryNotFoundException::new);
            CategoryEntity updatedCategory = categoryMapper.toEntity(currentCategory, category);
            return categoryMapper.toDomain((categoryRepository.save(updatedCategory)));
        } catch (DataIntegrityViolationException e) {
            throw new InvalidCategoryException();
        }
    }

    @Override
    public Category find(Long id) {
        return categoryMapper.toDomain(categoryRepository
                .findById(id)
                .orElseThrow(CategoryNotFoundException::new));
    }

    @Override
    public Category findByName(String name) {
        return categoryMapper.toDomain((categoryRepository
                .findByName(name)
                .orElseThrow(CategoryNotFoundException::new)));
    }

    @Override
    public List<Category> findAll() {
        List<CategoryEntity> categoryEntities = new ArrayList<>();
        categoryRepository.findAll().forEach(categoryEntities::add);
        return categoryEntities
                .stream()
                .map(categoryMapper::toDomain)
                .toList();
    }

    @Override
    public boolean delete(Long id) {
        if (categoryRepository.existsById(id)) {
            categoryRepository.deleteById(id);
            return true;
        }
        throw new CategoryNotFoundException();
    }
}
