package br.com.kaindall.products.infrastructure.queue.movements.utils;

public enum MovementResultStatus {
    SUCCESS("Movimentação realizada com sucesso"),
    FAIL("Falha ao realizar o movimentação"),
    UNKNOWN_PRODUCT("Produto inexistente com o identificador informado"),
    INVALID_QUANTITY("Estoque insuficiente para realizar a movimentação");

    private final String description;

    MovementResultStatus(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }
}
