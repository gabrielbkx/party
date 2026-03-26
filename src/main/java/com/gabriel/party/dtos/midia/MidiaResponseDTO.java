package com.gabriel.party.dtos.midia;

import java.util.UUID;

public record MidiaResponseDTO(
        UUID id,
        String url,
        String tipo,
        Integer ordem,
        UUID prestadorId,
        String prestadorNome
) {}
