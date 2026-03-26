package com.gabriel.party.dtos.prestador;

import java.util.UUID;

public record PrestadorResponseDTO(
        UUID id,
        String nome,
        String email,
        String telefone,
        UUID categoriaId,
        String categoriaNome
) {}

