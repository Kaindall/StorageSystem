package br.com.kaindall.products.application.web.exceptions;

import br.com.kaindall.products.application.web.dtos.responses.ErrorDTO;
import br.com.kaindall.products.domain.category.entities.exceptions.CategoryNotFoundException;
import br.com.kaindall.products.domain.category.entities.exceptions.InvalidCategoryException;
import br.com.kaindall.products.domain.category.entities.exceptions.UnknownCategoryException;
import br.com.kaindall.products.domain.movement.entities.exceptions.MovementNotFoundException;
import br.com.kaindall.products.domain.product.entities.exceptions.InvalidProductException;
import br.com.kaindall.products.domain.product.entities.exceptions.ProductNotFoundException;
import br.com.kaindall.products.domain.product.entities.exceptions.UnavailableProductQuantityException;
import br.com.kaindall.products.domain.product.entities.exceptions.UnknownProductException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(CategoryNotFoundException.class)
    public ResponseEntity<ErrorDTO> handleCategoryNotFoundException (CategoryNotFoundException error) {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(new ErrorDTO(
                        error.getCode(),
                        error.getMessage(),
                        error.getTimestamp()
                ));
    }

    @ExceptionHandler(InvalidCategoryException.class)
    public ResponseEntity<ErrorDTO> handleInvalidCategoryException (InvalidCategoryException error) {
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(new ErrorDTO(
                        error.getCode(),
                        error.getMessage(),
                        error.getTimestamp()
                ));
    }

    @ExceptionHandler(UnknownCategoryException.class)
    public ResponseEntity<ErrorDTO> handleUnknownCategoryException (UnknownCategoryException error) {
        return ResponseEntity
                .status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(new ErrorDTO(
                        error.getCode(),
                        error.getMessage(),
                        error.getTimestamp()
                ));
    }

    @ExceptionHandler(ProductNotFoundException.class)
    public ResponseEntity<ErrorDTO> handleProductNotFoundException (ProductNotFoundException error) {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(new ErrorDTO(
                        error.getCode(),
                        error.getMessage(),
                        error.getTimestamp()
                ));
    }

    @ExceptionHandler(InvalidProductException.class)
    public ResponseEntity<ErrorDTO> handleInvalidProductException (InvalidProductException error) {
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(new ErrorDTO(
                        error.getCode(),
                        error.getMessage(),
                        error.getTimestamp()
                ));
    }

    @ExceptionHandler(UnavailableProductQuantityException.class)
    public ResponseEntity<ErrorDTO> handleUnavailableProductQuantityException (UnavailableProductQuantityException error) {
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(new ErrorDTO(
                        error.getCode(),
                        error.getMessage(),
                        error.getTimestamp()
                ));
    }

    @ExceptionHandler(UnknownProductException.class)
    public ResponseEntity<ErrorDTO> handleUnknownProductException (UnknownProductException error) {
        return ResponseEntity
                .status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(new ErrorDTO(
                        error.getCode(),
                        error.getMessage(),
                        error.getTimestamp()
                ));
    }

    @ExceptionHandler(MovementNotFoundException.class)
    public ResponseEntity<ErrorDTO> handleMovementNotFoundException (MovementNotFoundException error) {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(new ErrorDTO(
                        error.getCode(),
                        error.getMessage(),
                        error.getTimestamp()
                ));
    }
}
