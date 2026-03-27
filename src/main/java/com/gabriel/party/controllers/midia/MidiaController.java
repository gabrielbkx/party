package com.gabriel.party.controllers.midia;


import com.gabriel.party.dtos.midia.MidiaRequestDTO;
import com.gabriel.party.dtos.midia.MidiaResponseDTO;
import com.gabriel.party.services.midia.MidiaService;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;

import java.util.UUID;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/midias")
@Tag(name = "Mídias", description = "Endpoints para gerenciamento de mídias de prestadores")
public class MidiaController {

    private final MidiaService midiaService;

    public MidiaController(MidiaService midiaService) {
        this.midiaService = midiaService;
    }

    @ApiResponses( value = {
            @ApiResponse(responseCode = "201", description = "Mídia criada com sucesso"),
            @ApiResponse(responseCode = "400", description = "Requisição inválida, dados incorretos ou faltando"),
            @ApiResponse(responseCode = "404", description = "Prestador associado não encontrado")
    })
    @Operation(summary = "Criar nova mídia", description = "Cria uma nova mídia associada a um prestador.")
    @PostMapping
    public ResponseEntity<MidiaResponseDTO> criarMidia(@Valid @RequestBody MidiaRequestDTO dto) {
        var midiaCriada = midiaService.salvarMidia(dto);
        var uri = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(midiaCriada.id())
                .toUri();
        return ResponseEntity.created(uri).body(midiaCriada);
    }

    @ApiResponses( value = {
            @ApiResponse(responseCode = "200", description = "Lista de mídias retornada com sucesso")
    })
    @Operation(summary = "Listar todas as mídias", description = "Retorna uma lista paginada de todas as mídias ativas.")
    @GetMapping
    public ResponseEntity<Page<MidiaResponseDTO>> listarTodasMidias(
            @PageableDefault(size = 10, sort = "nome")Pageable pageable) {
        return ResponseEntity.ok(midiaService.listarMidias(pageable));
    }

    @ApiResponses( value = {
            @ApiResponse(responseCode = "200", description = "Mídia retornada com sucesso"),
            @ApiResponse(responseCode = "404", description = "Mídia não encontrada")
    })
    @Operation(summary = "Buscar mídia", description = "Busca os detalhes de uma mídia ativa pelo ID.")
    @GetMapping("/{id}")
    public ResponseEntity<MidiaResponseDTO> buscarMidiaPorId(@PathVariable UUID id) {
        return ResponseEntity.ok(midiaService.buscarMidiaPorId(id));
    }

    @ApiResponses( value = {
            @ApiResponse(responseCode = "200", description = "Mídia atualizada com sucesso"),
            @ApiResponse(responseCode = "400", description = "Requisição inválida"),
            @ApiResponse(responseCode = "404", description = "Mídia não encontrada")
    })
    @Operation(summary = "Atualizar mídia", description = "Atualiza os dados de uma mídia existente pelo ID.")
    @PutMapping("/{id}")
    public ResponseEntity<MidiaResponseDTO> atualizarMidia(@Valid @RequestBody MidiaRequestDTO dto, @PathVariable UUID id) {
        return ResponseEntity.ok(midiaService.atualizarMidia(dto, id));
    }

    @ApiResponses( value = {
            @ApiResponse(responseCode = "204", description = "Mídia inativada com sucesso"),
            @ApiResponse(responseCode = "404", description = "Mídia não encontrada")
    })
    @Operation(summary = "Deletar mídia", description = "Realiza a exclusão lógica (inativação) de uma mídia pelo ID.")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarMidia(@PathVariable UUID id) {
        midiaService.deletar(id);
        return ResponseEntity.noContent().build();
    }
}
