package br.com.kaindall.products.infrastructure.adapters;

import br.com.kaindall.products.domain.gateways.CategoryGateway;
import br.com.kaindall.products.domain.models.Category;
import br.com.kaindall.products.domain.models.exceptions.categories.CategoryNotFoundException;
import br.com.kaindall.products.domain.models.exceptions.categories.InvalidCategoryException;
import br.com.kaindall.products.domain.models.exceptions.categories.UnknownCategoryException;
import br.com.kaindall.products.infrastructure.adapters.mappers.CategoryEntityMapper;
import br.com.kaindall.products.infrastructure.jpa.entities.CategoryEntity;
import br.com.kaindall.products.infrastructure.jpa.repositories.CategoryRepository;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

@Component
public class CategoryAdapter implements CategoryGateway {
    private final CategoryRepository categoryRepository;
    private final CategoryEntityMapper categoryMapper;

    public CategoryAdapter(CategoryRepository categoryRepository, CategoryEntityMapper categoryMapper) {
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
