package com.gabriel.party.dtos.autenticacao.cadastro.cliente;


import com.gabriel.party.dtos.prestador.endereco.EnderecoDTO;
import com.gabriel.party.shared.Endereco;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

public record CadastroClienteDTO(
        @NotBlank(message = "O nome completo é obrigatório")
        String nomeCompleto,

        @NotBlank(message = "O email é obrigatório")
        @Email
        String email,

        @NotBlank(message = "A senha é obrigatória")
        String senha,

        EnderecoDTO endereco
) {}