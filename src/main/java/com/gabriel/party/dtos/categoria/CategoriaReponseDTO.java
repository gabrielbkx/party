package com.gabriel.party.dtos.categoria;

import java.util.UUID;

public record CategoriaReponseDTO(UUID id, String nome, String descricao, String iconeUrl,Integer quantidadePrestadores, boolean ativo) {
}
