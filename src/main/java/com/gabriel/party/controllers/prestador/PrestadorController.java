package com.gabriel.party.controllers.prestador;


import com.gabriel.party.dtos.prestador.PrestadorRequestDTO;
import com.gabriel.party.dtos.prestador.PrestadorResponseDTO;
import com.gabriel.party.services.prestador.PrestadorService;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.util.UUID;

@RestController
@RequestMapping("/prestadores")
public class PrestadorController {

    private final PrestadorService prestadorService;

    public PrestadorController(PrestadorService prestadorService) {
        this.prestadorService = prestadorService;
    }

    @PostMapping
    public ResponseEntity<PrestadorResponseDTO> criarPrestador(@Valid @RequestBody PrestadorRequestDTO dto) {
        var prestadorCriado = prestadorService.salvarPrestador(dto);
        var uri = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(prestadorCriado.id())
                .toUri();
        return ResponseEntity.created(uri).body(prestadorCriado);
    }

    @GetMapping
    public ResponseEntity<Page<PrestadorResponseDTO>> listarTodosPrestadores(Pageable pageable) {
        return ResponseEntity.ok(prestadorService.listarPrestadores(pageable));
    }

    @GetMapping("/{id}")
    public ResponseEntity<PrestadorResponseDTO> buscarPrestadorPorId(@PathVariable UUID id) {
        return ResponseEntity.ok(prestadorService.buscarPrestadorPorId(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<PrestadorResponseDTO> atualizarPrestador(@Valid @RequestBody PrestadorRequestDTO dto, @PathVariable UUID id) {
        return ResponseEntity.ok(prestadorService.atualizarPrestador(dto, id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarPrestador(@PathVariable UUID id) {
        prestadorService.deletar(id);
        return ResponseEntity.noContent().build();
    }
}
