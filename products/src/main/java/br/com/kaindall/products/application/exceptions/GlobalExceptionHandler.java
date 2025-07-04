package br.com.kaindall.products.application.exceptions;

import br.com.kaindall.products.application.dtos.responses.ErrorDTO;
import br.com.kaindall.products.domain.models.exceptions.BusinessException;
import br.com.kaindall.products.domain.models.exceptions.categories.CategoryNotFoundException;
import br.com.kaindall.products.domain.models.exceptions.categories.InvalidCategoryException;
import br.com.kaindall.products.domain.models.exceptions.categories.UnknownCategoryException;
import br.com.kaindall.products.domain.models.exceptions.products.InvalidProductException;
import br.com.kaindall.products.domain.models.exceptions.products.ProductNotFoundException;
import br.com.kaindall.products.domain.models.exceptions.products.UnavailableProductQuantityException;
import br.com.kaindall.products.domain.models.exceptions.products.UnknownProductException;
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
}
