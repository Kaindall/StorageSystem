package br.com.kaindall.orders.domain.orders.entities;

public enum OrderStatus {
    REQUESTED,
    PROCESSING,
    DELIVERING,
    COMPLETED,
    INVALID,
    CANCELED
}
