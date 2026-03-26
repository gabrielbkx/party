package com.gabriel.party.controllers.midia;


import com.gabriel.party.dtos.midia.MidiaRequestDTO;
import com.gabriel.party.dtos.midia.MidiaResponseDTO;
import com.gabriel.party.services.midia.MidiaService;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.util.UUID;

@RestController
@RequestMapping("/midias")
public class MidiaController {

    private final MidiaService midiaService;

    public MidiaController(MidiaService midiaService) {
        this.midiaService = midiaService;
    }

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

    @GetMapping
    public ResponseEntity<Page<MidiaResponseDTO>> listarTodasMidias(Pageable pageable) {
        return ResponseEntity.ok(midiaService.listarMidias(pageable));
    }

    @GetMapping("/{id}")
    public ResponseEntity<MidiaResponseDTO> buscarMidiaPorId(@PathVariable UUID id) {
        return ResponseEntity.ok(midiaService.buscarMidiaPorId(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<MidiaResponseDTO> atualizarMidia(@Valid @RequestBody MidiaRequestDTO dto, @PathVariable UUID id) {
        return ResponseEntity.ok(midiaService.atualizarMidia(dto, id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarMidia(@PathVariable UUID id) {
        midiaService.deletar(id);
        return ResponseEntity.noContent().build();
    }
}
