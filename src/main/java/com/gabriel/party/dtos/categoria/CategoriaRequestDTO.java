package com.gabriel.party.dtos.categoria;

import jakarta.validation.constraints.NotBlank;

public record CategoriaRequestDTO(
        @NotBlank(message = "O nome da categoria é obrigatório")
        String nome,
        String descricao,
        String iconeUrl) {
}
