package br.com.kaindall.products.infrastructure.adapters.mappers;

import br.com.kaindall.products.domain.models.Category;
import br.com.kaindall.products.domain.models.Product;
import br.com.kaindall.products.domain.models.exceptions.products.InvalidProductException;
import br.com.kaindall.products.infrastructure.jpa.entities.CategoryEntity;
import br.com.kaindall.products.infrastructure.jpa.entities.ProductEntity;
import org.springframework.stereotype.Component;

import java.lang.reflect.Field;

@Component
public class ProductEntityMapper {
    private final CategoryEntityMapper categoryMapper;

    public ProductEntityMapper(CategoryEntityMapper categoryMapper) {
        this.categoryMapper = categoryMapper;
    }

    public ProductEntity toEntity(ProductEntity target, Product source) {
        Field[] sourceAttributes = source.getClass().getDeclaredFields();
        for (Field attribute : sourceAttributes) {
            try {
                attribute.setAccessible(true);
                Object value = attribute.get(source);
                if (value != null) {
                    Field targetAttr = target.getClass().getDeclaredField(attribute.getName());
                    targetAttr.setAccessible(true);
                    if (attribute.getName().equals("category") && value instanceof Category) {
                        targetAttr.set(target, categoryMapper.toEntity((Category) value));
                    } else {
                        targetAttr.set(target, value);
                    }
                }
            } catch (NoSuchFieldException | IllegalAccessException e) {
                throw new InvalidProductException();
            }
        }
        return target;
    }

    public ProductEntity toEntity(Product product) {
        return new ProductEntity(
                product.id(),
                product.name(),
                product.description(),
                categoryMapper.toEntity(product.category()),
                product.price(),
                product.quantity(),
                false
        );
    }

    public Product toDomain(ProductEntity product) {
        return new Product(
                product.getId(),
                product.getName(),
                product.getDescription(),
                categoryMapper.toDomain(product.getCategory()),
                product.getPrice(),
                product.getQuantity()
        );
    }
}
